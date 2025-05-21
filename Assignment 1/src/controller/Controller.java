package controller;
import view.*;
import model.*;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Controller {
    private MainFrame mainFrame;

    private boolean isRunning;

    private File file;

    MovingText movingText;

    MusicPlayer musicPlayer;

    Clip clip;

    public Controller(){
        mainFrame = new MainFrame(this);
        mainFrame.Start();
    }

    public void setDisplayText(String txt, int x, int y) {
        mainFrame.setDisplayText(txt,x,y);
    }

    public void drawTriangle(Polygon p) {
        //mainFrame.drawTriangle(p);
    }

      public void setRunning(boolean running) {
            isRunning = running;
        }

     /**
     * When the start button in the GUI is pressed this method is called.
     *
     * This Method starts a new Thread.
     */
    public void startMusic(){
       musicPlayer = new MusicPlayer(this);
       musicPlayer.setRunning(true);
       musicPlayer.start();
    }

    /**
     * When Stop is pressed in the GUI this method is called.
     *
     * This method close the ais and stop the sound and stops the thread.
     */
    public void stopMusic(){
        musicPlayer.stopMusic();
    }


    public void chooseMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        file = new File(path);
        setFile(file);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    /**
     * This method is called from the controller when start is pressed in the GUI
     *
     * the setRunning is a boolean that sets it to true or false to start and stop the thread in the class.
     *
     * @param taskType Enum
     */
    public void start(TaskType taskType) {
        movingText = new MovingText(this);
        movingText.setRunning(true);
        movingText.start();

    }

    /**
     * This method stops the text from moving in the box by setting the isRunning to false that will stop the thread.
     *
     * @param taskType Enum
     */
    public void stop(TaskType taskType) {
        movingText.setRunning(false);
    }

  }
