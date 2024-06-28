package org.m0d3rn1ca.restaurantcrm.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableControlController implements Initializable {
    @FXML
    private TableView<Table> table_view;
    @FXML
    private TableColumn<Table, Integer> col_id;
    @FXML
    private TableColumn<Table, Integer> col_capacity;

    @FXML
    private Button create_table;
    @FXML
    private TextField capacity;

    @FXML
    private Button delete_table;

    @FXML
    private Button back;
    @FXML
    private Button quit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
    }

    private void update() {
        table_view.setEditable(true);
        Tables tables_object = Tables.getInstance();
        ObservableList<Table> tables = FXCollections.observableArrayList();
        tables.addAll(tables_object.getTables());

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        col_capacity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_capacity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Table, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Table, Integer> event) {
                Table table = event.getRowValue();
                table.setCapacity(event.getNewValue());
                tables_object.setTable(table.getID(), table);
            }
        });
        table_view.setItems(tables);
    }

    @FXML
    protected void create_table(ActionEvent event) {
        if (capacity.getText().isBlank()) {
            SceneManager.getInstance().alert("Некорректные данные", "Поле количества мест обязательно", "Попробуйте ещё раз");
            return;
        }
        if (Integer.parseInt(capacity.getText()) < 1) {
            SceneManager.getInstance().alert("Некорректные данные", "Количество мест у столика должно быть натуральным числом", "Попробуйте ещё раз");
            return;
        }
        Table table = new Table(-1, Integer.parseInt(capacity.getText()));
        Tables.getInstance().addTable(table);
        update();
    }

    @FXML
    protected void delete_table(ActionEvent event) {
        Table table = table_view.getSelectionModel().getSelectedItem();
        if (table == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали столик к удалению", "Попробуйте ещё раз");
            return;
        }
        Tables.getInstance().deleteTable(table.getID());
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
