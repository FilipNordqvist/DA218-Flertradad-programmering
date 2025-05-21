package view;

import controller.Controller;
import model.TaskType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDisplayText extends JPanel implements ActionListener {
    Controller controller;
    private JButton btnDisplay; // Start thread moving display
    private JButton btnDStop; // Stop moving display thread
    private JPanel pnlMove; // The panel to move display in

    public PanelDisplayText(Controller controller) {
        this.controller = controller;
        Border b2 = BorderFactory.createTitledBorder("Moving Text");
        setBorder(b2);
        setBounds(12, 118, 222, 269);
        setLayout(null);

        // Add buttons and drawing panel to this panel
        btnDisplay = new JButton("Start Display");
        btnDisplay.setBounds(10, 226, 121, 23);
        add(btnDisplay);

        btnDStop = new JButton("Stop");
        btnDStop.setBounds(135, 226, 75, 23);
        add(btnDStop);

        pnlMove = new JPanel();
        pnlMove.setBounds(10, 19, 200, 200);
        Border b21 = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        pnlMove.setBorder(b21);
        add(pnlMove);

        // Adding listener to buttons
        btnDisplay.addActionListener(this);
        btnDStop.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDisplay) {
           controller.start(TaskType.DisplayText);
        }
        else if (e.getSource() == btnDStop) {
           controller.stop(TaskType.DisplayText);
        }
    }
    public void setDisplayText(String txt, int x, int y) {
        Graphics g = pnlMove.getGraphics();
        g.clearRect(1, 1, 198, 198); //Clear the window
        g.drawChars(txt.toCharArray(), 0, txt.length(), x, y); //Draw the text
    }
}