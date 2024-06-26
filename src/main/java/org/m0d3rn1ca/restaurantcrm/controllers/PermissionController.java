package org.m0d3rn1ca.restaurantcrm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.m0d3rn1ca.restaurantcrm.Connector;
import org.m0d3rn1ca.restaurantcrm.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermissionController implements Initializable {
    @FXML
    private Button cabinet;
    @FXML
    private Button user_control;
    @FXML
    private Button ingredients;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connector cc = Connector.getInstance();
//        ArrayList<HashMap<String, Object>> result = cc.getUserRoles(cc.getLogin());
//        for (HashMap<String, Object> element : result) {
//
//        }
    }

    @FXML
    protected void toCabinet(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("Cabinet.fxml", 1080, 720);
    }

    @FXML
    protected void toUserControl(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("UserControl.fxml", 1080, 720);
    }
}
