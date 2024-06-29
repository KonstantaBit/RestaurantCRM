package org.m0d3rn1ca.restaurantcrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Roles {
    private static Roles INSTANCE;
    private final ArrayList<Role> contain;
    private final Connection connection;

    private Roles() {
        this.contain = new ArrayList<>();
        this.connection = Connector.getInstance().getConnection();
    }

    public static Roles getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Roles();
        return INSTANCE;
    }

    public void addRole(Role new_role) {
        try {
            String query = "INSERT INTO `roles` (`ID`, `name`) VALUES (NULL, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_role.getName());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public Role getRole(int id) {
        for (Role role : this.contain)
            if (role.getID() == id)
                return role;
        try {
            String query = "SELECT * FROM `roles` WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            Role role = new Role(id, rs.getString("name"));
            contain.add(role);
            return role;
        } catch (SQLException exception) {
            // TODO логирование
            System.out.println(exception.toString());
        }
        return null;
    }

    public void setRole(int id, Role new_role) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.set(i, new_role);
        try {
            String query = "UPDATE `roles` SET `name` = ? WHERE `id` = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, new_role.getName());
            statement.setInt(2, new_role.getID());
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public void deleteRole(int id) {
        for (int i = 0; i < contain.size(); ++i)
            if (contain.get(i).getID() == id)
                contain.remove(contain.get(i));
        try {
            String query = "DELETE FROM roles WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Role> getRoles() {
        try {
            ArrayList<Role> roles = new ArrayList<>();
            String query = "SELECT id FROM roles";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                roles.add(getRole(rs.getInt("ID")));
            return roles;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
