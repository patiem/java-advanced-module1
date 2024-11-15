package com.epam.backend.core.dto.repository.impl;

import com.epam.backend.core.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryUserRepositoryTest {

    private static final User USER1= new User("John", "Dot", LocalDate.of(2000,10,12));
    private static final User USER2= new User("Jemma", "Bird", LocalDate.of(2010,9,30));
    private static final User USER3= new User("Michelle", "Flat", LocalDate.of(2002,4,20));
    private static final User USER4= new User("Michael", "Rams", LocalDate.of(1985,5,4));

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
        repository.addUser(USER1);
        repository.addUser(USER2);
    }

    @Test
    void testAddUser() {
        repository.addUser(USER3);
        var allUsers = repository.getAllUsers();
        assertTrue(allUsers.contains(USER3));
    }

    @Test
    void testAddUsers() {
        var newUsers = List.of(USER3, USER4);
        repository.addUsers(newUsers);
        var allUsers = repository.getAllUsers();
        assertTrue(allUsers.containsAll(newUsers));
    }

    @Test
    void testGetAllUsers(){
        var allUsers = repository.getAllUsers();
        assertEquals(2, allUsers.size());
    }

}