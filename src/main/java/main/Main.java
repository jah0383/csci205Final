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
    public void start(Stage primaryStage) throws Exception {
        theController = new GameController(theModel,theView);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GuiV1.fxml"));
        primaryStage.setTitle("Covid Clicker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

//    public void test(){
//        System.out.println("hey");
//    }
}
