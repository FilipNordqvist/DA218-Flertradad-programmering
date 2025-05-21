package model;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import java.util.*;


public class PatientManager {
    private  Map<String, Patient> patients;
    private ExecutorService threadPool;

    public PatientManager() {
        patients = new HashMap<>();
        threadPool = Executors.newFixedThreadPool(5);
    }


    public CompletableFuture<String> addPatientEx(Patient patient) {
        CompletableFuture<String> future = new CompletableFuture<>();

        threadPool.submit(() -> {
            try{
                Thread.sleep(500);
                patients.put(patient.getId(),patient);
                future.complete((String.format("Patient %s Age %d added successfully.", patient.getId(),patient.getAge())));
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;

    }

    public CompletableFuture<String> updatePatientEx(Patient patient)
    {
        CompletableFuture<String> future = new CompletableFuture<>();

        threadPool.submit(() -> {
            try {
                Thread.sleep(400);

                if (patients.containsKey(patient.getId())) {
                    patients.replace(patient.getId(), patient);
                    future.complete("Patient updated successfully.");
                } else {
                    future.complete("Patient not found");
                }
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public Future<String> updatePatient(Patient patient) {
        Callable<String> task = () -> {
            if (patients.containsKey(patient.getId())) {
                patients.put(patient.getId(), patient);
                return "Patient updated successfully.";
            }
            return "Patient not found.";
        };
        return threadPool.submit(task);
    }

    public Future<Patient> getPatient(String patientID) {
        Callable<Patient> task = () -> patients.get(patientID);
        return threadPool.submit(task);
    }
    public Future<Patient> getPatient(int index) {
        int size = patients.size();
        List<String> keys = new ArrayList<>(patients.keySet());

        //indexes need to be reversed - hashmap saves them in reverse order newest at the beginning
        Collections.reverse(keys);
        String id = keys.get(index);

        Callable<Patient> task = () -> patients.get(id);
        return threadPool.submit(task);
    }



    public CompletableFuture<String> deletePatientEx(String patientID){
        CompletableFuture<String> future = new CompletableFuture<>();

        threadPool.submit(() -> {
            try{
                Thread.sleep(300);
                if(patients.containsKey(patientID)){
                    patients.remove(patientID);

                    future.complete(String.format("Patient with id = %s deleted successfully.", patientID));
                }
                else
                    future.complete("Patient not found");
            }catch (Exception e){
                future.completeExceptionally(e);
            }
        });
        return future;
    }


    public void shutdown() {
        threadPool.shutdown();
    }

    public CompletableFuture<List<String>> getAllPatientsStrings()
    {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        List<String> patientInfo = new ArrayList<>();

        threadPool.submit(() -> {
            try {
                Thread.sleep(100);
                for(Patient patient : patients.values()){
                    patientInfo.add((patient.toString()));
                }

                future.complete(patientInfo);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    public CompletableFuture<Patient> getPatientInfo(int index){
        CompletableFuture<Patient> future = new CompletableFuture<>();

        threadPool.submit(() -> {
            try{
                Thread.sleep(200);

                List<String> keyList = new ArrayList<>(patients.keySet());


                Collections.reverse(keyList);

                String id = keyList.get(index);
                System.out.println(String.format("Key %s corresponds to index = %d", id, index));
                Patient patient = patients.get(id);

                future.complete(patient);

            }catch (Exception e){
                future.completeExceptionally(e);
            }
        });

        return future;
    }



    public CompletableFuture<Patient> searchPatientsByName(String nameToSearch) {
        CompletableFuture<Patient> future = new CompletableFuture<>();

        threadPool.submit(() -> {
            try {
                Thread.sleep(300);

                Patient matchingPatient = null;

                for (Patient patient : patients.values()) {
                    if (patient.getName().equals(nameToSearch)) {
                        matchingPatient = patient;
                        break;
                    }
                }

                if (matchingPatient != null) {
                    future.complete(matchingPatient);
                } else {
                    future.complete(null);
                }
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }
}