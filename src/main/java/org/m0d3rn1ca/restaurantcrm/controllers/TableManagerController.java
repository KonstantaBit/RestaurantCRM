//package org.m0d3rn1ca.restaurantcrm.controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import org.m0d3rn1ca.restaurantcrm.Connector;
//import org.m0d3rn1ca.restaurantcrm.Table;
//
//import java.net.URL;
//import java.util.HashMap;
//import java.util.ResourceBundle;
//
//public class TableManagerController implements Initializable {
//    @FXML
//    private TableView<Table> table_view;
//    @FXML
//    private TableColumn<Table, Integer> col_id;
//    @FXML
//    private TableColumn<Table, Integer> col_capacity;
//
//    @FXML
//    private Button create_table;
//    @FXML
//    private TextField capacity;
//
//    @FXML
//    private Button delete_table;
//
//    @FXML
//    private Button back;
//    @FXML
//    private Button quit;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.update();
//    }
//
//    private void update() {
//        ObservableList<Table> tables = FXCollections.observableArrayList();
//
//        for (HashMap<String, Object> user : Connector.getInstance().getUsers()) {
//            Table temp = new Table(
//                    (Integer) user.get("ID"),
//                    (Integer) user.get("capacity")
//            );
//            tables.add(temp);
//        }
//        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
//        col_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
//        table_view.setItems(tables);
//    }
//
//    @FXML
//    protected void create_table(ActionEvent event) {
//       Connector.getInstance()
//    }
//
//
//}
