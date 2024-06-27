package org.m0d3rn1ca.restaurantcrm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.m0d3rn1ca.restaurantcrm.SceneManager;
import org.m0d3rn1ca.restaurantcrm.User;
import org.m0d3rn1ca.restaurantcrm.Users;

public class CabinetController implements Initializable {
    @FXML
    private TextField ITN;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField patronymic;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
    }

    private void update() {
        User user = Users.getInstance().getCurrent();
        ITN.setText(user.getITN());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        first_name.setText(user.getFirstName());
        last_name.setText(user.getLastName());
        patronymic.setText(user.getPatronymic());
    }

    @FXML
    protected void put_data(ActionEvent event) {
        if (!SceneManager.getInstance().dialog("Предупреждение!", "Изменение данных", "Вы уверены, что хотите изменить данные?"))
            return;

        Users users = Users.getInstance();
        User user = users.getCurrent().reBuilder()
                .setITN(ITN.getText())
                .setPhone(phone.getText())
                .setAddress(address.getText())
                .setFirstName(first_name.getText())
                .setLastName(last_name.getText())
                .setPatronymic(patronymic.getText())
                .build();
        users.setCurrent(user);
        users.setUser(user.getID(), user);

        update();
    }

    @FXML
    protected void put_password(ActionEvent event) {
        if (!SceneManager.getInstance().dialog("Предупреждение!", "Изменение данных", "Вы уверены, что хотите изменить данные?"))
            return;

        Users users = Users.getInstance();
        User user = users.getCurrent().reBuilder()
                .setPassword(User.hash(password.getText()))
                .build();
        users.setCurrent(user);
        users.setUser(user.getID(), user);

        password.setText("");
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
