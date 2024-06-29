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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.ResourceBundle;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;

public class EatingControlController implements Initializable {
    @FXML
    private TableView<Item> table_foods;
    @FXML
    private TableColumn<Item, String> col_name;
    @FXML
    private TableColumn<Item, Integer> col_amount;

    @FXML
    private ComboBox<String> table;
    @FXML
    private ComboBox<String> client;
    @FXML
    private ComboBox<String> food;

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> start;
    @FXML
    private ComboBox<String> end;

    // Ужасное решение, но в спешке, я не придумал, как лучше работать с заказами
    private Order active_order = new Order(-1, String.valueOf(Math.random() * 100000));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Orders.getInstance().addOrder(active_order);
        table_foods.setEditable(true);

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_name.setCellFactory(TextFieldTableCell.forTableColumn());

        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_amount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_amount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, Integer> event) {
                Item item = event.getRowValue();
                if (event.getNewValue() < 0) {
                    SceneManager.getInstance().alert("Отмена операции", "Количество ингредиента должно быть положительным", "Попробуйте ещё раз");
                    return;
                }
                item.setAmount(event.getNewValue());
                Items.getInstance().setItem(item.getID(), item);
            }
        });

        ObservableList<String> table_list = table.getItems();
        for (Table buff : Tables.getInstance().getTables())
            table_list.add(String.valueOf(buff.getID()));

        ObservableList<String> client_list = client.getItems();
        for (User buff : Users.getInstance().getUsers())
            client_list.add(String.valueOf(buff.getFullName()));

        ObservableList<String> food_list = food.getItems();
        for (Food buff : Foods.getInstance().getFoods())
            food_list.add(String.valueOf(buff.getName()));

        ObservableList<String> start_list = start.getItems();
        ObservableList<String> end_list = end.getItems();

        // Ещё одно ужасное решение
        start_list.add("9:00");
        start_list.add("10:00");
        start_list.add("11:00");
        start_list.add("12:00");
        start_list.add("13:00");
        start_list.add("14:00");
        start_list.add("15:00");
        start_list.add("16:00");
        start_list.add("17:00");

        end_list.add("9:00");
        end_list.add("10:00");
        end_list.add("11:00");
        end_list.add("12:00");
        end_list.add("13:00");
        end_list.add("14:00");
        end_list.add("15:00");
        end_list.add("16:00");
        end_list.add("17:00");


        this.update();
    }

    private void update() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        int code = Orders.getInstance().getOrder(active_order.getCode()).getID();
        if (Items.getInstance().getItems(code) != null)
            items.addAll(Items.getInstance().getItems(code));
        table_foods.setItems(items);
    }

    @FXML
    protected void add_food(ActionEvent event) {
        if (food.getValue() == null) {
            SceneManager.getInstance().alert("Некорректные данные", "...", "Попробуйте ещё раз");
            return;
        }
        Food buff = Foods.getInstance().getFood(food.getValue());
        Item item = new Item(-1, Orders.getInstance().getOrder(active_order.getCode()).getID(), buff.getID(), 1);
        Items.getInstance().addItem(item);
        update();
    }

    @FXML
    protected void delete_food(ActionEvent event) {
        Item item = table_foods.getSelectionModel().getSelectedItem();
        if (item == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали блюдо к удалению", "Попробуйте ещё раз");
            return;
        }
        Items.getInstance().deleteItem(item.getID());
        update();
    }

    @FXML
    protected void create_eating(ActionEvent event) throws IOException {
        if (table.getValue() == null || client.getValue() == null || date.getValue() == null || start.getValue() == null || end.getValue() == null) {
            SceneManager.getInstance().alert("Некорректные данные", "...", "Попробуйте ещё раз");
            return;
        }
        LocalDateTime ldt =  date.getValue().atTime(LocalTime.parse(start.getValue()));
        Timestamp timestamp = Timestamp.valueOf(ldt);
        long time = MINUTES.between(LocalTime.parse(start.getValue()), LocalTime.parse(end.getValue()));
        Eating eating = new Eating(-1, Integer.parseInt(table.getValue()), Users.getInstance().getCurrent().getID(), Orders.getInstance().getOrder(active_order.getCode()).getID(), timestamp, (int) time);
        Eatings.getInstance().addEating(eating);

        this.backf(event);
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