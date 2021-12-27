package com.example.hausaufgabe6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class HelloApplication extends Application {

    public void start(Stage stage) throws Exception {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainPanelView.fxml")));
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainPanelView.fxml")));
        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        Stage newStage = new Stage();
        newStage.setScene(scene2);
        newStage.setTitle("Secondary");
        stage.setScene(scene1);
        stage.setTitle("Primary");
        stage.show();
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}