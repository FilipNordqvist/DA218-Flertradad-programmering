package view;

import javax.swing.*;

import controller.Controller;

/**
 * The GUI for assignment 3
 */
public class View {

    private JFrame frame;				// The Main window

     private Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }
    private PanelProgress pnlBuffer;
    private PanelProducers pnlProducers;
    private PanelConsumers pnlConsumers;

    public void Start()
    {
        frame = new JFrame();
        frame.setBounds(0, 0, 730, 526);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Food Supply System");
        InitializeGUI();					// Fill in components

        frame.setVisible(true);
        frame.setResizable(false);			// Prevent user from change size
        frame.setLocationRelativeTo(null);	// Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void InitializeGUI()
    {
        // First create the three main panels
        pnlBuffer = new PanelProgress(controller);
        frame.add(pnlBuffer);

        pnlProducers = new PanelProducers(controller);
        frame.add(pnlProducers);

        pnlConsumers = new PanelConsumers(controller);
        frame.add(pnlConsumers);

    }
    public void updateProgressbar(int value) {
        pnlBuffer.updateProgressbar(value);
    }


    public void updateConInfo(int type, int items) {
        pnlConsumers.updateConInfo( type,  items);

    }

    public void updateFoodList(String foodItem, String foodStore) {
        pnlConsumers.updateProductList(foodItem, foodStore);
     }

    public void setProducerStatus(String producerName, String status)
    {
        pnlProducers.setProducerStatus(producerName, status);
    }
    public void setConsumerStatus(String consumerName, String status)
    {
        pnlConsumers.setConsumerStatus(consumerName, status);
      }

    public boolean getContinueLoading(String consumerName) {
       return pnlConsumers.getContinueLoading(consumerName);
    }


}