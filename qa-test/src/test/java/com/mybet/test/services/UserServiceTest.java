package com.mybet.test.services;

import com.mybet.java.entities.User;
import com.mybet.java.services.UserService;
import com.mybet.java.services.data.UserData;
import com.mybet.test.SpringContextBasedTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends SpringContextBasedTest {

    @Autowired
    private UserService userService;

    @Test
    public void findDefaultUserById() {
        User user = userService.findUser(1);
        Assert.assertNotNull("User not found", user);
        Assert.assertEquals("Wrong user returned", "maximus", user.getUserName());
    }

    @Test
    public void findDefaultUserByUserName() throws SQLException {
        User user = userService.findUser("maximus");
        Assert.assertNotNull("User not found", user);
        Assert.assertEquals("Wrong user returned", new Integer(1), user.getId());
    }

    @Test
    public void tryToFindNonExistingUserById() {
        User user = userService.findUser(999);
        Assert.assertNull("User must not be found", user);
    }

    @Test
    public void createUser() throws SQLException {
        UserData userData = new UserData();
        userData.setFirstName("Toni");
        userData.setLastName("Tester");
        userData.setGender("m");
        userData.setCountryCode("CA");
        userData.setLanguageCode("en");
        userData.setUserName("tonitester");
        userData.setPwHash("123456");

        userService.create(userData);

        User user = userService.findUser("tonitester");
        Assert.assertNotNull("User not created", user);
        Assert.assertEquals("User name does not match", userData.getUserName(), user.getUserName());
        Assert.assertEquals("Password does not match", userData.getPwHash(), user.getPwHash());
        Assert.assertEquals("First name does not match", userData.getFirstName(), user.getFirstName());
        Assert.assertEquals("Last name does not match", userData.getLastName(), user.getLastName());
        Assert.assertEquals("Gender does not match", userData.getGender(), user.getGender());
        Assert.assertEquals("Language code does not match", userData.getLanguageCode(), user.getLanguageCode());
        Assert.assertEquals("Country code does not match", userData.getCountryCode(), user.getCountryCode());
    }
}
