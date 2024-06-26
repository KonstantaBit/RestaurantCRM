package org.m0d3rn1ca.restaurantcrm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.m0d3rn1ca.restaurantcrm.CachedConnector;
import org.m0d3rn1ca.restaurantcrm.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CabinetController implements Initializable {
    @FXML
    private TextField ITN;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField full_name;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
    }

    private void update() {
        HashMap<String, Object> result = CachedConnector.getInstance().getCurrentUser();
        ITN.setText((String) result.get("ITN"));
        phone.setText((String) result.get("phone_number"));
        address.setText((String) result.get("residential_address"));
        full_name.setText(String.format("%s %s %s", result.get("last_name"), result.get("first_name"), result.get("patronymic")));
    }

    @FXML
    protected void put_data(ActionEvent event) {
        if (SceneManager.getInstance().dialog()) {
            CachedConnector CC = CachedConnector.getInstance();
            CC.setUser("*", "*", "*", address.getText(), ITN.getText(), phone.getText(), CC.getLogin());
        }
        update();
    }

    @FXML
    protected void put_password(ActionEvent event) {
        // TODO:
        update();
    }

    @FXML
    protected void backward(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("Actions.fxml", 1080, 720);
    }

    @FXML
    protected void quitf(ActionEvent event) {
        // TODO: сделать ценрализованный выход
        System.exit(0);
    }
}
