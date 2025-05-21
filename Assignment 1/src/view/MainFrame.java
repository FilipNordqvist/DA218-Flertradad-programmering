package view;

import java.awt.Polygon;

import javax.swing.JFrame;

import controller.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class MainFrame {
    /**
     * These are the components you need to handle. You have to add listeners and/or
     * code
     */
    private JFrame frame; // The Main window
    private Controller controller;
    PanelDisplayText pnlDisplay;
    PanelRotateImage  pnlTriangle;
    PanelPlayMusic pnlSound;
    PanelCatchMe pnlCatchMe;
    /**
     * Constructor
     */
    public MainFrame(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the application
     */
    public void Start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 830, 438);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multiple Thread Demonstrator");
        InitializeGUI(); // Fill in components
        frame.setVisible(true);
        frame.setResizable(false); // Prevent user from change size
        frame.setLocationRelativeTo(null); // Start middle screen
    }


    private void InitializeGUI() {
        // The music player outer panel

        pnlSound = new PanelPlayMusic(controller);
        frame.add(pnlSound);

        // The moving display outer panel
        pnlDisplay = new PanelDisplayText(controller);
        frame.add(pnlDisplay);

        // The moving graphics outer panel
        pnlTriangle = new PanelRotateImage(controller);
        // Add this to main window
        frame.add(pnlTriangle);

        pnlCatchMe = new PanelCatchMe(controller);
        frame.add(pnlCatchMe);

    }


    public void drawTriangle(Polygon p) {
        pnlTriangle.drawTriangle(p);
    }


    public void setDisplayText(String txt, int x, int y) {
        pnlDisplay.setDisplayText(txt,x,y);
    }
}

