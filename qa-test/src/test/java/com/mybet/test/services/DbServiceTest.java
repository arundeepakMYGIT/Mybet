package com.mybet.test.services;

import com.mybet.java.entities.MarketType;
import com.mybet.java.entities.User;
import com.mybet.java.services.DbService;
import com.mybet.test.SpringContextBasedTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
public class DbServiceTest extends SpringContextBasedTest {

    @Autowired
    private DbService dbService;

    private Connection connection;

    @Before
    public void connect() throws SQLException {
        connection = dbService.connect();
    }

    @Test
    public void databasePreparation() throws SQLException {
        Assert.assertNotNull("DB connection could not be established", connection);
        Assert.assertEquals("Size of all users list is not 1", 1, dbService.findAllUsers().size());
        User user = dbService.findById(User.class, 1);
        Assert.assertNotNull("Inital user has not been created", user);
        Assert.assertEquals("Inital test user's user name is incorrect", "maximus", user.getUserName());
        MarketType marketType1X2 = dbService.getMarketTypeForName("1X2");
        Assert.assertNotNull("Market type 1X2 has not been created", marketType1X2);
        MarketType marketTypeOU25 = dbService.getMarketTypeForName("O/U 2.5");
        Assert.assertNotNull("Market type O/U 2.5 has not been created", marketTypeOU25);
    }

    @After
    public void disconnect() throws SQLException {
        connection.close();
    }
}
