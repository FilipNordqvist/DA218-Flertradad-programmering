package textEditors.model;

import textEditors.main.Controller;

import java.util.ArrayList;
import java.util.List;

public class Writer extends Thread{

    SharedBuffer sharedBuffer;
    List<String> listOfLines;



    public Writer(SharedBuffer sharedBuffer){
        this.sharedBuffer=sharedBuffer;
        start();

    }

    @Override
    public void run() {

        while(true){
            try {
                sharedBuffer.write();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
