package com.mybet.java;

import com.google.gson.Gson;
import com.mybet.java.services.*;
import com.mybet.java.services.data.BetData;
import com.mybet.java.services.data.EventData;
import com.mybet.java.services.data.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.mybet.java.util.ApplicationUtil.errorMessage;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@SpringBootApplication
public class Application {

    @Autowired
    private BetService betService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(path = "/", produces = "application/json", method = GET)
    public String findAllEvents() {
        return eventService.allEventsAsJson();
    }

    @RequestMapping(path = "/createEvent", produces = "application/json")
    public String createEvent(@RequestParam("eventData") String eventDataAsJson) {
        EventData eventData = new Gson().fromJson(eventDataAsJson, EventData.class);
        try {
            eventService.create(eventData);
        } catch (SQLException e) {
            return new Gson().toJson(errorMessage(e));
        }
        return findAllEvents();
    }

    @RequestMapping(path = "/allUsers", produces = "application/json", method = GET)
    public String findAllUsers() {
        try {
            return new Gson().toJson(userService.findAllUsers());
        } catch (SQLException e) {
            return new Gson().toJson(errorMessage(e));
        }
    }

    @RequestMapping(path = "/user/{id}", produces = "application/json", method = GET)
    public String findUser(@PathVariable("id") int userId) {
        return new Gson().toJson(userService.findUser(userId));
    }

    @RequestMapping(path = "/user/{id}/bets", produces = "application/json", method = GET)
    public String findBetsForUser(@PathVariable("id") int userId) {
        try {
            return new Gson().toJson(userService.findBetsForUser(userId));
        } catch (SQLException e) {
            return new Gson().toJson(errorMessage(e));
        }
    }

    @RequestMapping(path = "/bet/{id}/potentialWin", produces = "application/json", method = GET)
    public String potentialWin(@PathVariable("id") int betId) {
        try {
            return new Gson().toJson(betService.getPotentialWin(betId));
        } catch (SQLException e) {
            return new Gson().toJson(errorMessage(e));
        }
    }

    @RequestMapping(path = "/createUser", produces = "application/json")
    public String createUser(@RequestParam("userData") String userDataAsJson) {
        UserData userData = new Gson().fromJson(userDataAsJson, UserData.class);
        return findUser(userService.create(userData).getId());
    }

    @RequestMapping(path = "/placeBet", produces = "application/json")
    public String placeBet(@RequestParam("userId") int userId, @RequestParam("betData") String betDataAsJson) {
        BetData betData = new Gson().fromJson(betDataAsJson, BetData.class);
        try {
            return new Gson().toJson(betService.placeBet(userId, betData));
        } catch (Exception e) {
            return new Gson().toJson(errorMessage(e));
        }
    }
}
