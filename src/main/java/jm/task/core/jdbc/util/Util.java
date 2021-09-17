package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190685";
    private static Connection connection;

    public static Connection myDbConnection() {

        if (connection == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connection to DB successfully!");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Connection to DB failed");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
