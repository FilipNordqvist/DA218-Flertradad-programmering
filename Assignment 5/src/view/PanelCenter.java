package view;

import controller.Controller;
import model.OptionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class PanelCenter extends JPanel implements ActionListener {

    private Controller controller;
    private int width, height;
    JComboBox<String> cmbOptions;
    JButton btnOK = new JButton("Execute");
    JLabel lblInfo = new JLabel();
    public PanelCenter(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        addComponents();
    }

    private void addComponents() {
        int height2 = (int) (0.12 * height);
        //JPanel pnlCenter = new JPanel();

        setLayout(new GridLayout(2, 1, 5, 5));
        setPreferredSize(new Dimension(width - 20, height2));
        GUIUtilities.setPanelBorder(this, "ToDo", 6);

       // JButton btnOK = new JButton("Go");

        btnOK.setPreferredSize(new Dimension(100, 20));
        //operationComboBox = new JComboBox<>(new String[]{"Add", "Update", "Search", "Delete"});
        OptionType[] options = OptionType.values();
        String[] names = new String[options.length];

        for (int i=0; i < names.length; i++)
            names[i] = options[i].name();

        cmbOptions = new JComboBox<>(names);

        cmbOptions.setPreferredSize(new Dimension(100, 20));
        add(cmbOptions);
        add(btnOK);
       // lblInfo.setBorder(BorderFactory.createEmptyBorder());
        lblInfo.setPreferredSize(new Dimension(width, 20));
        lblInfo.setForeground(new Color(190,30, 30));

        add(lblInfo);

        btnOK.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        //use an enum for update, etc
        if (e.getSource() == btnOK) {
            int index = cmbOptions.getSelectedIndex();
            if (index >= 0) {
                try {
                    controller.execute(index);
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void setStatusText(String message)
    {
        lblInfo.setText(message);
    }

}
