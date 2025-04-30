package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class Main {
    public static void main(String[] args) throws Exception {


        UserServiceImpl userDaoHibernate = new UserServiceImpl();
        try {
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser("Max", "Yuha", (byte) 34);
            userDaoHibernate.saveUser("Max2", "Yuha2", (byte) 35);
            userDaoHibernate.saveUser("Max3", "Yuha3", (byte) 36);
            userDaoHibernate.saveUser("Max4", "Yuha4", (byte) 37);
            userDaoHibernate.removeUserById(1);
            userDaoHibernate.getAllUsers();
            userDaoHibernate.cleanUsersTable();
            userDaoHibernate.dropUsersTable();
        } finally {
            getSessionFactory().close();
        }
    }
}

