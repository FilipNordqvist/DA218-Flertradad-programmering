package controller;

import model.GenderType;
import model.Patient;
import model.PatientManager;
import view.MainFrame;
import model.OptionType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Controller {
    private MainFrame gui;
    private PatientManager model;
    static private int idStart = 100;


    public Controller() {
        SwingUtilities.invokeLater(() -> {  //make GUI-to be updated by other threads
            gui = new MainFrame(this);
            gui.setVisible(true);
        });
        model = new PatientManager();
    }



    public void  execute(int index) throws ExecutionException, InterruptedException {

        CompletableFuture<String> result= null;//= new CompletableFuture<String>();

        Future<?> result2 = null;

        // Perform other operations while waiting for the result
        String message = null;
        String errMessage = "To be completed...";
        OptionType option = OptionType.getValue(index);
        Patient patient = null; //

        switch (option) {

            case Add:
                patient = createPatient(null);
                result = model.addPatientEx(patient);
                message = (String) result.get();
                if (patient.getAge() <= 0)
                    message += "Invalid age, Update!";

                System.out.println(message);
                gui.updateStatus(message);
                break;

            case Update:
                String patId = gui.getId();
                result2 = model.getPatient(patId);

                patient = (Patient) result2.get();
                Patient newPatient = createPatient(patient.getId());

                result = model.updatePatientEx(newPatient);
                message = (String) result.get();
                System.out.println(message);
                gui.updateStatus(message);
                break;


            case Search_by_name:
                String nameToSearch = gui.getName();

                CompletableFuture<Patient> resultFuture = model.searchPatientsByName(nameToSearch);
                Patient matchingPatient = resultFuture.get();

                if (matchingPatient != null) {
                    ArrayList<String> patientList = new ArrayList<>();
                    patientList.add(matchingPatient.toString());
                    gui.updateStatus("Patient found in the registry:");
                    gui.updatePatientList(patientList);
                } else {
                    gui.updateStatus("Patient not found.");
                }
                break;

            case Delete:
                patId = gui.getId();
                result = model.deletePatientEx(patId);
                message = result.get();
                System.out.println(message);
                gui.updateStatus(message);
                break;


                case ListAll:
                DisplayAllPatientsInfo();
                break;

        }
        if(option!=OptionType.ListAll)
            DisplayAllPatientsInfo();

    }

    private void DisplayAllPatientsInfo() throws ExecutionException, InterruptedException {

        Future<?> result2 = model.getAllPatientsStrings();

        ArrayList<String> patientList = (ArrayList<String>)result2.get();

        Collections.reverse(patientList);

        gui.updatePatientList(patientList);
    }

    private Patient createPatient(String id) {
        if(id==null){
            id = "Patient id: " + String.valueOf(idStart++);
            gui.setId(id);
        }

        String name = gui.getName();
        int age = 0;
        try{
            age = Integer.parseInt(gui.getAge());
        } catch (Exception e) {
            age = 0;
        }

        GenderType gender = GenderType.valueOf(gui.getGender());

        String healthHistory = gui.getHealthHistory();
        String currentStatus = gui.getHealthStatus();


        Patient patient = new Patient(id,name,age,gender);

        patient.setMedicalHistory(healthHistory);
        patient.setCurrentHealthStatus(currentStatus);

        return patient;
    }

     public void onPatientListboxChanged(int index) throws ExecutionException, InterruptedException
    {
      CompletableFuture<Patient> result = model.getPatientInfo(index);
       Patient patient = (Patient) result.get();

        String[] patientInfo = new String[6];

        if (patient != null) {
            patientInfo[0] = patient.getId();
            patientInfo[1] = patient.getName();
            patientInfo[2] = String.valueOf(patient.getAge());
            patientInfo[3] = patient.getGender().toString();
            patientInfo[4] = patient.getMedicalHistory();
            patientInfo[5] = patient.getCurrentHealthStatus();

            gui.setPatientInfo(patientInfo);
        }
    }


}