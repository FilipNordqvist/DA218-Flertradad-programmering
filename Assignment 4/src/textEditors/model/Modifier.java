package textEditors.model;

import textEditors.main.Controller;
import textEditors.view.MainPanel;

public class Modifier extends Thread {
    SharedBuffer sharedBuffer;
    Controller controller;
    String find;
    String replace;




    public Modifier(SharedBuffer sharedBuffer, Controller controller) {
        this.sharedBuffer=sharedBuffer;
        this.controller = controller;
        start();

    }

    @Override
    public void run() {

        while(!sharedBuffer.getModified()){
            try {
                if(sharedBuffer.getFind() != null && sharedBuffer.getReplace() != null){
                    Thread.sleep(1000);
                    sharedBuffer.modify();
                }else{
                    currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


