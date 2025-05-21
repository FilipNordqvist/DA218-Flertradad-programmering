package view;

import controller.Controller;
import model.TaskType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRotateImage extends JPanel implements ActionListener {

    Controller controller;
    private JButton btnTriangle;// Start moving graphics thread
    private JButton btnTStop; // Stop moving graphics thread
    private JPanel pnlRotate; // The panel to move graphics in

    public PanelRotateImage(Controller controller) {
        this.controller = controller;

        Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
        setBorder(b3);
        setBounds(240, 118, 242, 269);
        setLayout(null);

        // Add buttons and drawing panel to this panel
        btnTriangle = new JButton("Start Rotate");
        btnTriangle.setBounds(10, 226, 121, 23);
        add(btnTriangle);

        btnTStop = new JButton("Stop");
        btnTStop.setBounds(135, 226, 75, 23);
        add(btnTStop);

        pnlRotate = new JPanel();
        pnlRotate.setBounds(10, 19, 220, 200);
        Border b4 = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        pnlRotate.setBorder(b4);
        add(pnlRotate);

        btnTriangle.addActionListener(this);
        btnTStop.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTriangle) {
        //    controller.Start(TaskType.RotatTriangle);
        }
        else if (e.getSource() == btnTStop) {
            //controller.Stop(TaskType.RotatTriangle);
        }

    }
    public void drawTriangle(Polygon p) {
        Graphics g = pnlRotate.getGraphics();
        g.clearRect(10, 10, 200, 200); //Clear the window
        g.drawPolygon(p); //Draw triangle
    }

}
