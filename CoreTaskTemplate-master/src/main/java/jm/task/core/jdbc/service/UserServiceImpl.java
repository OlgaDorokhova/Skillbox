package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.Factory;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends UserDaoHibernateImpl implements UserService {

    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public UserServiceImpl() {

    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        super.createUsersTable();
    }

    public void dropUsersTable(){
        super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        super.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return super.getAllUsers();
    }

    public void cleanUsersTable() {
        super.cleanUsersTable();
    }
}
