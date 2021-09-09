package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    public static void myDbConnection() throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "190685";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.createStatement();
            System.out.println("Connection to DB successfully!");

        } catch (Exception e) {
            System.out.println("Connection to DB failed");
            e.printStackTrace();
        }
    }
}
