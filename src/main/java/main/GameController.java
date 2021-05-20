package main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import static main.BuyMode.*;

public class GameController {

    // view and model loaded in
    /**
     * instance of GameModel
     */
    private GameModel theModel;

    @FXML
    private VBox producer_pane;


    @FXML
    private Label dna_label1;

    @FXML
    private Label dna_per_second1;

    @FXML
    private Tab upgrade_tab;

    @FXML
    private GridPane upgrade_grid;

    @FXML
    private ImageView visual;

    @FXML
    private ImageView visual_mask;

    @FXML
    private Pane part_pane;
    @FXML
    private VBox upgrade_vbox;

    private Image bodyMaskImage;

    /**
     * MediaPlayer object (for music)
     */
    private MediaPlayer musicPlayer;



    /**
     * Constructor for the game controller
     * @author James Howe
     */
    public GameController() {
        this.theModel = new GameModel();
    }

    /**
     * The constructor has to be zero argument for it to work with Scene Builder, so this is the real constructor
     *
     * @param theModel is the model of the game, i.e. the info about all cells currently in the game
     */
    public void setupController(GameModel theModel) {
        this.theModel = theModel;
    }

    /**
     * Initializes the game doing what a constructor would normally do, but due to some quirks of when FXML stuff is loaded
     * has to be done, outside of the constructor
     * @author James Howe
     */
    @FXML
    public void initialize(){

        linkAndBindProducers();

        setupUpgrades();

        setImages();

        setupModelDNA();

        this.musicStart();
    }

    /**
     * This handles the linking of the DNA information to the model
     * @author James Howe
     */
    private void setupModelDNA() {
        ChangeListener<Number> DNAListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> dna_label1.setText(newStatus.toString()));
        this.theModel.totalDNAProperty().addListener(DNAListener);

