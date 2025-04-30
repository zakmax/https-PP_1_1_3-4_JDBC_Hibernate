package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class Util {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3307/my_database";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user_password";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws Exception {

        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Creating connection to database...");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private Util() {}

    public static SessionFactory getSessionFactory(){
        if (Objects.isNull(sessionFactory)){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3307/my_database");
        configuration.setProperty("hibernate.connection.username", "user");
        configuration.setProperty("hibernate.connection.password", "user_password");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }
}






