package main;

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
    private long totalDNA;
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


    public GameModel(){
        this.producers = new ArrayList<>();
        this.producers.add(new Producer("P1",1,1,1,1.1));
        this.producers.add(new Producer("P2",1,1,10,1.1));
        this.producers.add(new Producer("P3",1,1,10,1.1));
        this.producers.add(new Producer("P4",1,1,10,1.1));
        this.producers.add(new Producer("P5",1,1,10,1.1));
        this.producers.add(new Producer("P6",1,1,10,1.1));
    }






}
