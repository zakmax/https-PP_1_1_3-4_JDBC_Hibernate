package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            UserServiceImpl userDaoJDBC = new UserServiceImpl();
            userDaoJDBC.createUsersTable();
            userDaoJDBC.saveUser("Max", "Petrov", (byte) 30);
            userDaoJDBC.saveUser("Dry", "Ivanov", (byte) 20);
            userDaoJDBC.saveUser("Siy", "Sidorov", (byte) 18);
            userDaoJDBC.saveUser("Miha", "Helov", (byte) 25);
            List<User> allUsers = userDaoJDBC.getAllUsers();
            allUsers.forEach(System.out::println);
            userDaoJDBC.removeUserById(1);
            List<User> allUsers2 = userDaoJDBC.getAllUsers();
            allUsers2.forEach(System.out::println);
            userDaoJDBC.cleanUsersTable();
            userDaoJDBC.dropUsersTable();
        } finally {
            getConnection().close();
        }
    }
}

