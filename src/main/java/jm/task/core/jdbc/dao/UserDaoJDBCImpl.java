package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {

        try (Statement statement = getConnection().createStatement()) {

            String SQL = """
                    CREATE TABLE IF NOT EXISTS user
                    ( id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(20) NOT NULL,
                         lastName VARCHAR(20),
                         age INT)
                    """;
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() {

        try (Statement statement = getConnection().createStatement()) {

            String SQL1 = "DROP TABLE IF EXISTS user";
            statement.executeUpdate(SQL1);
            System.out.println(" ТАБЛИЦА ЮЗЕР УДАЛЕНА");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public void saveUser(String name, String lastName, byte age) {

        String insertUserSQL = "INSERT INTO user ( name, lastName, age) VALUES ( ?, ?, ?)";
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(insertUserSQL)) {

            // Добавление первого пользователя
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем —" + " " + name + " " + "добавлен в базу данных.");





        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM user WHERE id = ?";

        try (
             PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

            // Устанавливаем значение id для удаления
            preparedStatement.setLong(1, id);


            // Выполняем команду удаления
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь с id " + id + " успешно удалён.");
            } else {
                System.out.println("Пользователь с id " + id + " не найден.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

        public List<User> getAllUsers() {
            List<User>  spisok = new ArrayList<>();
            try {


                String sql = "SELECT * FROM user";
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                // Обрабатываем результаты запроса
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");

                    User user = new User( id, name,lastName,age);


                    spisok.add(user);


                    System.out.println("ID: " + id + ", Name: " + name + ", Last Name: " + lastName + ", Age: " + age);

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return spisok ;
        }


        public void cleanUsersTable () {
            String sql = "TRUNCATE TABLE user";

            try {
                getConnection().createStatement().executeUpdate(sql);
                System.out.println("таблица очищена");

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
