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

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    /**
     * The list of producers
     */
    public ArrayList<Producer> producers;

    /**
     * The current buy mode of the game (1x/10x/100x/MAX)
     */
    public BuyMode buyMode;

    /**
     * boolean if the program is mutted or not
     */
    private SimpleBooleanProperty muted;


    public GameModel(){
        this.totalDNA = new SimpleLongProperty(0);
        this.totalGain = new SimpleDoubleProperty(0.0);
        this.buyMode = BuyMode.ONE;
        this.muted = new SimpleBooleanProperty(false);
        this.producers = new ArrayList<>();
        this.producers.add(new Producer("P1",0,1000,1,1.01, Color.LIGHTGREEN));
        this.producers.add(new Producer("P2",50,1,10,1.1, Color.BLUE));
        this.producers.add(new Producer("P3",50,1,10,1.1, Color.MAGENTA));
        this.producers.add(new Producer("P4",50,1,10,1.1, Color.YELLOWGREEN));
        this.producers.add(new Producer("P5",50,1,10,1.1, Color.GRAY));
        this.producers.add(new Producer("P6",50,1,10,1.1, Color.LIGHTCORAL));
    }


    //TODO - method that calculate the maximum amount of a producer the user can buy
    public int calcMaxBuy(){
        return 1;

    }


    public long getTotalDNA() {
        return totalDNA.get();
    }

    public SimpleLongProperty totalDNAProperty() {
        return totalDNA;
    }

    public void setTotalDNA(long totalDNA) {
        this.totalDNA.set(totalDNA);
    }

    public boolean isMuted() {
        return muted.get();
    }

    public SimpleBooleanProperty mutedProperty() {
        return muted;
    }

    public void toggleMute(){
        this.muted.set(!this.muted.get());
    }

    public double getTotalGain() {
        return totalGain.get();
    }

    public SimpleDoubleProperty totalGainProperty() {
        return totalGain;
    }

    public void setTotalGain(double totalGain) {
        this.totalGain.set(totalGain);
    }
}
