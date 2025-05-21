package textEditors.main;

import textEditors.model.*;
import textEditors.view.MainFrame;
import textEditors.view.MainPanel;
import textEditors.view.PanelSouth;

import java.io.File;
import java.util.List;


public class Controller
{
    private String[] sourceStrings;
    private MainFrame view;
    SharedBuffer sharedBuffer;


    public Controller() {
        view = new MainFrame(this);

    }
    
    public void execute( String[] lines, String find, String replace) {
        view.markWord(find);
        sharedBuffer = new SharedBuffer(sourceStrings,find,replace,this);

        int numberOfWriters = 3;
        for(int i = 0; i < numberOfWriters; i++){
            Writer writer = new Writer(sharedBuffer);
            writer.setName("Writer Thread nr. " + i);
        }

        int numberOfModifiers = 4;
        for(int i = 0; i < numberOfModifiers; i++){
            Modifier modifier = new Modifier(sharedBuffer, this);
            modifier.setName("Modifier Thread nr. " + i);
        }
        Reader reader = new Reader(sharedBuffer, this);
        reader.setName("Reader 1");
    }



    public void loadFileOnlyforTest() {
        FileManager fileManager = new FileManager(this);
        sourceStrings = fileManager.onLoadFile();

    }

    public void setStatusText(String text) {
        view.updateThreadsStatus(text);
    }

    public void setDestText(String text){ view.setDestText(text);}

    public void setSourceText(String[] lines) {
        view.setSourceText(lines);
    }

}

