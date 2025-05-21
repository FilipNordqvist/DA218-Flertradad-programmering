package textEditors.model;

import textEditors.main.Controller;

public class SharedBuffer {

    public int bufferSize = 20;

    boolean modified;
    String find;
    String replace;
    Controller controller;
    private int writePos = 0;
    private int readPos = 0;
    private int findPos = 0;
    private String[] textArray;
    private BufferStatus[] statusArray;
    private String[] textToWrite;


    public SharedBuffer(String[] textToWrite, String find, String replace, Controller controller) {
        this.textToWrite = textToWrite;
        this.find = find;
        this.replace = replace;
        this.controller = controller;
        textArray = new String[bufferSize];
        statusArray = new BufferStatus[bufferSize];

        for (int i = 0; i < statusArray.length; i++) {
            statusArray[i] = BufferStatus.Empty;
        }

    }

    public synchronized void write() throws InterruptedException {
        while (statusArray[writePos] != BufferStatus.Empty || writePos >= textToWrite.length) {
            wait();
        }

        textArray[writePos] = textToWrite[writePos];
        controller.setStatusText(Thread.currentThread().getName() + " Wrote: " + textArray[writePos] + " to sharedBuffer position: " + writePos);

        statusArray[writePos] = BufferStatus.New;

        writePos = ((writePos + 1) % bufferSize); // increase where to write next time

        notifyAll(); // notify threads that something is written in the buffer


    }


    public synchronized String read() throws InterruptedException {

        while (statusArray[readPos] != BufferStatus.Checked) {
            wait(); //If there isnt new data to read wait.
        }

        int checkedPosition = findCheckedPosition();

        String data = textArray[readPos];

        controller.setStatusText(Thread.currentThread().getName() + " read " + data + " to sharedBuffer position: " + readPos);

        statusArray[readPos] = BufferStatus.Empty;

        readPos = (readPos + 1) % bufferSize; // increase where to read next time

        notifyAll();

        return data;
    }



    public synchronized void modify() throws InterruptedException {

        while (statusArray[findPos] != BufferStatus.New) {
            wait();
        }

        if(textArray[findPos] == null){
            modified = true;
        }else if(textArray[findPos].contains(find)){
            controller.setStatusText("Modifier thread replaced" + find +
                    "with" + replace + "in position: " + findPos);
            textArray[findPos] = textArray[findPos].replace(find, replace);
        }

        statusArray[findPos] = BufferStatus.Checked;
        findPos = (findPos + 1) % textArray.length;

        notifyAll();

        }



    private int findCheckedPosition() {
        for (int i = 0; i < bufferSize; i++) {
            if (statusArray[i] == BufferStatus.Checked) {
                return i;
            }
        }
        throw new IllegalStateException("Error");
    }

    public String getFind() {
        return find;
    }

    public String getReplace() {
        return replace;
    }

    public boolean getModified() {
        return modified;
    }
}

