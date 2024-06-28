package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Foods {
    private static Foods INSTANCE;
    private final ArrayList<Food> contain;
    private final Connection connection;

    private Foods() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Foods getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Foods();
        return INSTANCE;
    }

    public void addFood(Food new_food) {
        try {
            String query = "INSERT INTO `foods` (`ID`, `name`) VALUES (NULL, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_food.getName());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Food getFood(int id) {
        for (Food food : this.contain)
            if (food.getID() == id)
                return food;
        try {
            String query = "SELECT * FROM `foods` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Food food = new Food(id, rs.getString("name"));
            contain.add(food);
            return food;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setFood(int id, Food new_food) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_food);
        try {
            String query = "UPDATE `foods` SET `name` = ? WHERE `foods`.`id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_food.getName());
            statement.setInt(2, new_food.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteFood(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM foods WHERE `foods`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Food> getFoods() {
        try {
            ArrayList<Food> foods = new ArrayList<>();
            String query = "SELECT id FROM foods";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                foods.add(getFood(rs.getInt("ID")));
            return foods;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
