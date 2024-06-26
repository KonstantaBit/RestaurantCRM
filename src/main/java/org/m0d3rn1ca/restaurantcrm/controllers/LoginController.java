package org.m0d3rn1ca.restaurantcrm.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.m0d3rn1ca.restaurantcrm.SceneManager;
import org.m0d3rn1ca.restaurantcrm.User;
import org.m0d3rn1ca.restaurantcrm.Users;

public class LoginController {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox remember_me;
    @FXML
    private Label error;

    @FXML
    protected void checkData(ActionEvent event) throws IOException {
        Users users = Users.getInstance();
        User user = users.getUser(login.getText());
        if (user == null) {
            error.setText("Пользователя с таким логином не существует!");
            error.setVisible(true);
            return;
        }
        String password_hash = User.hash(password.getText());
        if (user.getPassword().equals(password_hash)) {
            users.setCurrent(user);
            SceneManager.getInstance().setScene("Actions.fxml", 1080, 720);
        } else {
            error.setText("Неверный пароль!");
            error.setVisible(true);
        }
    }
}