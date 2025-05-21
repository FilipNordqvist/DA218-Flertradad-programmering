package view;

import controller.Controller;
import model.TaskType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelCatchMe extends JPanel implements ActionListener, ItemListener {
    private JButton btnStart; // Start game catch me
    private JButton btnStop; // Start game catch me
    private JPanel pnlGame; // The panel to play in
    private JLabel lblScore; // Dispaly hits
    private JComboBox<String> cmbLevel; // Skill combo box, needs to be filled in
    private JLabel lblSkill;
    private Controller controller;
    private ImageIcon icon;

    public PanelCatchMe(Controller controller)
    {
        this.controller = controller;

        Color lightG = new Color(230,230,230);

        // The game outer panel
        Border border = BorderFactory.createTitledBorder("  Game Catch Me  ");
        setBorder(border);
        setBounds(488, 12, 320, 375);
        setLayout(null);

        // Add controls to this panel
        lblSkill = new JLabel("Level:");
        lblSkill.setBounds(20, 20, 50, 18);
        add(lblSkill);

        JLabel lblHits = new JLabel("Hits:");
        lblHits.setBackground(Color.GRAY);
        lblHits.setBounds(240, 20, 60, 16);
        add(lblHits);

        String items[] = {"200 ms","400 ms", "600 ms", "800 ms", "900 ms", "1000 ms"};
        cmbLevel = new JComboBox(items);


        cmbLevel.setBounds(10, 41, 70, 24);
        add(cmbLevel);
        cmbLevel.addItemListener(this);

        btnStart = new JButton("Start");
        btnStart.setBounds(90, 40, 70, 24);
        add(btnStart);

        btnStop= new JButton("Start");
        btnStop.setBounds(165, 40, 70, 24);
        add(btnStop);

        lblScore = new JLabel(""); // Needs to be updated
        lblScore.setBounds(240, 40, 60, 24);
        Border b40 = BorderFactory.createLineBorder(Color.GRAY);
        lblScore.setBorder(b40);
        add(lblScore);

        pnlGame = new JPanel();
        pnlGame.setBounds(10, 71, 290, 283);
        pnlGame.setToolTipText("Click on the moving figure to get score!");
        Border b41 = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        pnlGame.setBorder(b41);
        add(pnlGame);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
          //  controller.Start(TaskType.RotatTriangle);
        }
    }
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
        if (e.getSource() == cmbLevel) {
            lblScore.setText(cmbLevel.getSelectedItem() + " selected");

        }
    }


}
