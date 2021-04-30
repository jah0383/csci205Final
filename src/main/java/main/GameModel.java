package main;

import java.time.LocalDateTime;

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

}
