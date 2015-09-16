package by.amelkov.dao;

import by.amelkov.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    User getUser(String login);

    List<User> getAllUsers();
}
