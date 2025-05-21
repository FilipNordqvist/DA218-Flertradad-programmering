package model;

import controller.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer extends Thread{
    private boolean isRunning;

    AudioInputStream ais;

    File file;

    private Controller controller;

    Clip clip;

    public MusicPlayer(Controller controller){
        this.controller=controller;
    }

    @Override
    public void run() {
        try {
            ais = AudioSystem.getAudioInputStream(controller.getFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
            setRunning(true);
            while (isRunning)  {
                clip.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public void stopMusic() {
        setRunning(false);
        clip.stop();

    }
}
