package main;

import javafx.beans.property.SimpleLongProperty;

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
    private long totalGain;
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


    public GameModel(){
        this.totalDNA = new SimpleLongProperty(0);
        this.totalGain = 0;
        this.buyMode = BuyMode.ONE;
        this.producers = new ArrayList<>();
        this.producers.add(new Producer("P1",0,1000,1,1.01));
        this.producers.add(new Producer("P2",50,1,10,1.1));
        this.producers.add(new Producer("P3",50,1,10,1.1));
        this.producers.add(new Producer("P4",50,1,10,1.1));
        this.producers.add(new Producer("P5",50,1,10,1.1));
        this.producers.add(new Producer("P6",50,1,10,1.1));
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
}
