package org.m0d3rn1ca.restaurantcrm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox remember_me;

    @FXML
    private Button submit;

    @FXML
    protected void checkData(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        CachedConnector connector = CachedConnector.getInstance();
        HashMap<String, Object> result = connector.getUserByLogin(login.getText());
        if (result == null)
            return;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encoded_hash = digest.digest(password.getText().getBytes(StandardCharsets.UTF_8));
        String password_hash = new String(encoded_hash, StandardCharsets.UTF_8);
        System.out.println(password_hash);
        if (result.get("password").equals(password_hash)) {
            connector.setLogin(login.getText());
            SceneManager.getInstance().setScene("Cabinet.fxml", 1080, 720);
        }
    }
}