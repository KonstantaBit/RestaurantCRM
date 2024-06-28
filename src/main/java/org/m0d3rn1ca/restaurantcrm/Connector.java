package org.m0d3rn1ca.restaurantcrm;

import java.sql.*;

public class Connector {
    private static Connector INSTANCE;
    private Connection connection;

    private Connector() {
        // TODO: сделать возможность выбирать сервер
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chuh",
                    "root", "");
        } catch (SQLException exception) {
            // Уведомление об невозможности подключения к серверу
            SceneManager.getInstance().alert(
                    "Критическая ошибка!",
                    "Невозможно подключиться к базе данных",
                    "Попробуйте снова через 15 минут"
            );
            // TODO: сделать логирование
        }
    }

    public static Connector getInstance() {
        // TODO: сделать логирование
        if (INSTANCE == null)
            INSTANCE = new Connector();
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        // TODO: сделать логирование
        try {
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }
//    // CRUDA User 1
//    public HashMap<String, Object> getUser(String login) {
//        try {
//            ArrayList<String> input = new ArrayList<>();
//            input.add(login);
//
//            if (cacheOperator.checkCache(1, input))
//                return cacheOperator.getCache(1, input).getFirst();
//
//            String query = "SELECT * FROM `users` WHERE `login` = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, login);
//            statement.execute();
//            ResultSet result = statement.getResultSet();
//
//            cacheOperator.addCache(1, input, result);
//
//            return cacheOperator.getCache(1, input).getFirst();
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//        return null;
//    }
//
//    public void setUser(String last_name, String first_name, String patronymic, String residential_address, String ITN, String phone_number, String login) { // User U
//        try {
//            String query = "UPDATE `users` SET `last_name` = ?, `first_name` = ?, `patronymic` = ?, `residential_address` = ?, `ITN` = ?, `phone_number` = ? WHERE `users`.`login` = ?;";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, last_name);
//            statement.setString(2, first_name);
//            statement.setString(3, patronymic);
//            statement.setString(4, residential_address);
//            statement.setString(5, ITN);
//            statement.setString(6, phone_number);
//            statement.setString(7, login);
//            statement.execute();
//            cacheOperator.removeCache(1);
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }
//
//    public void addUser(String last_name, String first_name, String patronymic, String residential_address, String ITN, String phone_number, String login, String password) { // User C
//        try {
//            String query = "INSERT INTO `users` (`ID`, `last_name`, `first_name`, `patronymic`, `residential_address`, `ITN`, `phone_number`, `login`, `password`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, last_name);
//            statement.setString(2, first_name);
//            statement.setString(3, patronymic);
//            statement.setString(4, residential_address);
//            statement.setString(5, ITN);
//            statement.setString(6, phone_number);
//            statement.setString(7, login);
//            statement.setString(8, password);
//            statement.execute();
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }public void deleteUser(String login) { // User D
////        try {
////            String query = "DELETE FROM users WHERE `users`.`login` = ?";
////            PreparedStatement statement = connection.prepareStatement(query);
////            statement.setString(1, login);
////            statement.execute();
////            cacheOperator.removeCache(1);
////        } catch (SQLException exception) {
////            System.out.println(exception.toString());
////        }
////    }
//
//
//
//    public ArrayList<HashMap<String, Object>> getUsers() { // User A
//        try {
//            String query = "SELECT login FROM `users`;";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.execute();
//            ResultSet result = statement.getResultSet();
//
//            ArrayList<HashMap<String, Object>> response = new ArrayList<>();
//
//            for (HashMap<String, Object> temp : cacheOperator.convertResultSetToList(result))
//                response.add(getUser((String) temp.get("login")));
//            return response;
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//
//        return null;
//    }
//    // Roles CRUDA 2
//    public ArrayList<HashMap<String, Object>> getUserRoles(String login) { // UserRole R
//        try {
//            String query = "SELECT name FROM `roles` INNER JOIN `users_roles` ON role_id = id INNER JOIN `users` ON user_id = users.id WHERE login = ?;";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, login);
//            statement.execute();
//            ResultSet result = statement.getResultSet();
//
//            if (result.isBeforeFirst()) { // Проверка на наличие данных
//                cacheOperator.addCache(2, new ArrayList<String>(List.of(new String[]{login})), result);
//                return cacheOperator.getCache(2, new ArrayList<String>(List.of(new String[]{login})));
//            }
//            cacheOperator.addCache(2, new ArrayList<String>(List.of(new String[]{login})), (ResultSet) null);
//            return null;
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//        return null;
//    }
//    // Ingredients CRUDA 3
//    public void addIngredient(String name, String unit, String amount) { // Ingredient C
//        try {
//            String query = "INSERT INTO `ingredients` (`unit`, `name`, `amount`) VALUES (?, ?, ?);";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, unit);
//            statement.setString(2, name);
//            statement.setString(3, amount);
//            statement.execute();
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }
//
//    public HashMap<String, Object> getIngredient(String name) { // Ingredient R
//        try {
//            ArrayList<String> input = new ArrayList<>();
//            input.add(name);
//
//            if (cacheOperator.checkCache(3, input))
//                return cacheOperator.getCache(3, input).getFirst();
//
//            String query = "SELECT * FROM `ingredients` WHERE `name` = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, name);
//            statement.execute();
//            ResultSet result = statement.getResultSet();
//
//            cacheOperator.addCache(3, input, result);
//
//            return cacheOperator.getCache(3, input).getFirst();
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//        return null;
//    }
//
//    public void setIngredient(String name, String unit, String amount) { // Ingredient U
//        try {
//            String query = "UPDATE `ingredients` SET `amount` = ?, `unit` = ? WHERE `ingredients`.`name` = ?;";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, amount);
//            statement.setString(2, unit);
//            statement.setString(3, name);
//            statement.execute();
//            cacheOperator.removeCache(3);
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }
//
//    public void deleteIngredient(String login) { // Ingredient D
//        try {
//            String query = "DELETE FROM users WHERE `users`.`login` = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, login);
//            statement.execute();
//            cacheOperator.removeCache(1);
//        } catch (SQLException exception) {
//            System.out.println(exception.toString());
//        }
//    }
//
//    // Рабочие функции
//    public String getLogin() {
//        return current_login;
//    }
//
//    public void setLogin(String login) {
//        this.current_login = login;
//    }
//
//    public HashMap<String, Object> getCurrentUser() {
//        return getUser(current_login);
//    }
}
