package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Positions {
    private static Positions INSTANCE;
    private final ArrayList<Position> contain;
    private final Connection connection;

    private Positions() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Positions getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Positions();
        return INSTANCE;
    }

    public void addPosition(Position new_position) {
        try {
            String query = "INSERT INTO `users_roles` (`ID`, `user_id`, `role_id`) VALUES (NULL, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, new_position.getUserId());
            statement.setInt(1, new_position.getRoleId());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Position getPosition(int id) {
        for (Position position : this.contain)
            if (position.getID() == id)
                return position;
        try {
            String query = "SELECT * FROM `users_roles` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Position position = new Position(id, rs.getInt("user_id"), rs.getInt("role_id"));
            contain.add(position);
            return position;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void deletePosition(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM `users_roles` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Position> getPositions(int user_id) {
        try {
            ArrayList<Position> positions = new ArrayList<>();
            String query = "SELECT ID FROM `users_roles` WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                positions.add(getPosition(rs.getInt("ID")));
            return positions;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
