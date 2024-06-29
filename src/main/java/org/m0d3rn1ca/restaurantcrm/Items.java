package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Items {
    private static Items INSTANCE;
    private final ArrayList<Item> contain;
    private final Connection connection;

    private Items() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Items getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Items();
        return INSTANCE;
    }

    public void addItem(Item new_item) {
        try {
            String query = "INSERT INTO `items` (`order_id`, `food_id`, `amount`) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_item.getOrderId());
            statement.setInt(2, new_item.getFoodId());
            statement.setInt(3, new_item.getAmount());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Item getItem(int id) {
        for (Item item : this.contain)
            if (item.getID() == id)
                return item;
        try {
            String query = "SELECT * FROM `items` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Item item = new Item(id, rs.getInt("order_id"), rs.getInt("food_id"), rs.getInt("amount"));
            contain.add(item);
            return item;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setItem(int id, Item new_item) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_item);
        try {
            String query = "UPDATE `items` SET `amount` = ? WHERE `id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_item.getAmount());
            statement.setInt(2, new_item.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteItem(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM items WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Item> getItems(int order_id) {
        try {
            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT id FROM items WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, order_id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                items.add(getItem(rs.getInt("ID")));
            return items;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
