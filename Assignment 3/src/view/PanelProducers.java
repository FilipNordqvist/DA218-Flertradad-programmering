package view;

import javax.swing.*;
import controller.Controller;

import java.awt.*;

public class PanelProducers extends JPanel {
    private Controller controller;
    private JButton btnStartS;            // Button start producer 1
    private JButton btnStopS;            // Button stop producer 1
    private JLabel lblStatusS;            // Status producer 1

    public PanelProducers(Controller controller) {
        this.controller = controller;
        createProducer1();
        createProducer2();
        createProducer3();
    }

    public void setProducerStatus(String producerName, String status) {

        switch (producerName) {
            case "Scan":
                lblStatusS.setText(String.format("Status: %s", status));
                break;
            case "Arla":
                lblStatusA.setText(String.format("Status: %s", status));
                break;
            case "Axfood":
                lblStatusX.setText(String.format("Status: %s", status));
                break;
        }
    }

    private void createProducer1() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "  Producers  "));
        setBounds(13, 13, 229, 379);
        setLayout(null);

        //Producer panel
        JPanel pnlScan = new JPanel();
        pnlScan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), " Producer: 1 " ));
        pnlScan.setBounds(6, 19, 217, 100);
        pnlScan.setLayout(null);

        //Status
        lblStatusS = new JLabel("Status:" + controller.getStatusProducer());
        lblStatusS.setBounds(10, 31, 200, 13);
        pnlScan.add(lblStatusS);

        //Button start
        btnStartS = new JButton("Start Producing");
        btnStartS.setBounds(10, 59, 125, 23);
        btnStartS.addActionListener(l -> {
            controller.startProducer(0);   //TO DO
            btnStartS.setEnabled(false);
        }); //actionlistener to controller method
        pnlScan.add(btnStartS);

        //Button stop
        btnStopS = new JButton("Stop");
        btnStopS.setBounds(140, 59, 65, 23);
        btnStopS.addActionListener(l -> {
            controller.stopProducer(0);  //TO DO
            lblStatusS.setText(controller.getStatusProducer());
            btnStartS.setEnabled(true);
        }); //actionlistener to controller method
        pnlScan.add(btnStopS);

        add(pnlScan);
    }

    private JButton btnStartA;			// Button start producer 2
    private JButton btnStopA;			// Button stop producer 2
    private JLabel lblStatusA;			// Status producer 2

    private void createProducer2() {
        JPanel pnlArla = new JPanel();
        pnlArla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)," Producer: 2 "));
        pnlArla.setBounds(6, 139, 217, 100);
        pnlArla.setLayout(null);

        //status
        lblStatusA = new JLabel("Status:" + controller.getStatusProducer());
        lblStatusA.setBounds(10, 31, 200, 13);
        pnlArla.add(lblStatusA);

        // button start
        btnStartA = new JButton("Start Producing");
        btnStartA.setBounds(10, 59, 125, 23);
        btnStartA.addActionListener(l -> {
            controller.startProducer(1);   //TO DO
            btnStartA.setEnabled(false);
        }); //actionlistener to controller method
        pnlArla.add(btnStartA);

        //Button Stop
        btnStopA = new JButton("Stop");
        btnStopA.setBounds(140, 59, 65, 23);
        btnStopA.addActionListener(l -> {
            controller.stopProducer(1);
            lblStatusS.setText(controller.getStatusProducer());
            btnStartA.setEnabled(true);
        }); //actionlistener to controller method
        pnlArla.add(btnStopA);

        add(pnlArla);

    }

    private JButton btnStartX;			// Button start AxFood
    private JButton btnStopX;			// Button stop AxFood
    private JLabel lblStatusX;			// Status AxFood

    private void createProducer3() {
        JPanel pnlAxfood = new JPanel();
        pnlAxfood.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)," Producer: 3 "));
        pnlAxfood.setBounds(6, 262, 217, 100);
        pnlAxfood.setLayout(null);

        lblStatusX = new JLabel("Status:" + controller.getStatusProducer());
        lblStatusX.setBounds(10, 31, 200, 13);
        pnlAxfood.add(lblStatusX);

        //Button Start
        btnStartX = new JButton("Start Producing");
        btnStartX.setBounds(10, 59, 125, 23);
        btnStartX.addActionListener(l -> {
            controller.startProducer(2);  //TO DO
            btnStartX.setEnabled(false);}); //actionlistener to controller method and disable button
        pnlAxfood.add(btnStartX);

        //Button stop
        btnStopX = new JButton("Stop");
        btnStopX.setBounds(140, 59, 65, 23);
        btnStopX.addActionListener(l -> {
            controller.stopProducer(2);  //TO DO
            lblStatusS.setText(controller.getStatusProducer());
             btnStartX.setEnabled(true);
            }); //actionlistener to controller method
        pnlAxfood.add(btnStopX);

        add(pnlAxfood);

    }
}