package com.mybet.test.services;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.mybet.java.entities.Event;
import com.mybet.java.services.DbService;
import com.mybet.test.SpringContextBasedTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest extends SpringContextBasedTest{
	
    @Autowired
    private DbService dbService;
    
    /* ---- To verify all events result from SQL are displayed as Json format in an array */
    @Test
    public void verifyAllEventsAsJson() throws SQLException
    {
    	List<Event> allEvents = dbService.findAllEvents();
    	
    	Assert.assertEquals(new Gson().toJson(allEvents.toArray()), "Expected Result in array");
    }

}
