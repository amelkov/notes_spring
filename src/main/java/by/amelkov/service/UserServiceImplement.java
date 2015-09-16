package by.amelkov.service;

import by.amelkov.dao.UserDAO;
import by.amelkov.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImplement implements UserService, UserDetailsService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public void add(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void edit(User user) {
        userDAO.editUser(user);
    }

    @Override
    public void delete(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUser(s);
    }
}
