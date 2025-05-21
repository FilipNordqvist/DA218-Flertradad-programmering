package controller;
//import model.*;
import model.Consumer;
import model.Producer;
import model.Buffer;
import view.*;

public class Controller {
    Consumer consumer;
    Producer producer;
    private View view;
    private Buffer buffer;
    private String statusConsumer;
    private String statusProducer;
    private PanelConsumers panelConsumers = new PanelConsumers(this);
    private PanelProducers panelProducers;
    private Consumer[] consumers = new Consumer[3];
    private Producer[] producers = new Producer[3];
    private final int maxBufferSize = 50;

    public Controller() {
        buffer = new Buffer(this);
        setup();
        createThreads();
        // create and setup producers, consumers, main view and start it
    }

    private void setup() {
        view = new View(this);
        view.Start();
    }

    private void createThreads() {
        for (int i = 0; i < consumers.length; i++) {
            consumer = new Consumer(buffer, this, i);
            consumers[i] = consumer;
        }

        for (int i = 0; i < producers.length; i++) {
            producer = new Producer(buffer, this, i);
            producers[i] = producer;
        }
    }

    public void startConsumer(int index) {
        consumer = new Consumer(buffer, this, index);
        consumers[index] = consumer;
        consumers[index].start();

    }

    public void stopConsumer(int index) {
        consumers[index].setConsumerIsRunning(false);

    }

    public void startProducer(int index) {
        producer = new Producer(buffer, this, index);
        producers[index] = producer;
        producers[index].start();
    }

    public void stopProducer(int index) {
        producers[index].setProducerIsRunning(false);
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public synchronized void updateProgressBar(int bufferSize) {
        view.updateProgressbar(bufferSize);
    }

    public String getStatusConsumer() {
        return statusConsumer;
    }

    public String getStatusProducer() {
        return statusConsumer;
    }

    public void setStatusProducer(int index, String statusProducer) {

        String foodStore = "";
        if (index == 0) {
            foodStore = "Scan";
        } else if (index == 1) {
            foodStore = "Arla";
        } else {
            foodStore = "Axfood";
        }
        view.setProducerStatus(foodStore, statusProducer);
    }

    public void setStatusConsumer(int index, String statusConsumer) {
        String foodStore = "";
        if (index == 0) {
            foodStore = "ICA";
        } else if (index == 1) {
            foodStore = "Coop";
        } else {
            foodStore = "City Gross";
        }
        view.setConsumerStatus(foodStore, statusConsumer);
    }


    public boolean continuesLoading(int index) {
        String foodStore = "";
        if (index == 0) {
            foodStore = "ICA";
        } else if (index == 1) {
            foodStore = "Coop";
        } else {
            foodStore = "City Gross";
        }
        return view.getContinueLoading(foodStore);
    }

    public void listFoodItems(int index, String foodName) {
        String foodStore = "";
        if (index == 0) {
            foodStore = "ICA";
        } else if (index == 1) {
            foodStore = "Coop";
        } else {
            foodStore = "City Gross";
        }
        view.updateFoodList(foodName, foodStore);
    }


    public void updateFoodList(String foodItem, String name) {
        panelConsumers.updateProductList(foodItem, name);
    }

    public void amountOfItems(int type, int amount) {
        view.updateConInfo(type, amount);
    }


    public void clearGUIWindow(int index) {
        String foodStore = "";
        if (index == 0) {
            foodStore = "ICA";

        } else if (index == 1) {
            foodStore = "Coop";
        } else {
            foodStore = "City Gross";
        }
        panelConsumers.clearCargoList(foodStore);
    }
}
