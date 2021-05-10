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
    private SimpleStringProperty displayTotalGain;

    public int getNumberPurchased() {
        return numberPurchased;
    }

    private int numberPurchased;
    private double gainMult;
    private double periodMult;

    public long getMostRecentGain() {
        return mostRecentGain.get();
    }

    public SimpleLongProperty mostRecentGainProperty() {
        return mostRecentGain;
    }

    public void setMostRecentGain(long mostRecentGain) {
        this.mostRecentGain.set(mostRecentGain);
    }

    private SimpleLongProperty mostRecentGain;

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
        this.periodMult = .5;



        this.displayCostForNext = new SimpleStringProperty(this.costForNext.getValue().toString());
        this.displayCostForNext.bind(this.costForNext.asString());



        this.displayTotalGain = new SimpleStringProperty(this.totalGain.getValue().toString());
        this.displayTotalGain.bind(this.totalGain.asString());


        this.progress = new SimpleDoubleProperty(0.0);
        this.mostRecentGain = new SimpleLongProperty(0);

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
    public long buy(Long DNA) {

        //Check for enough DNA
        if(DNA < this.costForNext.get()){
            return -1;
        }

        //If its the first one then it starts the thread

//        if(this.numberPurchased == 0){
//            this.run();
//        }
        //Update stuff
        long cost = this.costForNext.get();
        this.costForNext.set((long)(this.costForNext.get() * this.costMult));

        this.totalGain.set((this.totalGain.get() + this.initialGain));

        this.numberPurchased += 1;

        if(this.numberPurchased == 5 || this.numberPurchased == 10 || (this.numberPurchased % 25) == 0){
            this.currentInterval = Duration.ofMillis((long)(this.currentInterval.toMillis() * periodMult));
        }
        System.out.println(this.currentInterval.toMillis());
        return cost;

    }


    @Override
    public void run() {
        long seconds = currentInterval.toSeconds();
        timeRemaining = Duration.ofSeconds(seconds);
        LocalDateTime timeInitialized = LocalDateTime.now();
        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
        while (true){
            this.timeProperty.setValue(Long.toString(this.timeRemaining.toMillis()));
            this.progress.set(1 - (double)timeRemaining.toMillis()/(double)currentInterval.toMillis());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.print("Time remaining: "+ timeRemaining + " seconds.");
            LocalDateTime timeNow = LocalDateTime.now();
//            System.out.println(" It is now: " + timeNow);
            timeRemaining = Duration.between(timeNow, timeEnd);
            if (timeRemaining.isNegative()){
                timeRemaining = Duration.ofMillis(currentInterval.toMillis());
                timeNow = LocalDateTime.now();
                timeEnd = timeNow.plusNanos(currentInterval.toNanos());
                this.mostRecentGain.set(this.totalGain.get());
                this.mostRecentGain.set(0);
            }
        }
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


    public long getInitialGain() {
        return initialGain;
    }
}