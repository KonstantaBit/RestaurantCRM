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
import org.m0d3rn1ca.restaurantcrm.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodControlController implements Initializable {
    @FXML
    private TableView<Food> table_foods;
    @FXML
    private TableColumn<Food, Integer> col_id;
    @FXML
    private TableColumn<Food, String> col_name;

    @FXML
    private TableView<Recipe> table_recipe;
    @FXML
    private TableColumn<Recipe, String> col_ing;
    @FXML
    private TableColumn<Recipe, Float> col_amount;
    @FXML
    private TableColumn<Recipe, String> col_unit;

    @FXML
    private ComboBox<String> ing;

    @FXML
    private TextField food_name;

    private Food selected = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_foods.setEditable(true);
        table_recipe.setEditable(true);

        table_foods.setOnMousePressed(e ->{
            if (e.isPrimaryButtonDown() ){
                selected = table_foods.getSelectionModel().getSelectedItem();
                update();
            }
        });

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Food, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Food, String> event) {
                Food food = event.getRowValue();
                food.setName(event.getNewValue());
                Foods.getInstance().setFood(food.getID(), food);
            }
        });

        col_ing.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));


        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_amount.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        this.update();
    }

    private void update() {
        Foods foods_object = Foods.getInstance();
        Recipes recipes_object = Recipes.getInstance();

        ObservableList<Food> foods = FXCollections.observableArrayList();
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        foods.addAll(foods_object.getFoods());

        recipes.clear();
        if (selected != null)
            recipes.addAll(recipes_object.getRecipes(selected.getID()));

        table_foods.setItems(foods);
        table_recipe.setItems(recipes);

        ObservableList<String> list = ing.getItems();
        list.clear();

        for (Ingredient ingredient : Ingredients.getInstance().getIngredients()) {
            boolean flag = true;
            for (Recipe rec : recipes) {
                if (rec.getIngredientId() == ingredient.getID()) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                list.add(ingredient.getName());
        }

        col_amount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Recipe, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Recipe, Float> event) {
                Recipe recipe = event.getRowValue();
                if (event.getNewValue() < 0) {
                    SceneManager.getInstance().alert("Отмена операции", "Количество ингредиента должно быть положительным", "Попробуйте ещё раз");
                    return;
                }
                recipe.setAmount(event.getNewValue());
                recipes_object.setRecipe(recipe.getFoodId(), recipe);
            }
        });
    }

    @FXML
    protected void create_food(ActionEvent event) {
        if (food_name.getText().isBlank()) {
            SceneManager.getInstance().alert("Некорректные данные", "...", "Попробуйте ещё раз");
            return;
        }
        Food food = new Food(-1, food_name.getText());
        Foods.getInstance().addFood(food);
        update();
    }

    @FXML
    protected void delete_food(ActionEvent event) {
        Food food = table_foods.getSelectionModel().getSelectedItem();
        if (food == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали блюдо к удалению", "Попробуйте ещё раз");
            return;
        }
        Foods.getInstance().deleteFood(food.getID());
        update();
    }

    @FXML
    protected void create_ing(ActionEvent event) {
        if (ing.getValue() == null) {
            SceneManager.getInstance().alert("Некорректные данные", "Поле ... обязательно", "Попробуйте ещё раз");
            return;
        }
        Food buff_f = table_foods.getSelectionModel().getSelectedItem();
        Ingredient buff_i = Ingredients.getInstance().getIngredient(ing.getValue());
        Recipe recipe = new Recipe(-1, 1, buff_f.getID(), buff_i.getID());
        Recipes.getInstance().addRecipe(recipe);
        update();
    }

    @FXML
    protected void delete_ing(ActionEvent event) {
        Recipe recipe = table_recipe.getSelectionModel().getSelectedItem();
        if (recipe == null) {
            SceneManager.getInstance().alert("Отмена операции", "Вы не выбрали ингредиент к удалению", "Попробуйте ещё раз");
            return;
        }
        Recipes.getInstance().deleteRecipe(recipe.getID());
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