        ChangeListener<Number> DNAGainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> dna_per_second1.setText(newStatus.toString()));
        this.theModel.totalGainProperty().addListener(DNAGainListener);
    }

    /**
     * This Method Handles the setup for the upgrades, naming them, assigning an action to them, and binding them
     * it then adds it to the upgrades tab in the window
     * @author James Howe
     */
    private void setupUpgrades() {
        for (int i = 1; i < this.theModel.getUpgrades().size(); i++) {
            Button gainUp1 = new Button();
            gainUp1.setPrefWidth(248);
            gainUp1.setPrefHeight(30);
            gainUp1.setText(String.format("P%d Gain 2x",i));
            gainUp1.setOnAction(upgradeBuy);
            gainUp1.setId(String.format("P%dG1",i));
            gainUp1.disableProperty().bind(this.theModel.getUpgrades().get(i).purchasedGainX2Property());

            Button gainUp2 = new Button();
            gainUp2.setPrefWidth(248);
            gainUp2.setPrefHeight(30);
            gainUp2.setText(String.format("P%d Gain 8x",i));
            gainUp2.setOnAction(upgradeBuy);
            gainUp2.setId(String.format("P%dG2",i));
            gainUp2.disableProperty().bind(this.theModel.getUpgrades().get(i).purchasedGainX8Property());

            Button periodUp1 = new Button();
            periodUp1.setPrefWidth(248);
            periodUp1.setPrefHeight(30);
            periodUp1.setText(String.format("P%d Time .5x",i));
            periodUp1.setOnAction(upgradeBuy);
            periodUp1.setId(String.format("P%dT3",i));
            periodUp1.disableProperty().bind(this.theModel.getUpgrades().get(i).purchasedPeriodX2Property());

            Button periodUp2 = new Button();
            periodUp2.setPrefWidth(248);
            periodUp2.setPrefHeight(30);
            periodUp2.setText(String.format("P%d Time .25x",i));
            periodUp2.setOnAction(upgradeBuy);
            periodUp2.setId(String.format("P%dT4",i));
            periodUp2.disableProperty().bind(this.theModel.getUpgrades().get(i).purchasedPeriodX4Property());


            upgrade_vbox.getChildren().add(gainUp1);
            upgrade_vbox.getChildren().add(gainUp2);
            upgrade_vbox.getChildren().add(periodUp1);
            upgrade_vbox.getChildren().add(periodUp2);
        }
    }

    /**
     * Sets the appropriate images for the visual representations in the game
     * @author James Howe
     */
    private void setImages(){
        InputStream bodyStream = (getClass().getClassLoader().getResourceAsStream("BodyVisual.png"));
        Image bodyImage = new Image(bodyStream);
        visual.setImage(bodyImage);

        InputStream bodyMaskStream = (getClass().getClassLoader().getResourceAsStream("BodyMask.png"));
        Image bodyMaskImage = new Image(bodyMaskStream);
        visual_mask.setImage(bodyMaskImage);
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
            Label amountLabel = (Label) ((HBox) displayVBox.getChildren().get(2)).getChildren().get(1);

            HBox timeBox = (HBox) producerVBox.getChildren().get(1);
            ProgressBar progressBar = (ProgressBar) timeBox.getChildren().get(0);
            Label timeLabel = (Label) ((HBox) timeBox.getChildren().get(1)).getChildren().get(1);



            ChangeListener<String> costListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> costLabel.setText(newStatus));
            ChangeListener<String> gainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> gainLabel.setText(newStatus));
            ChangeListener<String> amountListener = (obs, oldValue, newValue) -> Platform.runLater(() -> amountLabel.textProperty().set(newValue));
            ChangeListener<String> timeListener = (obs, oldValue, newValue) -> Platform.runLater(() -> timeLabel.textProperty().set(newValue));
            this.theModel.producers.get(i).displayCostForNextProperty().addListener(costListener);
            this.theModel.producers.get(i).displayTotalGainProperty().addListener(gainListener);
            this.theModel.producers.get(i).displayNumberPurchasedProperty().addListener(amountListener);
            this.theModel.producers.get(i).timePropertyProperty().addListener(timeListener);

            ChangeListener<Number> progressListener = (obs, oldValue, newValue) -> Platform.runLater(() -> progressBar.setProgress(newValue.doubleValue()));
            this.theModel.producers.get(i).progressProperty().addListener(progressListener);

            ChangeListener<Number> mostRecentGainListener = (obs, oldStatus, newStatus) -> Platform.runLater(() -> this.theModel.setTotalDNA(this.theModel.getTotalDNA() + (long) newStatus));
            this.theModel.producers.get(i).mostRecentGainProperty().addListener(mostRecentGainListener);

            this.theModel.producers.get(i).setInitialDisplay();
        }
    }


    /**
     * Creates a small particle and adds it onto the body visual
     * @author James Howe
     */
    public void addParticle(Color color){
        Random rand = new Random();
        Circle particle = new Circle();
        particle.setFill(color);
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
    public void producerBuyHandler(Event event)     {
        //Gets the id from the node which called the event
        String nodeID = ((Node) event.getSource()).getId();
        int producerNumber = Integer.parseInt(nodeID.substring(1, 2));



        //Handles if its the first producer
        if (producerNumber == 1) {
            theModel.setTotalDNA(theModel.getTotalDNA() + this.theModel.getProducers().get(0).getInitialGain());
            this.addParticle(this.theModel.getProducers().get(0).getPartColor());
        }
        else {
            Producer producer = this.theModel.getProducers().get(producerNumber - 1);
            long cost;
            switch(theModel.buyMode){
                case ONE:
                    buyProducer(producer);
                    break;
                case TEN:
                    for (int i = 0; i < 10; i++) {
                        cost = buyProducer(producer);
                        if (cost == -1) {
                            break;
                        }
                    }
                    break;
                case ONEHUNDRED:
                    for (int i = 0; i < 100; i++) {
                        cost = buyProducer(producer);
                        if (cost == -1) {
                            break;
                        }
                    }
                    break;
                case MAX:
                    while (true) {
                        cost = buyProducer(producer);
                        if (cost == -1) {
                            break;
                        }
                    }
                    break;
                default:
                    buyProducer(producer);
                    break;
            }
        }

        //Updates the total gain per second
        updateTotalGain();
    }

    /**
     * updates the totalGain of the program
     *
     * calculates the sum of the total DNA gain per second for each producer
     * sets theModel's total Gain to the sum
     */
    private void updateTotalGain(){
        Double gainAccumulator = 0.0;
        for (int i = 1; i < this.theModel.getProducers().size(); i++) {
            gainAccumulator += this.theModel.getProducers().get(i).getDnaPerSecond();
        }
        this.theModel.setTotalGain(gainAccumulator);
    }


    /**
     * Changes the model's buyMode variable to the enumeration ONE
     *
     * used in button to buy 1 producer at a time
     */
    @FXML
    public void changeBuyModeto1x() {
        theModel.buyMode = ONE;
    }


    /**
     * Changes the model's buyMode variable to the enumeration TEN
     *
     * used in button to buy 10 producers at a time
     */
    @FXML
    public void changeBuyModeto10x() {
        theModel.buyMode = TEN;
    }

    /**
     * Changes the model's buyMode variable to the enumeration ONEHUNDRED
     *
     * used in button to buy 100 producers at a time
     */
    @FXML
    public void changeBuyModeto100x() {
        theModel.buyMode = ONEHUNDRED;
    }

    /**
     * Changes the model's buyMode variable to the enumeration MAX
     *
     * used in button to buy maximum producers at a time
     */
    @FXML
    public void changeBuyModetoMAX() {
        theModel.buyMode = MAX;
    }

    /**
     * Handles the buying of the producer,
     * @param producer The producer you'd like to buy
     * @return the Cost of the producer, more importantly a -1 if it couldn't be afforded
     * @author James Howe
     */
    private long buyProducer(Producer producer) {
        long cost = producer.buy(theModel.getTotalDNA());
        if (cost != -1 && producer.getNumberPurchased() == 1) {
            new Thread(producer).start();
            this.addParticle(producer.getPartColor());
        }
        if (cost != -1) {
            this.addParticle(producer.getPartColor());
            theModel.setTotalDNA(theModel.getTotalDNA() - cost);
        }
        return cost;
    }

    /**
     * This Event handler deals with when an upgrade has been purchased and calls the relevant buy method
     * from controller
     */
    EventHandler<ActionEvent> upgradeBuy = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event){
            String nodeID = ((Node) event.getSource()).getId();
            int upgradeNumber = Integer.parseInt(nodeID.substring(1, 2));
            int upgradeType = Integer.parseInt(nodeID.substring(3, 4));
            Upgrade upgrade = theModel.getUpgrades().get(upgradeNumber);
            switch (upgradeType){
                case 1:
                    buyUpgradeGainX2(upgrade);
                    break;
                case 2:
                    buyUpgradeGainX8(upgrade);
                    break;
                case 3:
                    buyUpgradePeriodX4(upgrade);
                    break;
                case 4:
                    buyUpgradePeriodX16(upgrade);
                    break;
            }
            updateTotalGain();
        }

    };

    /**
     * Checks if the upgrade is able to be bought (If the upgrade is not purchased already and if there is enough DNA stored).
     * If able, then the upgrade is purchased and the DNA is subtracted from the total.
     * @param upgrade The relevant upgrade that is being bought from upgrade event handler.
     * @return True if successful purchase, false if insufficient DNA and or already bought.
     * @author Michael Gertz
     */
    private boolean buyUpgradeGainX2(Upgrade upgrade){
        long cost = 1000;
        long currentDNA = theModel.getTotalDNA();
        boolean isPurchased = upgrade.isPurchasedGainX2();
        if(!isPurchased && currentDNA >= cost){
            isPurchased = upgrade.upgradeGainX2();
            theModel.setTotalDNA(theModel.getTotalDNA() - cost);
        }
        return isPurchased;
    }

    /**
     * Checks if the upgrade is able to be bought (If the upgrade is not purchased already and if there is enough DNA stored).
     * If able, then the upgrade is purchased and the DNA is subtracted from the total.
     * @param upgrade The relevant upgrade that is being bought from upgrade event handler.
     * @return True if successful purchase, false if insufficient DNA and or already bought.
     * @author Michael Gertz
     */
    private boolean buyUpgradeGainX8(Upgrade upgrade){
        long cost = 10000;
        long currentDNA = theModel.getTotalDNA();
        boolean isPurchased = upgrade.isPurchasedGainX8();
        if(!isPurchased && currentDNA >= cost){
            isPurchased = upgrade.upgradeGainX8();
            theModel.setTotalDNA(theModel.getTotalDNA() - cost);
        }
        return isPurchased;
    }

    /**
     * Checks if the upgrade is able to be bought (If the upgrade is not purchased already and if there is enough DNA stored).
     * If able, then the upgrade is purchased and the DNA is subtracted from the total.
     * @param upgrade The relevant upgrade that is being bought from upgrade event handler.
     * @return True if successful purchase, false if insufficient DNA and or already bought.
     * @author Michael Gertz
     */
    private boolean buyUpgradePeriodX4(Upgrade upgrade){
        long cost = 3000;
        long currentDNA = theModel.getTotalDNA();
        boolean isPurchased = upgrade.getPurchasedPeriodX2();
        if(!isPurchased && currentDNA >= cost){
            isPurchased = upgrade.upgradePeriodX2();
            theModel.setTotalDNA(theModel.getTotalDNA() - cost);
        }
        return isPurchased;
    }

    /**
     * Checks if the upgrade is able to be bought (If the upgrade is not purchased already and if there is enough DNA stored).
     * If able, then the upgrade is purchased and the DNA is subtracted from the total.
     * @param upgrade The relevant upgrade that is being bought from upgrade event handler.
     * @return True if successful purchase, false if insufficient DNA and or already bought.
     * @author Michael Gertz
     */
    private boolean buyUpgradePeriodX16(Upgrade upgrade){
        long cost = 30000;
        long currentDNA = theModel.getTotalDNA();
        boolean isPurchased = upgrade.getPurchasedPeriodX4();
        if(!isPurchased && currentDNA >= cost){
            isPurchased = upgrade.upgradePeriodX4();
            theModel.setTotalDNA(theModel.getTotalDNA() - cost);
        }
        return isPurchased;
    }

    /**
     * The control for the mute button
     *
     * if the sound is not already muted when pressed, mute the sound
     *
     * if the sound is already muted when pressed, unmute the sound
     * @author Joseph and James Howe
     */
    @FXML
    public void muteToggle(){
        if (theModel.isMuted()){
            musicPlayer.setVolume(0.2);
        }else{
            musicPlayer.setVolume(0.0);
        }
        theModel.toggleMute();
    }


    /**
     * Initializes the music at the start of the game
     * @author Joseph and James Howe
     */
    public void musicStart() throws NullPointerException {
        URL musicUrl = getClass().getClassLoader().getResource("GameMusic.mp3");
        Media sound = new Media("file://" + musicUrl.getPath());
        musicPlayer = new MediaPlayer(sound);
        musicPlayer.setCycleCount(999999999);
        musicPlayer.setAutoPlay(true);
        musicPlayer.setVolume(0.2);
    }


}