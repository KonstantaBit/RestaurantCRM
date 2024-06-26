package org.m0d3rn1ca.restaurantcrm;

import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager scene_manager = SceneManager.getInstance();
        scene_manager.setStage(stage);
        scene_manager.setScene("Login.fxml", 1080, 720);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        Connector.getInstance().closeConnection();
        super.stop();
    }
}