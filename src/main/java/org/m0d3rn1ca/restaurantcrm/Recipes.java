package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Recipes {
    private static Recipes INSTANCE;
    private final ArrayList<Recipe> contain;
    private final Connection connection;

    private Recipes() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Recipes getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Recipes();
        return INSTANCE;
    }

    public void addRecipe(Recipe new_recipe) {
        try {
            String query = "INSERT INTO `recipes` (`food_id`, `ingredient_id`, `amount`) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_recipe.getFoodId());
            statement.setInt(2, new_recipe.getIngredientId());
            statement.setFloat(3, new_recipe.getAmount());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Recipe getRecipe(int id) {
        for (Recipe recipe: this.contain)
            if (recipe.getID() == id)
                return recipe;
        try {
            String query = "SELECT * FROM `recipes` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Recipe recipe = new Recipe(id, rs.getInt("amount"), rs.getInt("food_id"), rs.getInt("ingredient_id"));
            contain.add(recipe);
            return recipe;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setRecipe(int id, Recipe new_recipe) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_recipe);
        try {
            String query = "UPDATE `recipes` SET `amount` = ?  WHERE `id` = ? ;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, new_recipe.getAmount());
            statement.setInt(2, new_recipe.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteRecipe(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM `recipes` WHERE `ID` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Recipe> getRecipes(int food_id) {
        try {
            ArrayList<Recipe> recipes = new ArrayList<>();
            String query = "SELECT ID FROM recipes WHERE `food_id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, food_id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                recipes.add(getRecipe(rs.getInt("ID")));
            return recipes;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
