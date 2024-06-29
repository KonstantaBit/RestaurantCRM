package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Orders {
    private static Orders INSTANCE;
    private final ArrayList<Order> contain;
    private final Connection connection;

    private Orders() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Orders getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Orders();
        return INSTANCE;
    }

    public void addOrder(Order new_order) {
        try {
            String query = "INSERT INTO `orders` (`ID`, `code`) VALUES (NULL, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_order.getCode());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

//    public Order getOrder(int id) {
//        for (Order order : this.contain)
//            if (order.getID() == id)
//                return order;
//        try {
//            String query = "SELECT * FROM `orders` WHERE `id` = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, id);
//            statement.execute();
//            ResultSet rs = statement.getResultSet();
//            rs.next();
//            Order order = new Order(id, rs.getInt("code"));
//            contain.add(order);
//            return order;
//        } catch (SQLException exception) {
//            // TODO логирование
//            System.out.println(exception.toString());
//        }
//        return null;
//    }

    public Order getOrder(String code) {
        for (Order order : this.contain)
            if (order.getCode().equals(code))
                return order;
        try {
            String query = "SELECT * FROM `orders` WHERE `code` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Order order = new Order(rs.getInt("ID"), code);
            contain.add(order);
            return order;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void deleteOrder(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM orders WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

//    public ArrayList<Item> getOrders() {
//        try {
//            ArrayList<Item> items = new ArrayList<>();
//            String query = "SELECT id FROM tables";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.execute();
//            ResultSet rs = statement.getResultSet();
//            while (rs.next())
//                items.add(getItem(rs.getInt("ID")));
//            return items;
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//        return null;
//    }
}
