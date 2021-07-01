package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Util.getConnection();
            Util util = new Util();
            UserServiceImpl userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Ivan", "Ivanov", (byte) 14);
            userService.saveUser("Ivan", "Semenov", (byte) 44);
            userService.saveUser("Ivan", "Petrov", (byte) 33);
            userService.saveUser("Ivan", "Sviridov", (byte) 56);
            List<User> list = userService.getAllUsers();
            System.out.println(list);
            System.out.println();
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
