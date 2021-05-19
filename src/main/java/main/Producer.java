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
    private SimpleStringProperty timeProperty;
    private SimpleStringProperty displayTotalGain;
    private SimpleLongProperty numberPurchased;
    private SimpleStringProperty displayNumberPurchased;
    private SimpleDoubleProperty gainMult;
    private SimpleDoubleProperty periodMult;
    private SimpleLongProperty mostRecentGain;
    private Color partColor;

    /**
     *
     * @param name
     * @param initialCost
     * @param initialGain
     * @param initialPeriod
     * @param costMult
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
        this.costMult = costMult;
        this.costForNext = new SimpleLongProperty(this.initialCost);

        this.initialGain = initialGain;
        this.totalGain = new SimpleLongProperty(0);
        this.gainMult = new SimpleDoubleProperty(1);

        this.initialPeriod = initialPeriod;
        this.currentInterval = Duration.ofSeconds(initialPeriod);
        this.timeRemaining = this.currentInterval;
        this.timeProperty = new SimpleStringProperty(Double.toString(initialPeriod));
        this.periodMult = new SimpleDoubleProperty(1);


        this.numberPurchased = new SimpleLongProperty(0);

        this.partColor = partColor;


        this.displayCostForNext = new SimpleStringProperty(this.costForNext.getValue().toString());
        this.displayCostForNext.bind(this.costForNext.asString());

        this.displayTotalGain = new SimpleStringProperty(this.totalGain.getValue().toString());
        this.displayTotalGain.bind(this.totalGain.multiply(this.gainMult).asString());

        this.displayNumberPurchased = new SimpleStringProperty((this.numberPurchased.getValue().toString()));
        this.displayNumberPurchased.bind(this.numberPurchased.asString());

        this.progress = new SimpleDoubleProperty(0.0);
        this.mostRecentGain = new SimpleLongProperty(0);

    }

    public double getDnaPerSecond(){
        double gain = 0.0;
        if(this.numberPurchased.get() != 0) {
            gain = ((double) (this.totalGain.get() * gainMult.get()) / (double) (this.currentInterval.toMillis() * periodMult.get())) * 1000.0;
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
     *
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
     *
     * @return
     */
    public double getProgress() {
        return progress.get();
    }

    /**
     *
     * @return
     */
    public SimpleDoubleProperty progressProperty() {
        return progress;
    }

    /**
     *
     * @return
     */
    public String getDisplayTotalGain() {
        return displayTotalGain.get();
    }

    /**
     *
     * @return
     */
    public SimpleStringProperty displayTotalGainProperty() {
        return displayTotalGain;
    }

    /**
     *
     * @return
     */
    public String getDisplayCostForNext() {
        return displayCostForNext.get();
    }

    /**
     *
     * @return
     */
    public SimpleStringProperty displayCostForNextProperty() {
        return displayCostForNext;
    }

    /**
     *
     * @return
     */
    public String getTimeProperty() {
        return timeProperty.get();
    }

    /**
     *
     * @return
     */
    public SimpleStringProperty timePropertyProperty() {
        return timeProperty;
    }

    /**
     *
     * @return
     */
    public long getInitialGain() {
        return initialGain;
    }

    public Color getPartColor() {
        return partColor;
    }

    public String getDisplayNumberPurchased() {
        return displayNumberPurchased.get();
    }

    public SimpleStringProperty displayNumberPurchasedProperty() {
        return displayNumberPurchased;
    }

    public long getNumberPurchased() {
        return numberPurchased.get();
    }

    public SimpleLongProperty numberPurchasedProperty() {
        return numberPurchased;
    }

    public void setNumberPurchased(long numberPurchased) {
        this.numberPurchased.set(numberPurchased);
    }

    public double getGainMult() {
        return gainMult.get();
    }

    public SimpleDoubleProperty gainMultProperty() {
        return gainMult;
    }

    public void setGainMult(double gainMult) {
        this.gainMult.set(gainMult);
    }

    public double getPeriodMult() {
        return periodMult.get();
    }

    public SimpleDoubleProperty periodMultProperty() {
        return periodMult;
    }

    public void setPeriodMult(double periodMult) {
        this.periodMult.set(periodMult);
    }

    public long getMostRecentGain() {
        return mostRecentGain.get();
    }

    public SimpleLongProperty mostRecentGainProperty() {
        return mostRecentGain;
    }

    public void setMostRecentGain(long mostRecentGain) {
        this.mostRecentGain.set(mostRecentGain);
    }

}