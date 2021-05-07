/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2021
 * Instructor: Prof. Chris Dancy
 *
 * Name: Joseph Carluccio
 * Section: 1:50 - 2:40
 * Date: 4/28/21
 * Time: 2:20 PM
 *
 * Project: csci205SP21finalproject
 * Package: main
 * Class: Producer
 *
 * Description: This class is the structure for the producer,
 * providing all of the necessary variable for the game to function.
 *
 * ****************************************
 */
package main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class Producer implements Runnable {

    private String name;
    private long initialCost;
    private long initialGain;



    private long initialPeriod;
    private double costMult;
    private SimpleLongProperty costForNext;
    private SimpleStringProperty displayCostForNext;
    private Duration currentInterval;
    private Duration timeRemaining;

    private SimpleLongProperty totalGain;
    private SimpleDoubleProperty progress;
    private SimpleStringProperty timeProperty; //TODO change this to string, add an update/format for it
    private SimpleDoubleProperty dnaProduced;
    private SimpleStringProperty displayDnaProduced;







    private SimpleStringProperty displayTotalGain;
    private int numberPurchased;
    private double gainMult;
    private double periodMult;

    //TODO JAVA DOC
    public Producer(String name,
                    long initialCost,
                    long initialGain,
                    long initialPeriod,
                    double costMult) {
        this.name = name;
        this.initialCost = initialCost;
        this.initialGain = initialGain;
        this.initialPeriod = initialPeriod;
        this.costMult = costMult;
        this.costForNext = new SimpleLongProperty(this.initialCost);
        this.currentInterval = Duration.ofSeconds(initialPeriod);
        this.timeRemaining = this.currentInterval;
        this.timeProperty = new SimpleStringProperty(Double.toString(initialPeriod));
        this.totalGain = new SimpleLongProperty(0);
        this.numberPurchased = 0;
        this.gainMult = 1;
        this.periodMult = 1;

        this.dnaProduced = new SimpleDoubleProperty(0);
        this.displayDnaProduced = new SimpleStringProperty(this.dnaProduced.toString());
        this.displayDnaProduced.bind(this.dnaProduced.asString());

        this.displayCostForNext = new SimpleStringProperty(this.costForNext.toString());
        this.displayCostForNext.bind(this.costForNext.asString());

        this.displayTotalGain = new SimpleStringProperty(this.totalGain.toString());
        this.displayTotalGain.bind(this.totalGain.asString());

        this.progress = new SimpleDoubleProperty(0.0);

    }

    /**
     * method to update the values of producer
     */
    public void update()
    {}

    /**
     * getter for the gain of the producer
     * @param timerInterval - interval of time to be used in calculating the producer's total gain
     * @return totalGain - long value of the gain of the producer
     */
    public long getGain(Duration timerInterval){
        return totalGain.get();
    }


    //TODO JAVA DOC
    public void buy() {
        this.costForNext.set(this.costForNext.get() + 1);
        this.totalGain.set(this.totalGain.get() + 1);

    }


    @Override
    public void run() {
        long seconds = currentInterval.toSeconds();
        timeRemaining = Duration.ofSeconds(seconds);
        LocalDateTime timeInitialized = LocalDateTime.now();
        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
        while (true){
            this.timeProperty.setValue(Long.toString(this.timeRemaining.getSeconds()));
            this.progress.set(1 - (double)timeRemaining.toSeconds()/(double)currentInterval.toSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Time remaining: "+ timeRemaining + " seconds.");
            LocalDateTime timeNow = LocalDateTime.now();
            System.out.println(" It is now: " + timeNow);
            timeRemaining = Duration.between(timeNow, timeEnd);
            if (timeRemaining.isNegative()){
                timeRemaining = Duration.ofSeconds(seconds);
                timeNow = LocalDateTime.now();
                timeEnd = timeNow.plusSeconds(seconds);
                getGain(timeRemaining);
                dnaProduced.set(5 + dnaProduced.getValue());
                System.out.println(dnaProduced);
            }
        }
    }

    public SimpleStringProperty getDnaProduced() {
        return displayDnaProduced;
    }
    public double getProgress() {
        return progress.get();
    }
    public SimpleDoubleProperty progressProperty() {
        return progress;
    }
    public String getDisplayTotalGain() {
        return displayTotalGain.get();
    }
    public SimpleStringProperty displayTotalGainProperty() {
        return displayTotalGain;
    }
    public String getDisplayCostForNext() {
        return displayCostForNext.get();
    }
    public SimpleStringProperty displayCostForNextProperty() {
        return displayCostForNext;
    }
    public String getTimeProperty() {
        return timeProperty.get();
    }
    public SimpleStringProperty timePropertyProperty() {
        return timeProperty;
    }


}