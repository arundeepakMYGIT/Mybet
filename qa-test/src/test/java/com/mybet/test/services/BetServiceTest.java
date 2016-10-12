package com.mybet.test.services;

import com.mybet.java.entities.Bet;
import com.mybet.java.entities.Event;
import com.mybet.java.entities.Market;
import com.mybet.java.entities.Outcome;
import com.mybet.java.services.BetService;
import com.mybet.java.services.EventService;
import com.mybet.java.services.data.BetData;
import com.mybet.test.SpringContextBasedTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class BetServiceTest extends SpringContextBasedTest {

    @Autowired
    private BetService betService;

    @Autowired
    private EventService eventService;

    @Test
    public void placeBet() throws Exception {
        double stake = 5.0;
        BetData betData = new BetData();
        betData.setStake(stake);
        betData.setCurrencyCode("EUR");
        betData.setUserId(1);
        Map<Integer, Map<String, Double>> tips = new HashMap<Integer, Map<String, Double>>();
        Event event = createTestEventForMarkt1();
        Assert.assertNotNull(event);
        List<Market> markets = eventService.findMarkets(event.getId());
        Map<String, Double> tipsOnMarket = new HashMap<String, Double>();
        List<Outcome> outcomes = eventService.findOutcomes(markets.get(0).getId());
        double odds = outcomes.get(0).getValue();
        tipsOnMarket.put(outcomes.get(0).getName(), odds);
        tips.put(markets.get(0).getId(), tipsOnMarket);
        betData.setTips(tips);

        Bet placedBet = betService.placeBet(1, betData);

        Assert.assertNotNull("Bet could not be placed", placedBet);
        Assert.assertEquals("Bet stake does not match", betData.getStake(), placedBet.getStake(), 0.0);
        Assert.assertEquals("Currency code does not match", betData.getCurrencyCode(), placedBet.getCurrencyCode());
        Assert.assertEquals("Potential Win is incorrect", stake * odds, betService.getPotentialWin(placedBet.getId()), 0.0);
    }
}
