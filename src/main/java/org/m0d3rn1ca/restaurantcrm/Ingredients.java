package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ingredients {
    private static Ingredients INSTANCE;
    private final ArrayList<Ingredient> contain;
    private final Connection connection;

    private Ingredients() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Ingredients getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Ingredients();
        return INSTANCE;
    }

    public void addIngredient(Ingredient ingredient) {
        try {
            String query = "INSERT INTO `ingredients` (`ID`, `unit`, `name`, `amount`) VALUES (NULL, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ingredient.getUnit());
            statement.setString(2, ingredient.getName());
            statement.setFloat(3, ingredient.getAmount());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Ingredient getIngredient(int id) {
        for (Ingredient ingredient : this.contain)
            if (ingredient.getID() == id)
                return ingredient;
        try {
            String query = "SELECT * FROM `ingredients` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Ingredient ingredient = new Ingredient(
                    id,
                    rs.getString("unit"),
                    rs.getString("name"),
                    rs.getFloat("amount")
            );
            contain.add(ingredient);
            return ingredient;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public Ingredient getIngredient(String name) {
        for (Ingredient ingredient : this.contain)
            if (ingredient.getName().equals(name))
                return ingredient;
        try {
            String query = "SELECT * FROM `ingredients` WHERE `name` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Ingredient ingredient = new Ingredient(
                    rs.getInt("ID"),
                    rs.getString("unit"),
                    rs.getString("name"),
                    rs.getFloat("amount")
            );
            contain.add(ingredient);
            return ingredient;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setIngredient(int id, Ingredient new_ingredient) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_ingredient);
        try {
            String query = "UPDATE `ingredients` SET `amount` = ? WHERE `ingredients`.`id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, new_ingredient.getAmount());
            statement.setInt(2, new_ingredient.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteIngredient(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM `ingredients` WHERE `ingredients`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        try {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String query = "SELECT `id` FROM `ingredients`";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                ingredients.add(getIngredient(rs.getInt("ID")));
            return ingredients;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
