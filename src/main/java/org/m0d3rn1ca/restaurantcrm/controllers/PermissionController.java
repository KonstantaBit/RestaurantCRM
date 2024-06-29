package org.m0d3rn1ca.restaurantcrm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermissionController implements Initializable {
    @FXML
    private Button user_control;
    @FXML
    private Button table_control;
    @FXML
    private Button food_control;
    @FXML
    private Button order_control;
    @FXML
    private Button ingredient_control;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = Users.getInstance().getCurrent();
        System.out.println(user.getID());
        for (Position position : Positions.getInstance().getPositions(user.getID())) {
            if (position.getName().equals("Администратор"))
                user_control.setDisable(false);
            if (position.getName().equals("Администратор") || position.getName().equals("Официант"))
                table_control.setDisable(false);
            if (position.getName().equals("Администратор") || position.getName().equals("Повар"))
                food_control.setDisable(false);
            if (position.getName().equals("Администратор") || position.getName().equals("Повар"))
                ingredient_control.setDisable(false);
            if (position.getName().equals("Администратор") || position.getName().equals("Официант"))
                order_control.setDisable(false);
        }

    }

    @FXML
    protected void toCabinet(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("Cabinet.fxml", 1080, 720);
    }

    @FXML
    protected void toUserControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("UserControl.fxml", 1080, 720);
    }

    @FXML
    protected void toTableControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("TableControl.fxml", 1080, 720);
    }

    @FXML
    protected void toIngControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("IngControl.fxml", 1080, 720);
    }

    @FXML
    protected void toFoodControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("FoodControl.fxml", 1080, 720);
    }

    @FXML
    protected void toOrderControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("EatingControl.fxml", 1080, 720);
    }
}
