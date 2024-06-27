package org.m0d3rn1ca.restaurantcrm;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Users {
    private static Users INSTANCE;
    private final ArrayList<User> contain;
    private final Connection connection;
    private User current;

    private Users() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Users getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Users();
        return INSTANCE;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public User getUser(int id) {
        for (User user : this.contain)
            if (user.getID() == id)
                return user;
        try {
            String query = "SELECT * FROM `users` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            User user = User.newBuilder(rs.getInt("ID"), rs.getString("login"))
                    .setLastName(rs.getString("last_name"))
                    .setFirstName(rs.getString("first_name"))
                    .setPatronymic(rs.getString("patronymic"))
                    .setPhone(rs.getString("phone_number"))
                    .setITN(rs.getString("ITN"))
                    .setAddress(rs.getString("residential_address"))
                    .setPassword(rs.getString("password"))
                    .build();
            contain.add(user);
            return user;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public User getUser(String login) {
        for (User user : this.contain)
            if (Objects.equals(user.getLogin(), login))
                return user;
        try {
            String query = "SELECT * FROM `users` WHERE `login` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            User user = User.newBuilder(rs.getInt("ID"), rs.getString("login"))
                    .setLastName(rs.getString("last_name"))
                    .setFirstName(rs.getString("first_name"))
                    .setPatronymic(rs.getString("patronymic"))
                    .setPhone(rs.getString("phone_number"))
                    .setITN(rs.getString("ITN"))
                    .setAddress(rs.getString("residential_address"))
                    .setPassword(rs.getString("password"))
                    .build();
            contain.add(user);
            return user;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setUser(int id, User new_user) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_user);
        try {
            String query = "UPDATE `users` SET `last_name` = ?, `first_name` = ?, `patronymic` = ?, `residential_address` = ?, `ITN` = ?, `phone_number` = ?, `password` = ? WHERE `users`.`id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_user.getLastName());
            statement.setString(2, new_user.getFirstName());
            statement.setString(3, new_user.getPatronymic());
            statement.setString(4, new_user.getAddress());
            statement.setString(5, new_user.getITN());
            statement.setString(6, new_user.getPhone());
            statement.setString(7, new_user.getPassword());
            statement.setInt(8, new_user.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteUser(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM users WHERE `users`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }
}
