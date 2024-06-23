package org.m0d3rn1ca.restaurantcrm;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CachedConnector {
    private static CachedConnector INSTANCE;
    private Connection connection;
    private CacheOperator cacheOperator;
    private CachedConnector() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chuh",
                    "root", "");
            cacheOperator = new CacheOperator();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public static CachedConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CachedConnector();
        }
        return INSTANCE;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

    public HashMap<String, Object> getUserByLogin(String login) {
        try {
            String query = "SELECT * FROM `users` WHERE `login` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.execute();
            ResultSet result = statement.getResultSet();

            if (result.isBeforeFirst()) { // Проверка на наличие данных
                cacheOperator.addCache(1, new ArrayList<String>(List.of(new String[]{login})), result);
                return cacheOperator.getCache(1, new ArrayList<String>(List.of(new String[]{login}))).getFirst();
            } else {
                cacheOperator.addCache(1, new ArrayList<String>(List.of(new String[]{login})), null);
                return null;
            }
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }
}
