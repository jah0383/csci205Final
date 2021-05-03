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

import java.time.Duration;
import java.time.LocalDateTime;

public class Producer {

    private String name;
    private long initialCost;
    private long initialGain;
    private double initialPeriod;
    private double costMult;
    private long costForNext;
    private Duration currentInterval;
    private Duration timeRemaining;
    private long totalGain;
    private int numberPurchased;
    private double gainMult;
    private double periodMult;


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

    public void run(int seconds) throws InterruptedException {
        timeRemaining = Duration.ofSeconds(seconds);
        LocalDateTime timeInitialized = LocalDateTime.now();
        LocalDateTime timeEnd = timeInitialized.plusSeconds(seconds);
        while (true){
            Thread.sleep(1000);
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

    public static void main(String[] args) throws InterruptedException {
        Producer newCell = new Producer();
        newCell.run(5);
    }


}