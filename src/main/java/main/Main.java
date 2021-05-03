package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private GameModel theModel;
    private GameView theView;
    private GameController theController;

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    @Override
    public void init() throws Exception{
        super.init();
        theModel = new GameModel();
        theView = new GameView(theModel);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GuiV1.fxml"));
        Parent root = loader.load();

        theController = new GameController();
        theController.setupController(theModel,theView);
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
