package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService extends AutoCloseable{
    void add(User user);
    List<User> listUsers();

    @Override
    void close();
}
