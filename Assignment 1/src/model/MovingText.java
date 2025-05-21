package model;

import controller.Controller;

import java.util.Random;

/**
 * Is a class that move the text label around in the box in the GUI.
 */
public class MovingText extends Thread {

    Controller controller;
    private int x, y;
    private boolean isRunning;

    private String text;

    public MovingText(Controller controller) {
        this.controller = controller;
        this.text = TaskType.DisplayText.name();
        this.x = 10;
        this.y = 10;


    }

    /**
     * Method that generate a random position for the text.
     */
    public void update() {
        Random rand = new Random();
        x = rand.nextInt(150);
        y = rand.nextInt(150);

    }

    /**
     * @param running if it is true the thread will go on if it is false it will stop the thread.
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * This is my thread, when started it will set the text in the left corner and call the update method to move the text around.
     */
    @Override
    public void run() {
        while (isRunning) {
            controller.setDisplayText(text, x, y);
            update();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
