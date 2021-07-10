package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDaoImp")
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

@Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {

      try {
         sessionFactory.getCurrentSession().save(user);
         System.out.println("User added................................");
      } catch (HibernateException e) {
         System.out.println("Error with adding user!!!!!!!!!-------------------------");
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
    List<User> result = null;
      try {
         result = sessionFactory.getCurrentSession().createQuery("from User").getResultList();
         System.out.println("User added................................");
      } catch (HibernateException e) {
         System.out.println("Error with adding user!!!!!!!!!-------------------------");
      }
      return result;
   }

}
