package com.example.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        Scene menu = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.setResizable(false);
        HelloController hello = loader.getController();
        hello.updateMute();
        stage.show();


    }

    public static void main() {
        launch();
    }
}