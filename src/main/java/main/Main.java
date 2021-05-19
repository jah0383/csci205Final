package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private GameModel theModel;
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
        this.theModel = new GameModel();


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
        theController.setupController(theModel);


        primaryStage.setTitle("Covid Clicker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //This just makes it so that when the window is closed the program stops
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }
}
