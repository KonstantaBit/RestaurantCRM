package org.m0d3rn1ca.restaurantcrm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private SceneManager scene_manager;
    @Override
    public void start(Stage stage) throws IOException {
        scene_manager = SceneManager.getInstance();
        scene_manager.setStage(stage);
        scene_manager.setScene("Login.fxml", 1080, 720);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        CachedConnector.getInstance().closeConnection();
        super.stop();
    }
}