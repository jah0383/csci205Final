package main;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
//        System.out.println(P2_time);
//        P2_time = new Label();
//        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
//        System.out.println(P2_time.textProperty().toString());


    }

    @FXML
    public void initialize() {



        for(int i = 1; i<this.theModel.getProducers().size(); i++){
            VBox producerVBox = (VBox) producer_pane.getChildren().get(i);

            VBox displayVBox = (VBox) ((HBox) producerVBox.getChildren().get(0)).getChildren().get(1);

            Label costLabel = (Label) ((HBox)displayVBox.getChildren().get(0)).getChildren().get(1);
            Label gainLabel = (Label) ((HBox)displayVBox.getChildren().get(1)).getChildren().get(1);
            Label timeLabel = (Label) ((HBox)displayVBox.getChildren().get(2)).getChildren().get(1);

            ProgressBar progressBar = (ProgressBar) producerVBox.getChildren().get(1);


            ChangeListener<String> costListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> costLabel.setText(newStatus));
            ChangeListener<String> gainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> gainLabel.setText(newStatus));
            ChangeListener<String> timeListener = (obs, oldValue, newValue) -> Platform.runLater(() -> timeLabel.textProperty().set(newValue));
            this.theModel.producers.get(i).displayCostForNextProperty().addListener(costListener);
            this.theModel.producers.get(i).displayTotalGainProperty().addListener(gainListener);
            this.theModel.producers.get(i).timePropertyProperty().addListener(timeListener);

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
//        System.out.println("test");
//        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
        new Thread(this.theModel.producers.get(1)).start();
        //this.theModel.producers.get(1).run(5);

    }

    @FXML
    public void producerBuy(Event actionEvent) {
        String nodeID = ((Node) actionEvent.getSource()).getId();
        System.out.println(nodeID);
        int producerNumber = Integer.parseInt(nodeID.substring(1,2));

        this.theModel.getProducers().get(producerNumber - 1).buy();


    }
}