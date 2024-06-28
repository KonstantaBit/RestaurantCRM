package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tables {
    private static Tables INSTANCE;
    private final ArrayList<Table> contain;
    private final Connection connection;

    private Tables() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Tables getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Tables();
        return INSTANCE;
    }

    public void addTable(Table table) {
        try {
            String query = "INSERT INTO `tables` (`ID`, `capacity`) VALUES (NULL, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, table.getCapacity());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Table getTable(int id) {
        for (Table table : this.contain)
            if (table.getID() == id)
                return table;
        try {
            String query = "SELECT * FROM `tables` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Table table = new Table(id, rs.getInt("capacity"));
            contain.add(table);
            return table;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setTable(int id, Table new_table) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_table);
        try {
            String query = "UPDATE `tables` SET `capacity` = ? WHERE `tables`.`id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_table.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteTable(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM tables WHERE `tables`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Table> getTables() {
        try {
            ArrayList<Table> users = new ArrayList<>();
            String query = "SELECT id FROM tables";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                users.add(getTable(rs.getInt("ID")));
            return users;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
