package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3307/my_database";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user_password";

    public static Connection getConnection() throws Exception {

        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Creating connection to database...");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}



