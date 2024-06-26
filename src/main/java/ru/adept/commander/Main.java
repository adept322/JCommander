package ru.adept.commander;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Запуск начального окна с параметрами
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main.fxml")));
        primaryStage.setTitle("JCommander");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}