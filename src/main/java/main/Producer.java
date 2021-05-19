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
import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDateTime;

public class Producer implements Runnable {

    private String name;

    /**
     * long value of the initial (first) cost of the producer
     */
    private long initialCost;

    /**
     * long value of the initial (first) gain of the cost of the producer
     */
    private long initialGain;

    private long initialPeriod;

    /**
     * double value for the cost multiplier of the producer
     */
    private double costMult;

    /**
     * Value for the cost of the next purchase of the same producer
     */
    private SimpleLongProperty costForNext;

    /**
     * Value for the cost of the next purchase of the same producer in string format
     */
    private SimpleStringProperty displayCostForNext;

    /**
     * Duration for the time it takes the producer to produce
     */
    private Duration currentInterval;

    /**
     * Duration for the time remaining until the producer produces
     */
    private Duration timeRemaining;

    /**
     * total gain for the producer
     */
    private SimpleLongProperty totalGain;

    /**
     * current progress towards the next production
     */
    private SimpleDoubleProperty progress;

    /**
     * String for the time left of the producer's production
     */
    private SimpleStringProperty timeProperty; //TODO change this to string, add an update/format for it

    /**
     * String for the total gain of the producer to be displayed
     */
    private SimpleStringProperty displayTotalGain;

    /**
     * the total number of a producer owned
     */
    private SimpleLongProperty numberPurchased;

    /**
     * String for the total number purchased for display
     */
    private SimpleStringProperty displayNumberPurchased;

    /**
     * gain multiplier for the producer
     */
    private SimpleDoubleProperty gainMult;

    /**
     * multiplier for the period of production for the producer
     */
    private SimpleDoubleProperty periodMult;


    /**
     * Getter that returns the number purchased of a producer
     * @return numberPurchased.get() - the amount purchased of a single producer
     */
    public long getNumberPurchased() {
        return numberPurchased.get();
    }

    /**
     * getter for the number purchased of a producer
     * @return numberPurchased - long value of the number of a producer purchased
     */
    public SimpleLongProperty numberPurchasedProperty() {
        return numberPurchased;
    }

    /**
     * setter for the number pruchased of the producer
     * @param numberPurchased - long value for numberPurchased to be set to
     */
    public void setNumberPurchased(long numberPurchased) {
        this.numberPurchased.set(numberPurchased);
    }

    /**
     * getter for the gain multiplier of the producer
     * @return gainMult.get() - double value for the gain multiplier of the producer
     */
    public double getGainMult() {
        return gainMult.get();
    }

    /**
     * returns the gain multiplier of the producer
     * @return gainMult - the gian multiplier of the producer
     */
    public SimpleDoubleProperty gainMultProperty() {
        return gainMult;
    }

    /**
     * setter for the gain multiplier of the producer
     * @param gainMult - double value for gainMult to be set to
     */
    public void setGainMult(double gainMult) {
        this.gainMult.set(gainMult);
    }

    /**
     * getter for the period multiplier of a producer
     * @return periodMult.get() - double value for the period multiplier of the producer
     */
    public double getPeriodMult() {
        return periodMult.get();
    }

    /**
     * returns the period multiplier of the producer
     * @return periodMult - double of the period multiplier of the producer
     */
    public SimpleDoubleProperty periodMultProperty() {
        return periodMult;
    }

    /**
     * setter for the period multiplier of the producer
     * @param periodMult - double value for periodMult to be set to
     */
    public void setPeriodMult(double periodMult) {
        this.periodMult.set(periodMult);
    }

    /**
     * getter for the most recent gain of the producer
     * @return mostRecentGain.get() - long alue of the most recent gain
     */
    public long getMostRecentGain() {
        return mostRecentGain.get();
    }

    /**
     * returns the most recent gain value
     * @return mostRecentGain - the most recent gain value
     */
    public SimpleLongProperty mostRecentGainProperty() {
        return mostRecentGain;
    }

    /**
     * sets the most recent gain
     * @param mostRecentGain - long value for mostRecentGain to be set to
     */
    public void setMostRecentGain(long mostRecentGain) {
        this.mostRecentGain.set(mostRecentGain);
    }

    /**
     * the most recent gain of the producer
     */
    private SimpleLongProperty mostRecentGain;

    /**
     * color for the appearing dots in the body visual
     */
    private Color partColor;

    private volatile boolean shutdown = false;

    /**
     * Constructor that sets the initial values for the producer
     * @param name - String value for name of the producer
     * @param initialCost - long value for the initial cost of the producer
     * @param initialGain - long value for the initial gain of the producer
     * @param initialPeriod - long value for the initial period of production of the producer
     * @param costMult - double value for the cost multiplier of the producer
     */
    //TODO JAVA DOC
    public Producer(String name,
                    long initialCost,
                    long initialGain,
                    long initialPeriod,
                    double costMult,
                    Color partColor) {
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
        this.numberPurchased = new SimpleLongProperty(0);
        this.gainMult = new SimpleDoubleProperty(1);
        this.periodMult = new SimpleDoubleProperty(1);

        this.partColor = partColor;



        this.displayCostForNext = new SimpleStringProperty(this.costForNext.getValue().toString());
        this.displayCostForNext.bind(this.costForNext.asString());



        this.displayTotalGain = new SimpleStringProperty(this.totalGain.getValue().toString());
        this.displayTotalGain.bind(this.totalGain.asString());

        this.displayNumberPurchased = new SimpleStringProperty((this.numberPurchased.getValue().toString()));
        this.displayNumberPurchased.bind(this.numberPurchased.multiply(this.gainMult).asString());


        this.progress = new SimpleDoubleProperty(0.0);
        this.mostRecentGain = new SimpleLongProperty(0);

    }

