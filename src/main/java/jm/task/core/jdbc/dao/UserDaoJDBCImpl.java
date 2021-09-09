package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190685";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));";

            statement.executeUpdate(sql);

            System.out.println("Table \"User\" create successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"User\" not create!");
            sqlEx.printStackTrace();
        }
    }


    public void dropUsersTable() {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "DROP TABLE IF EXISTS User";
            statement.executeUpdate(sql);

            System.out.println("Table \"User\" drop successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"User\" not drop!");
            sqlEx.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "INSERT INTO User (name, lastName, age) Values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void removeUserById(long id) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "DELETE FROM User WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User под id " + id + " удален из базы данных");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "SELECT * FROM User";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {

                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getByte(4));
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM User";
            statement.executeUpdate(sql);

            System.out.println("Table \"User\" clean successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"User\" not clean!");
            sqlEx.printStackTrace();
        }
    }
}
