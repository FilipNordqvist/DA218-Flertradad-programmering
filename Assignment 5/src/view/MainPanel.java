package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private int width;
    private int height;
    private Controller controller;

//    private JPanel pnlPatientDataTextFields;

    private PanelNorth pnlNorth;
    private PanelCenter pnlCenter;
    private PanelSouth pnlSouth;

    public MainPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width-20, height-20));
        setLayout(new BorderLayout(5, 5));
        GUIUtilities.setPanelBorder(this, "", 10);


        //patient data
        pnlNorth = new PanelNorth(controller, width, height);
        add(pnlNorth, BorderLayout.NORTH);

        pnlCenter = new PanelCenter(controller, width, height);
        add(pnlCenter, BorderLayout.CENTER);

        pnlSouth = new PanelSouth(controller, getWidth(), (int)(0.4*(height-50)));
        //pnlSouth.addTestItems();
        add(pnlSouth, BorderLayout.SOUTH);

    }

    public void setId(String id){
        pnlNorth.setId(id);
    }
    public String getId(){return pnlNorth.getId();}
    public String getName(){return pnlNorth.getName();}
    public String getAge(){return pnlNorth.getAge();}
    public String getGender(){return pnlNorth.getGender();}
    public String getHealthHistory(){return pnlNorth.getHealthHistory();}
    public String getHealthStatus(){return pnlNorth.getHealthStatus();}

    public void updateStatus(String message)
    {
         pnlCenter.setStatusText(message);
    }
    public void updateStatus(String message, boolean clearList)
    {
        pnlSouth.setStatusText(message, clearList);
    }

    public void setPatientInfo(String[] patientInfo){
        pnlNorth.setPatientInfo(patientInfo);
    }
    public void updatePatientList(ArrayList<String> patientInfo)
    {
        pnlSouth.updatePatientList(patientInfo);
    }
}