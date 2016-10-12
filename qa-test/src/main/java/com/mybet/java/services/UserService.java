package com.mybet.java.services;

import com.mybet.java.entities.*;
import com.mybet.java.services.data.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private DbService dbService;

    public User findUser(int userId) {
        return dbService.findById(User.class, userId);
    }

    public User findUser(String userName) throws SQLException {
        return dbService.findUser(userName);
    }

    public List<User> findAllUsers() throws SQLException {
        return dbService.findAllUsers();
    }

    public User create(UserData userData) {
        User user = new User();
        user.setUserName(userData.getUserName());
        user.setPwHash(userData.getPwHash());
        user.setGender(userData.getGender());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setGender(userData.getGender());
        user.setCountryCode(userData.getCountryCode());
        user.setLanguageCode(userData.getLanguageCode());
        dbService.persist(user);
        return user;
    }

    public List<Bet> findBetsForUser(int userId) throws SQLException {
        return dbService.findBetsForUser(userId);
    }
}
