package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();
    private Util util = new Util();
    private Statement statement;

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() throws SQLException {

        reopenConnection();
        statement = connection.createStatement();
        statement.executeUpdate("use users");
        statement.executeUpdate("CREATE TABLE if not exists `users`.`user` (`id` BIGINT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL,`lastName` VARCHAR(45) NULL," +
                "`age` INT NULL, PRIMARY KEY (`id`))");
        statement.close();
        connection.close();
    }

    public void dropUsersTable() throws SQLException {

        reopenConnection();
        statement = connection.createStatement();
        statement.executeUpdate("use users");
        statement.executeUpdate("drop table if exists user");
        statement.close();
        connection.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            reopenConnection();
            statement = connection.createStatement();
            statement.executeUpdate("use users");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name, lastName, age) values (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных.");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            reopenConnection();
            statement = connection.createStatement();
            statement.executeUpdate("use users");
            PreparedStatement preparedStatement = connection.prepareStatement("delete user where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            reopenConnection();
            statement = connection.createStatement();
            statement.executeUpdate("use users");
            ResultSet res = statement.executeQuery("select * from user");
            while (res.next()) {
                list.add(new User(res.getString(2), res.getString(3), res.getByte(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            reopenConnection();
            statement = connection.createStatement();
            statement.executeUpdate("use users");
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            statement.close();
            connection.close();

        }
    }

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = Util.getConnection();
        }
    }
}
