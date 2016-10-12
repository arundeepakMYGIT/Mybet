package com.mybet.java.services.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventData {

    private List<MarketData> markets;

    private String homeName;

    private String guestName;

    private Date eventDate;

    public List<MarketData> getMarkets() {
        return markets;
    }

    public void setMarkets(List<MarketData> markets) {
        this.markets = markets;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

}
