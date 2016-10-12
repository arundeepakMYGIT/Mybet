package com.mybet.java.services;

import com.google.gson.Gson;
import com.mybet.java.entities.*;
import com.mybet.java.services.data.EventData;
import com.mybet.java.services.data.MarketData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.mybet.java.util.ApplicationUtil.errorMessage;

@Service
public class EventService {

    @Autowired
    private DbService dbService;

    public String allEventsAsJson() {
        List<Event> allEvents;
        try {
            allEvents = dbService.findAllEvents();
            return new Gson().toJson(allEvents.toArray());
        } catch (SQLException e) {
            return new Gson().toJson(errorMessage(e));
        }
    }

    public Event create(EventData eventData) throws SQLException {
        Event event = new Event();
        Participant home = new Participant();
        home.setName(eventData.getHomeName());
        dbService.persist(home);
        event.setHomeParticipantId(home.getId());
        Participant guest = new Participant();
        guest.setName(eventData.getGuestName());
        dbService.persist(guest);
        event.setGuestParticipantId(guest.getId());
        event.setEventDate(new Timestamp(eventData.getEventDate().getTime()));
        dbService.persist(event);
        for (MarketData marketData : eventData.getMarkets()) {
            dbService.link(event, create(marketData));
        }
        return event;
    }

    public Market create(MarketData marketData) throws SQLException {
        Market market = new Market();
        MarketType marketType = dbService.getMarketTypeForName(marketData.getTypeName());
        if (marketType == null) {
            throw new IllegalArgumentException("Invalid market type specified: " + marketData.getTypeName());
        }
        market.setTypeId(marketType.getId());
        dbService.persist(market);
        for (String outcomeName : marketData.getOutcomes().keySet()) {
            Outcome outcome = new Outcome();
            outcome.setName(outcomeName);
            outcome.setValue(marketData.getOutcomes().get(outcomeName));
            dbService.persist(outcome);
            dbService.link(market, outcome);
        }
        return market;
    }

    public List<Market> findMarkets(Integer eventId) throws SQLException {
        return dbService.findMarkets(eventId);
    }

    public List<Outcome> findOutcomes(Integer marketId) throws SQLException {
        return dbService.findOutcomes(marketId);
    }
}
