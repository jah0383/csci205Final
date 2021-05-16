package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private GameModel theModel;
    private GameView theView;
    private GameController theController;

    /**
     * main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    /**
     * initializes the program
     * @throws Exception
     */
    @Override
    public void init() throws Exception{
        super.init();
        theModel = new GameModel();
        theView = new GameView(theModel);


    }

    /**
     * starts the program
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GuiV1.fxml"));
        Parent root = loader.load();

        theController = new GameController();
        theController.setupController(theModel,theView);
//        theController.musicStart();
//        theController.initialize();




        primaryStage.setTitle("Covid Clicker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


//    public static void test() throws InterruptedException {
//        Producer newCell = new Producer();
//        newCell.run(5);
//    }
}
