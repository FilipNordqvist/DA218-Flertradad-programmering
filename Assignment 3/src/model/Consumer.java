package model;

import controller.Controller;

import java.util.LinkedList;

public class Consumer extends Thread {

    Controller controller;
    Buffer buffer;
    int consumerListSize = 10;
    LinkedList<Product> consumerList;
    private int indexConsumerThread;
    boolean consumerIsRunning = true;


    public Consumer(Buffer buffer, Controller controller,int indexConsumerThread) {
        consumerList = new LinkedList<>();
        this.controller = controller;
        controller.setStatusConsumer(indexConsumerThread,"Not consuming");
        this.buffer = buffer;
        this.indexConsumerThread = indexConsumerThread;

    }


    public void run() {
        while (consumerIsRunning) {
            if(buffer.getProductsBuffer().size()== 0){
                controller.setStatusConsumer(indexConsumerThread,"Waiting");
                consumerIsRunning = false;
            }

            try {
                Product product = buffer.Consume();

                consumerList.add(product);

                System.out.println("Consumed: " + product);
                controller.updateProgressBar(buffer.getBufferSize());
                controller.amountOfItems(indexConsumerThread,consumerList.size());
                controller.setStatusConsumer(indexConsumerThread,"Consuming");
                controller.listFoodItems(indexConsumerThread, product.name);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(controller.continuesLoading(indexConsumerThread)){

                while(consumerList.size() == consumerListSize){
                    consumerList.clear();
                    controller.clearGUIWindow(indexConsumerThread);
                    //consumerList.remove(consumerList.size() - 1);;
                    controller.setStatusConsumer(indexConsumerThread,"Emptying");

                }

                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }



    public void setConsumerIsRunning( boolean b){
        consumerIsRunning = b;
    }
}

