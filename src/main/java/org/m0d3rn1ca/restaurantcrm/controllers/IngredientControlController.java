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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

public class IngredientControlController implements Initializable {
    @FXML
    private TableView<Ingredient> table_view;
    @FXML
    private TableColumn<Ingredient, Integer> col_id;
    @FXML
    private TableColumn<Ingredient, String> col_name;
    @FXML
    private TableColumn<Ingredient, Float> col_amount;
    @FXML
    private TableColumn<Ingredient, String> col_unit;

    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> combo_unit;
    @FXML
    private TextField amount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
    }

    private void update() {
        table_view.setEditable(true);
        Ingredients ingredients_object = Ingredients.getInstance();
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        ingredients.addAll(ingredients_object.getIngredients());

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_amount.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        col_amount.setOnEditCommit(event -> {
            Ingredient ingredient = event.getRowValue();
            ingredient.setAmount(event.getNewValue());
            ingredients_object.setIngredient(ingredient.getID(), ingredient);
        });

        ObservableList<String> list = combo_unit.getItems();
        list.clear();
        list.add(" гр.");
        list.add("унц.");
        list.add("мл.");
        list.add("ст. лож.");
        list.add("чайн. лож.");
        list.add("шт.");

        table_view.setItems(ingredients);
    }

    @FXML
    protected void create_ing(ActionEvent event) {
        if (name.getText().isBlank() || combo_unit.getValue().isBlank() || amount.getText().isBlank()) {
            SceneManager.getInstance().alert("Некорректные данные", "Все поля обязательны", "Попробуйте ещё раз");
            return;
        }
        if (Float.parseFloat(amount.getText()) < 0) {
            SceneManager.getInstance().alert("Некорректные данные", "Количество ингредиента должно быть положительным числом", "Попробуйте ещё раз");
            return;
        }
        Ingredient ingredient = new Ingredient(-1, combo_unit.getValue(), name.getText(), Float.parseFloat(amount.getText()));
        Ingredients.getInstance().addIngredient(ingredient);
        update();
    }

    @FXML
    protected void delete_ing(ActionEvent event) {
        Ingredient ingredient = table_view.getSelectionModel().getSelectedItem();
        if (ingredient == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали ингридиент к удалению", "Попробуйте ещё раз");
            return;
        }
        Ingredients.getInstance().deleteIngredient(ingredient.getID());
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
