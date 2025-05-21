package view;

import controller.Controller;
import model.Patient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PanelSouth extends JPanel {
    private JList<String> list;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private Controller controller;
    private int width, height;

    private JLabel lblLogbook = new JLabel("Threads on duty!");

    //public PanelSouth(Controller controller, int width, int height)
    public PanelSouth(Controller controller, int width, int height) {
        this.controller = controller;

        this.width = width;
        this.height = height;
        int margin = 6;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));

        // Create a JList and set its model to the DefaultListModel
        JList<String> list = new JList<>(listModel);

        JScrollPane s = GUIUtilities.setListScrollPanes(list, width + margin, height);
        GUIUtilities.setPanelBorder(this, " logbook ", margin);

        list.addListSelectionListener(selectionListener);

        //addListener();
        add(s, BorderLayout.NORTH);
    }

    ListSelectionListener selectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JList<String> source = (JList<String>) e.getSource();
                String selectedValue = source.getSelectedValue();

                int index = source.getSelectedIndex();

                try {
                    if (index >= 0)
                       controller.onPatientListboxChanged(index);
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                //System.out.println("Selected value: " + selectedValue);
            }
        }
    };


    public void updateListsBox(String [] stringList)
    {
        list.setListData(stringList);
    }
    public int getListIndex()
    {
        return list.getSelectedIndex();
    }


    public void addTestItems()
    {
        ArrayList<String> items = new ArrayList<>();

        for (int i=0; i < 10; i++)
        {
            listModel.addElement("Thread status -  line " + i);
        }
        for (String item : items)
            listModel.addElement(item);
    }

    public void setStatusText(String infoString)
    {
        listModel.addElement(infoString);
    }
    public void setStatusText(String infoString, boolean clearList)
    {
        if (clearList)
            listModel.clear();

        listModel.addElement(infoString);
    }
    public void clearLogbook()
    {
        listModel.clear();
    }
    public void updatePatientList(ArrayList<String> patientInfo)
    {
        listModel.clear();
        //reverse the order

        for (String str : patientInfo)
          listModel.addElement(str);
    }

}
