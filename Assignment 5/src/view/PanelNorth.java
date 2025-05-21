package view;

import controller.Controller;
import model.GenderType;
import model.OptionType;

import javax.swing.*;
import java.awt.*;

public class PanelNorth extends JPanel
{

    private Controller controller;
    private int width, height;
    private JPanel pnlPatientDataTextFields;

    private JLabel lblID =  new JLabel("Assigned by the system.");
    private  JTextField txtName = new JTextField();

    private JTextField txtAge = new JTextField();

    private JTextField txtHistory = new JTextField();
   // private JTextField txtGender = new JTextField();
    private JComboBox cmbGender;
    private  JTextField txtStatus = new JTextField();


    public PanelNorth(Controller controller, int width, int height)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;

        addComponents();
    }
    private void addComponents()
    {
        JPanel pnlPatientData = new  JPanel();
        pnlPatientData.setPreferredSize(new Dimension(width-40, (int)(0.4*(height))));
        GUIUtilities.setPanelBorder(pnlPatientData, "Patient data", 6);

        JPanel pnlPatientDataLabels = new JPanel(new GridLayout(6, 1, 5, 5));
        pnlPatientDataLabels.setPreferredSize(new Dimension((int)(0.3*width), (int)(0.3*height-6)));

        pnlPatientDataLabels.add(new JLabel("Patient id"));
        pnlPatientDataLabels.add(new JLabel("Name"));
        pnlPatientDataLabels.add(new JLabel("Age"));

        pnlPatientDataLabels.add(new JLabel("Gender"));

        pnlPatientDataLabels.add(new JLabel("Health history"));

        pnlPatientDataLabels.add(new JLabel("Current status"));
        pnlPatientData.add(pnlPatientDataLabels);

        pnlPatientDataTextFields = new JPanel(new GridLayout(6, 1, 5, 5));
        pnlPatientDataTextFields.setPreferredSize(new Dimension((int)(0.5*width), (int)(0.3*(height-6))));

        lblID.setForeground(new Color(50,140, 30));
        pnlPatientDataTextFields.add(lblID);
        pnlPatientDataTextFields.add(txtName);
        pnlPatientDataTextFields.add(txtAge);

        //pnlPatientDataTextFields.add(txtGender);

        GenderType[] genderType = GenderType.values();
        String[] names = new String[genderType.length];

        for (int i=0; i < names.length; i++)
            names[i] = genderType[i].name();

        cmbGender = new JComboBox<>(names);

        cmbGender.setPreferredSize(new Dimension(150, 20)); //(int)(0.05 * (height))));
        pnlPatientDataTextFields.add(cmbGender);

        pnlPatientDataTextFields.add(txtHistory);

        pnlPatientDataTextFields.add(txtStatus);

        pnlPatientData.add(pnlPatientDataTextFields);

        add(pnlPatientData, BorderLayout.NORTH);

     }

    public String getId(){
        String text = lblID.getText();
        return text;
    }
    public void setId(String id){
        lblID.setText(id);
    }
    public String getName(){return txtName.getText();}

    public String getAge(){
        String text = txtAge.getText();
        return text;
    }
    public String getGender(){return (String)cmbGender.getSelectedItem();}
    public String getHealthHistory(){return txtHistory.getText();}
    public String getHealthStatus(){return txtStatus.getText();}

    public void setPatientInfo(String[] patientInfo){
        lblID.setText(patientInfo[0]);
        txtName.setText(patientInfo[1]);
        txtAge.setText(patientInfo[2]);
        cmbGender.setSelectedItem(patientInfo[3]);

        txtHistory.setText(patientInfo[4]);
        txtStatus.setText(patientInfo[5]);
    }
}
