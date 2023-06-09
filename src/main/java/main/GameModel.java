package main;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class serves as the model for the incremental game,
 * this class holds the current state of the game and handles the logic
 */
public class GameModel {



    /**
     * total DNA count of the game
     */
    private SimpleLongProperty totalDNA;

    /**
     * current total gain of the game
     */
    private SimpleDoubleProperty totalGain;
    /**
     * The current date and time to be used by the game
     */
    private LocalDateTime currentTime;
    /**
     * String of the file path the user wants the game to be saved at
     */
    private String saveDataFilePath;



    /**
     * The list of producers
     */
    public ArrayList<Producer> producers;



    public ArrayList<Upgrade> upgrades;

    /**
     * The current buy mode of the game (1x/10x/100x/MAX)
     */
    public BuyMode buyMode;

    /**
     * boolean if the program is mutted or not
     */
    private SimpleBooleanProperty muted;

    /**
     * Constructor that initializes the producers that the user is able to purchase
     */
    public GameModel(){
        this.totalDNA = new SimpleLongProperty(0);
        this.totalGain = new SimpleDoubleProperty(0.0);
        this.buyMode = BuyMode.ONE;
        this.muted = new SimpleBooleanProperty(false);
        this.producers = new ArrayList<>();
        this.producers.add(new Producer("P1",0,100,1,1, Color.LIGHTGREEN));
        this.producers.add(new Producer("P2",500,25,3,1.1, Color.BLUE));
        this.producers.add(new Producer("P3",2000,150,10,1.4, Color.MAGENTA));
        this.producers.add(new Producer("P4",4000,400,20,1.5, Color.YELLOWGREEN));
        this.producers.add(new Producer("P5",6000,1000,40,1.6, Color.GRAY));
        this.producers.add(new Producer("P6",10000,9000,60,1.7, Color.LIGHTCORAL));

        this.upgrades = new ArrayList<>();
        this.upgrades.add(new Upgrade("P1 Upgrades", this.producers.get(0)));
        this.upgrades.add(new Upgrade("P2 Upgrades", this.producers.get(1)));
        this.upgrades.add(new Upgrade("P3 Upgrades", this.producers.get(2)));
        this.upgrades.add(new Upgrade("P4 Upgrades", this.producers.get(3)));
        this.upgrades.add(new Upgrade("P5 Upgrades", this.producers.get(4)));
        this.upgrades.add(new Upgrade("P6 Upgrades", this.producers.get(5)));
    }


    //TODO - method that calculate the maximum amount of a producer the user can buy
    public int calcMaxBuy(){
        return 1;

    }

    /**
     * gets the total DNA of the program
     * @return totalDNA.get() - the current total DNA of the program
     */
    public long getTotalDNA() {
        return totalDNA.get();
    }

    /**
     * getter for the total DNA of the program
     * @return totalDNA - the total DNA that the user has
     */
    public SimpleLongProperty totalDNAProperty() {
        return totalDNA;
    }

    /**
     * sets the total DNA of the program
     * @param totalDNA - long value for totalDNA to be set to
     */
    public void setTotalDNA(long totalDNA) {
        this.totalDNA.set(totalDNA);
    }

    /**
     * checks and returns the program's muted boolean property
     * @return muted.get() - boolean value of if the program is muted or not
     */
    public boolean isMuted() {
        return muted.get();
    }

    public SimpleBooleanProperty mutedProperty() {
        return muted;
    }

    /**
     * changes the muted property of the program
     *
     * if the program's muted property is true, change it to false
     * if the program's muted property is false, change it to true
     */
    public void toggleMute(){
        this.muted.set(!this.muted.get());
    }

    public double getTotalGain() {
        return totalGain.get();
    }

    /**
     * Getter for the totalGain of the program
     * @return totalGain - the totalGain of all the producers
     */
    public SimpleDoubleProperty totalGainProperty() {
        return totalGain;
    }

    /**
     * Sets the total gain of the producers
     * @param totalGain - double value for totalGain to be set to
     */
    public void setTotalGain(double totalGain) {
        this.totalGain.set(totalGain);
    }


    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }
}
