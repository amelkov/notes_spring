package by.amelkov.dao;

import by.amelkov.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImplement implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void editUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    public User getUser(String login) {
        User tmp;
        Session tmp2 = sessionFactory.getCurrentSession();
        tmp = (User) tmp2.get(User.class, login);
        return tmp;
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}