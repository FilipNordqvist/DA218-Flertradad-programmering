package model;

import controller.Controller;

import java.util.Random;

public class Producer extends Thread {

    private Controller controller;
    final int max = 50;
    Product[] itemArray = new Product[max];
    Random random = new Random();
    Product product;
    Buffer buffer;
    boolean producerIsRunning = true;

    private int indexProducerThread;

    public Producer(Buffer buffer, Controller controller,int indexProducerThread){
        this.buffer = buffer;
        this.controller=controller;
        controller.setStatusProducer(indexProducerThread,"Idle");
        this.indexProducerThread=indexProducerThread;
        CreateTestProducts();
    }

    public void run() {
        while (producerIsRunning) {
            int index = random.nextInt(itemArray.length);
            product = itemArray[index];

            try {
                buffer.Produce(product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Produced: " + product);
            controller.setStatusProducer(indexProducerThread, "Producing");
            controller.updateProgressBar(buffer.getBufferSize());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        controller.setStatusProducer(indexProducerThread,"Stopped");
    }
    public void setProducerIsRunning(boolean b){
        producerIsRunning = b;
    }

    public void CreateTestProducts() {
        for (int i = 0; i < 5; i++)
        {

            int j = i * 10;

            itemArray[0 + j] = new Product("Milk, Gen Food", 1.0, 6, CategoryType.Food);

            itemArray[1 + j] = new Product("Egg, organic", 5.0, 20, CategoryType.Food);

            itemArray[2 + j] = new Product("Dish Washer", 15, 1, CategoryType.Electronics);

            itemArray[3 + j] = new Product("Shaving machine", 200, 2, CategoryType.Electronics);

            itemArray[4 + j] = new Product("Screwdriver machine", 780, 3, CategoryType.Tools);

            itemArray[5 + j] = new Product("Tigersaw", 450,  3, CategoryType.Tools);

            itemArray[6 + j] = new Product("Milk, Gen Food", 1.0, 6, CategoryType.Food);

            itemArray[7 + j] = new Product("Egg Gen Food", 5.0, 20, CategoryType.Food);

            itemArray[8 + j] = new Product("Dish Washer, Global El", 15, 1, CategoryType.Electronics);

            itemArray[9 + j] = new Product("Shaving machine, Home Tools", 200, 2, CategoryType.Electronics);

        }
    }
}
