package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class Main {
    public static void main(String[] args) throws Exception {


        UserService userService = new UserServiceImpl();
        try {
            userService.createUsersTable();
            userService.saveUser("Max", "Yuha", (byte) 34);
            userService.saveUser("Max2", "Yuha2", (byte) 35);
            userService.saveUser("Max3", "Yuha3", (byte) 36);
            userService.saveUser("Max4", "Yuha4", (byte) 37);
            userService.removeUserById(1);
            userService.getAllUsers();
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } finally {
            getSessionFactory().close();
        }
    }
}

