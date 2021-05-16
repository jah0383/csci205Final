package main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Random;

import static main.BuyMode.*;

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

    @FXML
    private ImageView visual;

    @FXML
    private ImageView visual_mask;


    @FXML
    private AnchorPane visual_pane;

    @FXML
    private Pane part_pane;

    private Image bodyMaskImage;

    /**
     * Constructor for the game controller
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
    public void setupController(GameModel theModel, GameView theView) {
        this.theModel = theModel;
        this.theView = theView;
//        System.out.println(P2_time);
//        P2_time = new Label();
//        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
//        System.out.println(P2_time.textProperty().toString());


    }

    @FXML
    public void initialize() throws FileNotFoundException {


        linkAndBindProducers();

        InputStream bodyStream = (getClass().getClassLoader().getResourceAsStream("BodyVisual.png"));
        Image bodyImage = new Image(bodyStream);
        visual.setImage(bodyImage);

        InputStream bodyMaskStream = (getClass().getClassLoader().getResourceAsStream("BodyMask.png"));
        bodyMaskImage = new Image(bodyMaskStream);
        visual_mask.setImage(bodyMaskImage);

        ChangeListener<Number> DNAListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> dna_label1.setText(newStatus.toString()));
        this.theModel.totalDNAProperty().addListener(DNAListener);


    }

    /**
     * This method links everything in the scene to everything that's in the model
     *
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
            this.theModel.producers.get(i).displayCostForNextProperty().addListener(costListener);
            this.theModel.producers.get(i).displayTotalGainProperty().addListener(gainListener);
            this.theModel.producers.get(i).timePropertyProperty().addListener(timeListener);

            ChangeListener<Number> progressListener = (obs, oldValue, newValue) -> Platform.runLater(() -> progressBar.setProgress(newValue.doubleValue()));
            this.theModel.producers.get(i).progressProperty().addListener(progressListener);

            ChangeListener<Number> mostRecentGainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> this.theModel.setTotalDNA(this.theModel.getTotalDNA() + (long) newStatus));
            this.theModel.producers.get(i).mostRecentGainProperty().addListener(mostRecentGainListener);
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
////        System.out.println(this.theModel.producers.get(1).timePropertyProperty());
//        new Thread(this.theModel.producers.get(1)).start();
        //this.theModel.producers.get(1).run(5);

    }

    public void addCircle(){
        Random rand = new Random();
        Circle particle = new Circle();
        particle.setFill(Color.GREEN);
        particle.setRadius(1.5);
        particle.setCenterX(rand.nextDouble()*part_pane.getWidth());
        particle.setCenterY(rand.nextDouble()*part_pane.getHeight());
        part_pane.getChildren().add(particle);
    }

    /**
     * This method is called whenever a producer button is pressed and handles the buying of producers
     *
     * @param event is passed in automatically
     * @author James Howe
     */
    @FXML
    public void producerBuyHandler(Event event) {
        //Gets the id from the node which called the event
        String nodeID = ((Node) event.getSource()).getId();
        System.out.println(nodeID);
        int producerNumber = Integer.parseInt(nodeID.substring(1, 2));

        if (producerNumber == 1) {
            theModel.setTotalDNA(theModel.getTotalDNA() + this.theModel.getProducers().get(producerNumber - 1).getInitialGain());
            this.addCircle();
        } else {

            if (theModel.buyMode == ONE) {
                long cost = this.theModel.getProducers().get(producerNumber - 1).buy(theModel.getTotalDNA());
                System.out.println(this.theModel.getProducers().get(producerNumber - 1).getNumberPurchased());
                if (cost != -1 && this.theModel.getProducers().get(producerNumber - 1).getNumberPurchased() == 1) {
                    new Thread(this.theModel.producers.get(producerNumber - 1)).start();
                }
                if (cost != -1) {
                    theModel.setTotalDNA(theModel.getTotalDNA() - cost);
                }
            } else if (theModel.buyMode == TEN) {
                for (int i = 0; i < 10; i++) {
                    long cost = this.theModel.getProducers().get(producerNumber - 1).buy(theModel.getTotalDNA());
                    if (cost == -1) {
                        break;
                    } else {
                        theModel.setTotalDNA(theModel.getTotalDNA() - cost);
                    }
                }
            } else if (theModel.buyMode == ONEHUNDRED) {
                for (int i = 0; i < 100; i++) {
                    long cost = this.theModel.getProducers().get(producerNumber - 1).buy(theModel.getTotalDNA());
                    if (cost == -1) {
                        break;
                    } else {
                        theModel.setTotalDNA(theModel.getTotalDNA() - cost);
                    }
                }
            } //TODO - fill in max buy
            else if (theModel.buyMode == MAX) {
                //get the number of producers the user can purchase
                int maxNum = theModel.calcMaxBuy();
                while (true) {
                    long cost = this.theModel.getProducers().get(producerNumber - 1).buy(theModel.getTotalDNA());
                    if (cost == -1) {
                        break;
                    } else {
                        theModel.setTotalDNA(theModel.getTotalDNA() - cost);
                    }
                }
            }

        }
    }


    @FXML
    public void changeBuyModeto1x() {
        theModel.buyMode = ONE;
    }

    @FXML
    public void changeBuyModeto10x() {
        theModel.buyMode = TEN;
    }

    @FXML
    public void changeBuyModeto100x() {
        theModel.buyMode = ONEHUNDRED;
    }

    @FXML
    public void changeBuyModetoMAX() {
        theModel.buyMode = MAX;
    }

    @FXML
    public void muteButton(){
        if (!theModel.muted){
            musicMute();
        }else if(theModel.muted){
            musicUnMute();
        }
    }

    MediaPlayer mediaPlayer;
    /**
     * initializes the music at the start of the game
     */
    public void musicStart(){
//        String fileName = "resources/Never Gonna Give You Up (8 Bit Remix Cover Version) [Tribute to Rick Astley] - 8 Bit Universe.mp3";
//        Media sound = new Media(Paths.get(fileName).toUri().toString());
//        mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.setCycleCount(999999999);
//        mediaPlayer.setAutoPlay(true);
//
//        /*mediaPlayer.setOnEndOfMedia(new Runnable() {
//            @Override
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
//        mediaPlayer.play();*/
    }

    /**
     * mutes the music
     */
    public void musicMute(){
        mediaPlayer.setVolume(0.0);
    }

    /**
     * Un-mutes the music
     */
    public void musicUnMute(){
        mediaPlayer.setVolume(1.0);
    }
}