package org.m0d3rn1ca.restaurantcrm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        full_name.setText(String.format("%s %s %s", (String) result.get("last_name"), (String) result.get("first_name"), (String) result.get("patronymic")));
    }

    @FXML
    protected void put_data(ActionEvent event) {
        if (SceneManager.getInstance().dialog())
            System.out.println(1);
        else
            System.out.println(2);
        update();
    }
}
