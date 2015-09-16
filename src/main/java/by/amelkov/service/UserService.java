package by.amelkov.service;

import by.amelkov.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void edit(User user);

    void delete(User user);

    User getUser(String login);

    List<User> getAllUsers();
}