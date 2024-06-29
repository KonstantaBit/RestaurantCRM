package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Eatings {
    private static Eatings INSTANCE;
    private final ArrayList<Eating> contain;
    private final Connection connection;

    private Eatings() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Eatings getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Eatings();
        return INSTANCE;
    }

    public void addEating(Eating new_eating) {
        try {
            String query = "INSERT INTO `eatings` (`ID`, `table_id`, `waiter_id`, `order_id`, `timestapm`, `delta_time`) VALUES (NULL, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_eating.getTableId());
            statement.setInt(2, new_eating.getWaiterId());
            statement.setInt(3, new_eating.getOrderId());
            statement.setTimestamp(4, new_eating.getTimestamp());
            statement.setInt(5, new_eating.getDelta());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Eating getEating(int id) {
        for (Eating eating : this.contain)
            if (eating.getID() == id)
                return eating;
        try {
            String query = "SELECT * FROM `eatings` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Eating eating = new Eating(id, rs.getInt("table_id"), rs.getInt("waiter_id"), rs.getInt("order_id"), rs.getTimestamp("timestamp"), rs.getInt("delta_time"));
            contain.add(eating);
            return eating;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

//    public void setEating(int id, Eating new_eating) {
//        for (int i = 0; i < contain.size(); ++i)
//            if (contain.get(i).getID() == id)
//                contain.set(i, new_eating);
//        try {
//            String query = "UPDATE `foods` SET `name` = ? WHERE `foods`.`id` = ?;";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, new_eating.getName());
//            statement.setInt(2,new_eating.getID());
//            statement.execute();
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }

    public void deleteEating(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM eatings WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

//    public ArrayList<Food> getFoods() {
//        try {
//            ArrayList<Food> foods = new ArrayList<>();
//            String query = "SELECT id FROM foods";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.execute();
//            ResultSet rs = statement.getResultSet();
//            while (rs.next())
//                foods.add(getFood(rs.getInt("ID")));
//            return foods;
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//        return null;
//    }
}
