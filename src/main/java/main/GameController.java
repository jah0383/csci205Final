package main;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private Label P2_cost;

    @FXML
    private Label P2_gain;

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
    }

    @FXML
    public void initialize() {
        linkAndBindProducers();
    }

    /**
     * This method links everything in the scene to everything that's in the model
     * @author James Howe
     */
    private void linkAndBindProducers() {
        for (int i = 1; i < this.theModel.getProducers().size(); i++) {
            VBox producerVBox = (VBox) producer_pane.getChildren().get(i);

            VBox displayVBox = (VBox) ((HBox) producerVBox.getChildren().get(0)).getChildren().get(1);

            Label costLabel = (Label) ((HBox) displayVBox.getChildren().get(0)).getChildren().get(1);
            Label gainLabel = (Label) ((HBox) displayVBox.getChildren().get(1)).getChildren().get(1);
            Label timeLabel = (Label) ((HBox) displayVBox.getChildren().get(2)).getChildren().get(1);

            ProgressBar progressBar = (ProgressBar) producerVBox.getChildren().get(1);


            ChangeListener<String> costListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> costLabel.setText(newStatus));
            ChangeListener<String> gainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> gainLabel.setText(newStatus));
            ChangeListener<String> timeListener = (obs, oldValue, newValue) -> Platform.runLater(() -> timeLabel.textProperty().set(newValue));
            ChangeListener<String> dnaListener = (obs, oldValue, newValue) -> Platform.runLater(() -> dna_label1.setText(newValue));
            this.theModel.producers.get(i).displayCostForNextProperty().addListener(costListener);
            this.theModel.producers.get(i).displayTotalGainProperty().addListener(gainListener);
            this.theModel.producers.get(i).timePropertyProperty().addListener(timeListener);
            this.theModel.producers.get(i).getDnaProduced().addListener(dnaListener);

            ChangeListener<Number> progressListener = (obs, oldValue, newValue) -> Platform.runLater(() -> progressBar.setProgress(newValue.doubleValue()));
            this.theModel.producers.get(i).progressProperty().addListener(progressListener);
        }
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

    @FXML
    public void test(ActionEvent actionEvent) throws InterruptedException {
        if (!this.theModel.producers.get(1).getFirstBuy()) {
            this.theModel.producers.get(1).setFirstBuy();
            new Thread(this.theModel.producers.get(1)).start();
        }

    }

    /**
     * This method is called whenever a producer button is pressed and handles the buying of producers
     * @param event is passed in automatically
     * @author James Howe
     */
    @FXML
    public void producerBuyHandler(Event event) {
        //Gets the id from the node which called the event
        String nodeID = ((Node) event.getSource()).getId();
        System.out.println(nodeID);
        int producerNumber = Integer.parseInt(nodeID.substring(1,2));

        this.theModel.getProducers().get(producerNumber - 1).buy();

        //Testing
        System.out.println(this.theModel.getProducers().get(producerNumber - 1).getDisplayCostForNext());

    }
}