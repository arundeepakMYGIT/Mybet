package com.mybet.test.entities;

import org.junit.Assert;
import org.junit.Test;

import com.mybet.java.entities.Bet;

import java.sql.Timestamp;
import java.util.Calendar;

public class BetTest {

    @Test
    public void placementDateAsString() {
        Bet bet = new Bet();
        Calendar calendar = Calendar.getInstance();
        //noinspection MagicConstant
        calendar.set(2016, 7, 15, 12, 15, 30);
        bet.setPlacementDate(new Timestamp(calendar.getTimeInMillis()));
        Assert.assertEquals("2016-08-15 12:15:30", bet.getPlacementDateAsString());
    }
}
