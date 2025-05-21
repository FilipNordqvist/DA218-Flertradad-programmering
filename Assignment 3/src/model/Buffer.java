package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {

    Controller controller;
    private final int bufferSize = 50;
    int indexToRemove = 0;

    private ArrayList<Product> productsBuffer = new ArrayList<>(bufferSize);
    Semaphore producerSemaphore = new Semaphore(bufferSize); //counting empty slots (to write)
    Semaphore consumerSemaphore = new Semaphore(0); //counting full slots (to read and remove)

    public Buffer(Controller controller){
        this.controller = controller;

    }


    public void Produce(Product product) throws InterruptedException {
        producerSemaphore.acquire();

        synchronized (productsBuffer){

            while(isFull()){
                wait();
            }
            productsBuffer.add(product);
        }

        consumerSemaphore.release();
    }

    public synchronized Product Consume() throws InterruptedException {
        consumerSemaphore.acquire();
        Product product = null;

        synchronized (productsBuffer) {
            while (isEmpty()) {
                wait();
            }
            product = productsBuffer.remove(indexToRemove);

            producerSemaphore.release();
            return product;
        }
    }

    public int getBufferSize (){
        return productsBuffer.size();

    }

    public boolean isFull(){
        return productsBuffer.size() >= bufferSize;
    }


    public boolean isEmpty(){
        return productsBuffer.isEmpty();
    }

    public ArrayList<Product> getProductsBuffer() {
        return productsBuffer;
    }//bounded buffer
}
