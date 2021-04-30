package main;

public class GameController {

    // view and model loaded in
    private final GameModel theModel;
    private final GameView theView;

    /**
     * Constructor for the game controller
     *
     * @param theView  is the view of the game, i.e. the scene
     * @param theModel is the model of the game, i.e. the info about all cells currently in the game
     */
    public GameController(GameView theView, GameModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    /**
     * handles events for producers, i.e. button click to buy cells
     */
    private void producerEventHandler() {
        //if (theModel.producer)
    }

    /**
     * handles events for the upgrades of producers
     */
    private void upgradeEventHandler() {

    }

    /**
     * updates the values of all items in the game
     */
    private void update() {

    }

    /**
     * sets up the game
     */
    private void setup() {

    }

    /**
     * loads game data from save
     */
    private void loadFromSave() {

    }
}