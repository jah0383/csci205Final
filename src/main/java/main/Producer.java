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

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class Producer implements Runnable {

    private String name;
    private long initialCost;
    private long initialGain;



    private double initialPeriod;
    private double costMult;
    private long costForNext;
    private Duration currentInterval;
    private Duration timeRemaining;
    private SimpleLongProperty timeProperty;
    private long totalGain;
    private int numberPurchased;
    private double gainMult;
    private double periodMult;

    //TODO JAVA DOC
    public Producer(String name,
                    long initialCost,
                    long initialGain,
                    double initialPeriod,
                    double costMult) {
        this.name = name;
        this.initialCost = initialCost;
        this.initialGain = initialGain;
        this.initialPeriod = initialPeriod;
        this.costMult = costMult;
        this.costForNext = this.initialCost;
        this.currentInterval = Duration.ofSeconds((long)initialPeriod);
        this.timeRemaining = this.currentInterval;
        this.timeProperty = new SimpleLongProperty((long)initialPeriod);
        this.totalGain = 0;
        this.numberPurchased = 0;
        this.gainMult = 1;
        this.periodMult = 1;
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
        return totalGain;
    }

    //TODO JAVA DOC
//    public void run(int seconds) throws InterruptedException {
//        timeRemaining = Duration.ofSeconds(seconds);
//        LocalDateTime timeInitialized = LocalDateTime.now();
//        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
//        while (true){
//            this.timeProperty.setValue(this.timeRemaining.getSeconds());
//            System.out.println(this.getTimeProperty());
//            Thread.sleep(1000);
//            System.out.print("Time remaining: "+ timeRemaining + " seconds.");
//            LocalDateTime timeNow = LocalDateTime.now();
//            System.out.println(" It is now: " + timeNow);
//            timeRemaining = Duration.between(timeNow, timeEnd);
//            if (timeRemaining.isNegative()){
//                timeRemaining = Duration.ofSeconds(seconds);
//                timeNow = LocalDateTime.now();
//                timeEnd = timeNow.plusSeconds(seconds);
//                getGain(timeRemaining);
//            }
//        }
//    }

    public void runTest(int seconds){
        this.timeProperty.setValue(this.timeProperty.get()+1);
//        timeRemaining = Duration.ofSeconds(seconds);
//
//
//        LocalDateTime timeInitialized = LocalDateTime.now();
//        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
//        while (true){
//            this.timeProperty.set(this.timeRemaining.getNano());
//            System.out.println(this.getTimeProperty());
//            System.out.print("Time remaining: "+ timeRemaining + " seconds.");
//            LocalDateTime timeNow = LocalDateTime.now();
//            System.out.println(" It is now: " + timeNow);
//            timeRemaining = Duration.between(timeNow, timeEnd);
//            if (timeRemaining.isNegative()){
//                timeRemaining = Duration.ofSeconds(seconds);
//                timeNow = LocalDateTime.now();
//                timeEnd = timeNow.plusSeconds(seconds);
//                getGain(timeRemaining);
//                break;
//            }
//        }
    }



    public long getTimeProperty() {
        return this.timeProperty.get();
    }

    public SimpleLongProperty timePropertyProperty() {
        return this.timeProperty;
    }

    @Override
    public void run() {
        int seconds = 5;
        timeRemaining = Duration.ofSeconds(seconds);
        LocalDateTime timeInitialized = LocalDateTime.now();
        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
        while (true){
            this.timeProperty.setValue(this.timeRemaining.getSeconds());
            System.out.println(this.getTimeProperty());
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
            }
        }
    }


//    public static void main(String[] args) throws InterruptedException {
//        Producer newCell = new Producer();
//        newCell.run(5);
//    }


}