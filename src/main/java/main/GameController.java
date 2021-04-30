package main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;

public class GameController {
    private GameModel theModel;
    private GameView theView;

    public GameController(){

    }
    public GameController(GameModel theModel, GameView theView){
        this.theModel = theModel;
        this.theView = theView;
    }


    public void test(ActionEvent event){
        Button btn = (Button) event.getSource();
        System.out.println(btn.getId());
    }




}
