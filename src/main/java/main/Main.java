package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private GameModel theModel;
    private GameView theView;
    private GameController theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception{
        super.init();
        this.theModel = new GameModel();
        this.theView = new GameView(theModel);

    }

    @Override
    public void start(Stage primaryStage) {

    }
}
