package view;

import controller.Controller;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PanelPlayMusic extends JPanel implements ActionListener {
    private JButton btnOpen; // Open audio file
    private JButton btnPlay; // Start playing audio
    private JButton btnStop; // Stop playing
    private JButton btnPause;
    private JButton btnResume;
    private JLabel lblFileName; // Audio file
    private String fileName;
    Controller controller;

    public PanelPlayMusic(Controller controller) {
        this.controller = controller;

        Color lightG = new Color(230,230,230);

        Border b1 = BorderFactory.createTitledBorder("Play background music");
        setBorder(b1);
        setBounds(12, 12, 470, 100);
        setLayout(null);

        lblFileName = new JLabel("  Loaded audio File . . . ");
        lblFileName.setBounds(20, 30, 430, 24);
        // set the border of this component

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        lblFileName.setBorder(border);
        add(lblFileName);

        btnOpen = new JButton("Open");
        btnOpen.setBounds(20, 65, 80, 24);
        add(btnOpen);

        btnPlay = new JButton("Play");
        btnPlay.setBounds(105, 65, 80, 24);
        add(btnPlay);

        btnStop = new JButton("Stop");
        btnStop.setBounds(190, 65, 80, 24);
        add(btnStop);

        btnPause = new JButton("Pause");
        btnPause.setBounds(275, 65, 80, 24);
        btnPause.setBackground(lightG);
        add(btnPause);

        btnResume = new JButton("Resume");
        btnResume.setBounds(360, 65, 90, 24);
        btnResume.setBackground(lightG);
        add(btnResume);

        btnOpen.addActionListener(this);
        btnPlay.addActionListener(this);
        btnStop.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnPlay) {
            if ( (fileName != null) && (!fileName.isEmpty()) )
            controller.startMusic();
        } else if (e.getSource() == btnStop) {
            controller.stopMusic();
        } else if (e.getSource() == btnOpen) {
              JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("music", "wav"));
            fileChooser.setAcceptAllFileFilterUsed(true);
            int dlg = fileChooser.showOpenDialog(null);

            if (dlg == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                fileName = file.getPath();
                lblFileName.setText(fileName);
                try {
                    controller.chooseMusic(fileName);
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }

    }
}


