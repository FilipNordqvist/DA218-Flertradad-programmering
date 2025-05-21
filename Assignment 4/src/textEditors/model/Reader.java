package textEditors.model;

import textEditors.main.Controller;

public class Reader extends Thread{

    SharedBuffer sharedBuffer;
    Controller controller;
    private String readText;
    private int size;
    private String status;

    public Reader(SharedBuffer sharedBuffer, Controller controller){
        this.sharedBuffer=sharedBuffer;
        this.controller=controller;
        start();

    }


    public void run() {
        while (true) {
            try {
                readText = sharedBuffer.read();
                controller.setDestText(readText);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