    /**
     * gets the current DNA per second from the gain
     *
     * calculates the current DNA per second from the gain
     *
     * @return Math.round(gain) - the rounded value of gain
     */
    public double getDnaPerSecond(){
        double gain = 0.0;
        if(this.numberPurchased.get() != 0) {
            gain = ((double) this.totalGain.get() / (double) this.currentInterval.toMillis()) * 1000.0;
        }
        return Math.round(gain);
    }


    /**
     * Sets up everything so that the labels show the correct information on startup
     * @author James Howe
     */
    public void setInitialDisplay() {
        this.costForNext.set(0);
        this.costForNext.set(this.initialCost);

        this.totalGain.set(0);
        this.totalGain.set(this.initialGain);

        this.timeProperty.set(Long.toString(this.currentInterval.toMillis()));
    }



    /**
     * increases the costForNext and totalGain upon buying a producer
     * @author James Howe
     */
    //TODO JAVA DOC
    public long buy(Long DNA) {

        //Check for enough DNA
        if(DNA < this.costForNext.get()){
            return -1;
        }

        //If its the first one then it Doesn't update the gain

        if(this.numberPurchased.get() == 0){
            this.totalGain.set((this.totalGain.get() - this.initialGain));
        }

        //Update stuff
        long cost = this.costForNext.get();
        this.costForNext.set((long)(this.costForNext.get() * this.costMult));

        this.totalGain.set((this.totalGain.get() + this.initialGain));

        this.numberPurchased.set(this.numberPurchased.get() + 1);

        if(this.numberPurchased.get() == 5 || this.numberPurchased.get() == 10 || (this.numberPurchased.get() % 25) == 0){
            this.currentInterval = Duration.ofMillis((long)(this.currentInterval.toMillis() * .5));
        }
        return cost;

    }


    /**
     * begins the producer's production
     */
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
            LocalDateTime timeNow = LocalDateTime.now();
            timeRemaining = Duration.between(timeNow, timeEnd);
            if (timeRemaining.isNegative()){
                timeRemaining = Duration.ofMillis(Math.round(currentInterval.toNanos() * this.periodMult.get()));
                timeNow = LocalDateTime.now();
                timeEnd = timeNow.plusNanos(Math.round(currentInterval.toNanos() * this.periodMult.get()));
                this.mostRecentGain.set(Math.round(this.totalGain.get() * this.gainMult.get()));
                this.mostRecentGain.set(0);
            }
        }
    }


    /**
     * getter for the progress of the producer
     * @return progress.get() - double value of the current progress of the producer
     */
    public double getProgress() {
        return progress.get();
    }

    /**
     * returns progress of the producer
     * @return progress - the current progress of the producer
     */
    public SimpleDoubleProperty progressProperty() {
        return progress;
    }

    /**
     * getter for the displayed string value of the total gain of the producer
     * @return displayTotalGain.get() - String of the total gain of the producer
     */
    public String getDisplayTotalGain() {
        return displayTotalGain.get();
    }

    /**
     * returns the displayTotalGain string property
     * @return displayTotalGain - String of the total gain of the producer
     */
    public SimpleStringProperty displayTotalGainProperty() {
        return displayTotalGain;
    }

    /**
     * getter for the String value of the cost for next of the producer
     * @return displayCostForNext.get() - String of cost for the next purchase of the producer
     */
    public String getDisplayCostForNext() {
        return displayCostForNext.get();
    }

    /**
     *  returns the display value for the cost for the next purchase of a producer
     * @return displayCostForNext - string of the cost for the next purchase of a producer
     */
    public SimpleStringProperty displayCostForNextProperty() {
        return displayCostForNext;
    }

    /**
     * getter for the String value of the time property of the producer
     * @return timeProperty.get() - String of the timeProperty of the producer
     */
    public String getTimeProperty() {
        return timeProperty.get();
    }

    /**
     * returns the display value for the time of the producer
     * @return timeProperty - a string of the producer's time proerty
     */
    public SimpleStringProperty timePropertyProperty() {
        return timeProperty;
    }

    /**
     * getter for the initial gain of the producer
     * @return initialGain - long value for the initial gain of the producer
     */
    public long getInitialGain() {
        return initialGain;
    }

    /**
     * getter for the part color of the producer (color of the appearing dots in the visual)
     * @return partColor - Color of the dots
     */
    public Color getPartColor() {
        return partColor;
    }

    /**
     * getter for the display value of the number purchased of a producer
     * @return displayNumberPurchased.get() - string of the number purchased to be displayed
     */
    public String getDisplayNumberPurchased() {
        return displayNumberPurchased.get();
    }

    /**
     * returns the display for the number purchased of the producer
     * @return displayNumberPurchased - number of a kind of producer purchased to be displayed
     */
    public SimpleStringProperty displayNumberPurchasedProperty() {
        return displayNumberPurchased;
    }

}