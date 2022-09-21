package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class CategoryController {
    public Integer[] category = {9,11,12,14,15,21,22,23,32};
    private SceneController switcher = new SceneController();
    private  Audio sound ;



    @FXML Button mute;

    public CategoryController(){
        sound = new Audio("Main Theme (1).wav",0.3d);
        sound.loop();

    }
    @FXML
    public void menu(ActionEvent event) throws IOException {
        sound.stop();
        switcher.switchToMenu(event);

    }


    public void MM(ActionEvent Event){
        if(Audio.muted){
            Audio.unmute();
            sound.loop();
            System.out.println(Audio.sounds);
        }
        else{
            Audio.mute();
        }
        mute.setText(Audio.muted ? "unmute":"mute");
    }

    public void hover(MouseEvent event) throws IOException{
        Button button = (Button) event.getTarget();
        button.setTextFill(Paint.valueOf("red"));
    }

    public void hoveroff (MouseEvent event){
        Button button = (Button) event.getTarget();
        button.setTextFill(Paint.valueOf("gold"));
    }

    public void picked9 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,9);
    }
    public void picked11 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,11);
    }
    public void picked12 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,12);
    }
    public void picked14 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,14);
    }
    public void picked15 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,15);
    }
    public void picked21 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,21);
    }
    public void picked22 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,22);
    }
    public void picked23 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,23);
    }
    public void picked32 (ActionEvent event) throws IOException{
        switcher.switchToGame(event,32);
    }
}
