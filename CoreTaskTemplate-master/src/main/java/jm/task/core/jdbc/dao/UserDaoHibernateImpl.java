package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory factory;

    public UserDaoHibernateImpl() {

        factory = HibernateUtil.getSessionFactory();;
    }


    @Override
    public void createUsersTable() throws SQLException, ClassNotFoundException {
        Transaction transaction =null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            session.createSQLQuery("CREATE TABLE if not exists `users`.`user` (`id` BIGINT NOT NULL AUTO_INCREMENT" +
                    ", `name` VARCHAR(45) NULL," +
                    "`lastName` VARCHAR(45) NULL,`age` INT NULL, PRIMARY KEY (`id`))").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();

            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction =null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            session.createSQLQuery("drop table if exists user").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();

            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction =null;
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User " + name + " was created");
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();

        }         e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction =null;
        Session session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            User user = (User) session.load(User.class, new Long(id));
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();

            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list =  new ArrayList<>();
        Transaction transaction =null;
        Session session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            list = session.createQuery("from User").list();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction =null;
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            session.createSQLQuery("use users").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
}
