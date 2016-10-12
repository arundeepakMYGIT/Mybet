package com.mybet.java.services.data;

import java.util.Date;
import java.util.Map;

public class BetData {

    private double stake;

    private String currencyCode;

    private int userId;

    private Map<Integer, Map<String, Double>> tips;

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<Integer, Map<String, Double>> getTips() {
        return tips;
    }

    public Map<String, Double> getTipsForMarket(int marketId) {
        return tips.get(marketId);
    }

    public void setTips(Map<Integer, Map<String, Double>> tips) {
        this.tips = tips;
    }
}
