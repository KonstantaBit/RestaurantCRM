//package org.m0d3rn1ca.restaurantcrm.controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import org.m0d3rn1ca.restaurantcrm.Connector;
//import org.m0d3rn1ca.restaurantcrm.Person;
//import org.m0d3rn1ca.restaurantcrm.SceneManager;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.ResourceBundle;
//
//public class UserControlController implements Initializable {
//    @FXML
//    private  TextField ITN;
//    @FXML
//    private TextField last_name;
//    @FXML
//    private TextField first_name;
//    @FXML
//    private TextField patronymic;
//    @FXML
//    private TextField phone;
//    @FXML
//    private TextField address;
//    @FXML
//    private Button delete_user;
//    @FXML
//    private Button create_user;
//    @FXML
//    private Button update_user;
//    @FXML
//    private TableColumn<Person, Integer> col_id;
//    @FXML
//    private TableColumn<Person, String> col_login;
//    @FXML
//    private TableColumn<Person, String> col_last_name;
//    @FXML
//    private TableColumn<Person, String> col_first_name;
//    @FXML
//    private TableColumn<Person, String> col_patronymic;
//    @FXML
//    private TableColumn<Person, String> col_ITN;
//    @FXML
//    private TableColumn<Person, String> col_phone;
//    @FXML
//    private TableColumn<Person, String> col_address;
//    @FXML
//    private Button back;
//    @FXML
//    private Button quit;
//    @FXML
//    private TableView<Person> table;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        update();
//    }
//
//    public void update() {
//        //TableView<Person> table = new TableView<>();
//        ObservableList<Person> people = FXCollections.observableArrayList();
//
//        Connector CC = Connector.getInstance();
//        for (HashMap<String, Object> user : CC.getUsers()) {
//            Person temp = new Person(
//                    (Integer) user.get("ID"),
//                    (String) user.get("last_name"),
//                    (String) user.get("first_name"),
//                    (String) user.get("patronymic"),
//                    (String) user.get("residential_address"),
//                    (String) user.get("ITN"),
//                    (String) user.get("phone_number"),
//                    (String) user.get("login")
//            );
//            people.add(temp);
//        }
//        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
//        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
//        col_last_name.setCellValueFactory(new PropertyValueFactory<>("last"));
//        col_first_name.setCellValueFactory(new PropertyValueFactory<>("first"));
//        col_patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
//        col_ITN.setCellValueFactory(new PropertyValueFactory<>("ITN"));
//        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
//        table.setItems(people);
//    }
//
//    @FXML
//    protected void create_user(ActionEvent event) throws IOException {
//        SceneManager.getInstance().setScene("CreateUser.fxml", 1080, 720);
//    }
//
//    @FXML
//    protected void delete_user(ActionEvent actionEvent) {
//        Person p = table.getSelectionModel().getSelectedItem();
//        Connector.getInstance().deleteUser(p.getLogin());
//        update();
//    }
//
//    @FXML
//    protected void update_user(ActionEvent actionEvent) {
////        Person p = table.getSelectionModel().getSelectedItem();
////        CachedConnector.getInstance().setUser(last_name.getText(), first_name.getText(), patronymic.getText(), address.getText(), );
////        update();
//    }
//}
