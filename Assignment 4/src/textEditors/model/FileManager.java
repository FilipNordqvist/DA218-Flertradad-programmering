package textEditors.model;

import textEditors.main.Controller;
import textEditors.view.MainPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManager


{

    Controller controller;
    public FileManager(Controller controller) {
        this.controller=controller;
    }

    public String[] onLoadFile()
    {
        String[] lines = new String[0];
        JFileChooser chooser = new JFileChooser();
        File file = null;
        try
        {
            file = new File(new File(".").getCanonicalPath());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        chooser.setCurrentDirectory(file);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text", "txt");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                lines = Files.readAllLines(f.toPath(), Charset.forName("UTF-8")).toArray(new String[0]);
                controller.setSourceText(lines);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return lines;
    }

}
