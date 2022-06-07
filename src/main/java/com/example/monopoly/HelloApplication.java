package com.example.monopoly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("plane_finished.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1320, 840);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}