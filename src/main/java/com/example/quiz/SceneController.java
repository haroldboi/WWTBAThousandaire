package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Audio.sounds.clear();
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setResizable(false);
        HelloController hello = loader.getController();
        hello.updateMute();;
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
        Audio.sounds.clear();
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setResizable(false);
        CategoryController cat = loader.getController();
        cat.mute.setText(Audio.muted ? "unmute":"mute");
        stage.show();
    }

    public void switchToGame(ActionEvent event,Integer category) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Audio.sounds.clear();
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        GameController game = loader.getController();
        game.questionM(category);
        stage.show();
    }
}
