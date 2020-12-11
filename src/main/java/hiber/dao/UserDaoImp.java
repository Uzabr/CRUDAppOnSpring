package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByCarModelAndSeries () {
      TypedQuery query = sessionFactory.getCurrentSession().createQuery( "" +
              "select c.model, c.series, s.id, s.firstName, s.lastName, s.email " +
              "from User s, Car c where s.car = c.user");
      return query.getResultList();
   }
}
