package com.epam.backend.core.dto.repository;

import com.epam.backend.core.dto.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);
    void addUsers(List<User> users);
    List<User> getAllUsers();
}
