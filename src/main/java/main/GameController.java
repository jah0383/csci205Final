package main;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameController {

    // view and model loaded in
    private GameModel theModel;
    private GameView theView;

    @FXML
    private VBox producer_pane;

    @FXML
    private VBox P1;

    @FXML
    private Button P1_btn;

    @FXML
    private VBox P2;

    @FXML
    private VBox P2_display;

    @FXML
    private Label P2_time;

    @FXML
    private VBox P3;

    @FXML
    private VBox P4;

    @FXML
    private VBox P5;

    @FXML
    private VBox P6;

    @FXML
    private Label dna_label;

    @FXML
    private Label dna_label1;

    @FXML
    private Label dna_per_second;

    @FXML
    private Label dna_per_second1;

    @FXML
    private Button buy_1x;

    @FXML
    private Button buy_10x;

    @FXML
    private Button buy_100x;

    @FXML
    private Button buy_max;

    @FXML
    private TabPane u;

    @FXML
    private Tab visuals_tab;

    @FXML
    private Tab upgrade_tab;

    @FXML
    private GridPane upgrade_grid;

    /**
     * Constructor for the game controller
     *
     */
    public GameController() {
        this.theModel = new GameModel();
        this.theView = new GameView(this.theModel);
    }

    /**
     * The constructor has to be zero argument for it to work with Scene Builder, so this is the real constructor
     *
     * @param theView  is the view of the game, i.e. the scene
     * @param theModel is the model of the game, i.e. the info about all cells currently in the game
     */
    public void setupController(GameModel theModel, GameView theView){
        this.theModel = theModel;
        this.theView = theView;
//        System.out.println(P2_time);
//        P2_time = new Label();
//        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
//        System.out.println(P2_time.textProperty().toString());


    }

    @FXML
    public void initialize() {
//        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
        System.out.println(P2_time);
        P2_time.textProperty().bind(this.theModel.producers.get(1).timePropertyProperty().asString());
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

    public void test(ActionEvent actionEvent) throws InterruptedException {
        System.out.println("test");
        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
        new Thread(this.theModel.producers.get(1)).start();
        //this.theModel.producers.get(1).run(5);

    }
}