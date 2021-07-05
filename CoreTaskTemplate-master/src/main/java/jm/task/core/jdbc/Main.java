package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl udh = new UserServiceImpl();
        udh.createUsersTable();
        udh.saveUser("Noo", "Double", (byte) 23);
        udh.saveUser("Boom", "Inter", (byte) 65);
        udh.saveUser("Vok", "Hooch", (byte) 26);
        udh.saveUser("Fake", "Strong", (byte) 46);

        udh.removeUserById(2);
        List<User> list = udh.getAllUsers();
        list.forEach(System.out :: println);
        udh.cleanUsersTable();
        udh.dropUsersTable();
        factory.close();
    }
}
