package com.epam.backend.core.dto.repository.impl;

import com.epam.backend.core.dto.User;
import com.epam.backend.core.dto.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> usersList= new ArrayList<>();

    @Override
    public void addUser(User user) {
        usersList.add(user);
    }

    @Override
    public void addUsers(List<User> users) {
        usersList.addAll(users);
    }

    @Override
    public List<User> getAllUsers() {
        return usersList.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
