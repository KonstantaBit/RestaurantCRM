package org.m0d3rn1ca.restaurantcrm;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SceneManager {
    private static SceneManager INSTANCE;
    private Stage stage = null;
    private SceneManager() {

    }

    public static SceneManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SceneManager();
        return INSTANCE;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(String file, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    public boolean dialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
