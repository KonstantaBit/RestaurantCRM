package org.m0d3rn1ca.restaurantcrm.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UserControlController implements Initializable {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> col_id;
    @FXML
    private TableColumn<User, String> col_login;
    @FXML
    private TableColumn<User, String> col_last_name;
    @FXML
    private TableColumn<User, String> col_first_name;
    @FXML
    private TableColumn<User, String> col_patronymic;
    @FXML
    private TableColumn<User, String> col_ITN;
    @FXML
    private TableColumn<User, String> col_phone;
    @FXML
    private TableColumn<User, String> col_address;

    @FXML
    private TextField login;
    @FXML
    private TextField last_name;
    @FXML
    private TextField first_name;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField ITN;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField password;



    @FXML
    private Button delete_user;
    @FXML
    private Button create_user;
    @FXML
    private Button edit_role;

    @FXML
    private Button back;
    @FXML
    private Button quit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }

    public void update() {
        Users users_object = Users.getInstance();

        table.setEditable(true);
        ObservableList<User> users = FXCollections.observableArrayList();

        users.addAll(Users.getInstance().getUsers());

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));

        col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_last_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_last_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setLastName(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_first_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_first_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setFirstName(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        col_patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        col_patronymic.setCellFactory(TextFieldTableCell.forTableColumn());
        col_patronymic.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setPatronymic(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        col_ITN.setCellValueFactory(new PropertyValueFactory<>("ITN"));
        col_ITN.setCellFactory(TextFieldTableCell.forTableColumn());
        col_ITN.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setITN(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_phone.setCellFactory(TextFieldTableCell.forTableColumn());
        col_phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setPhone(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_address.setCellFactory(TextFieldTableCell.forTableColumn());
        col_address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setAddress(event.getNewValue());
                users_object.setUser(user.getID(), user);
            }
        });

        table.setItems(users);
    }

    @FXML
    protected void create_user(ActionEvent event) {
        if (login.getText().isBlank() || password.getText().isBlank()) {
            SceneManager.getInstance().alert("Некорректные данные", "Поля логин и пароль обязательные", "Попробуйте ещё раз");
            return;
        }
        User user = User.newBuilder(-1, login.getText())
                .setLastName(last_name.getText())
                .setFirstName(first_name.getText())
                .setPatronymic(patronymic.getText())
                .setAddress(address.getText())
                .setPhone(phone.getText())
                .setITN(ITN.getText())
                .setPassword(User.hash(password.getText()))
                .build();
        Users.getInstance().addUser(user);
        update();
    }

    @FXML
    protected void edit_role(ActionEvent event) {

    }

    @FXML
    protected void delete_user(ActionEvent event) {
        User user = table.getSelectionModel().getSelectedItem();
        if (user == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали пользователя к удалению", "Попробуйте ещё раз");
            return;
        }
        Users.getInstance().deleteUser(user.getID());
        update();
    }

    @FXML
    protected void backf(ActionEvent event) throws IOException {
        SceneManager.getInstance().setScene("Actions.fxml", 1080, 720);
    }

    @FXML
    protected void quitf(ActionEvent event) {
        // TODO: сделать ценрализованный выход
        System.exit(0);
    }
}
