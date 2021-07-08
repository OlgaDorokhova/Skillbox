package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Noo", "Double", (byte) 23);
        userService.saveUser("Boom", "Inter", (byte) 65);
        userService.saveUser("Vok", "Hooch", (byte) 26);
        userService.saveUser("Fake", "Strong", (byte) 46);

        userService.removeUserById(2);
        List<User> list = userService.getAllUsers();
        list.forEach(System.out :: println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        factory.close();
    }
}
