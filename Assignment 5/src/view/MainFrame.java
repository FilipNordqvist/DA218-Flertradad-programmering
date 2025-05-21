package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private final int width = 600;
    private final int height = 600;

    private Controller controller;
    private MainPanel mainPanel;

    public MainFrame(Controller controller)
    {
        this.controller = controller;
        ShowMainFrame();
    }

    private void ShowMainFrame()
    {
        mainPanel = new MainPanel(controller, width, height);
        JFrame frame = new JFrame("Patient Management System <by your name>");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public String getId(){return mainPanel.getId();}
    public void setId(String id){mainPanel.setId(id); }
    public String getName(){return mainPanel.getName();}
    public String getAge(){return mainPanel.getAge();}
    public String getGender(){return mainPanel.getGender();}
    public String getHealthHistory(){return mainPanel.getHealthHistory();}
    public String getHealthStatus(){return mainPanel.getHealthStatus();}
    public void updateStatus(String message)  {mainPanel.updateStatus(message);    }
    public void updateStatus(String message, boolean clearList)
    {

        mainPanel.updateStatus(message, clearList);
    }

    public void setPatientInfo(String[] patientInfo){
        mainPanel.setPatientInfo(patientInfo);
    }
    public void updatePatientList(ArrayList<String> patientInfo)
    {
        mainPanel.updatePatientList(patientInfo);
    }
}
