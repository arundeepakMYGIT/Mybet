package com.mybet.java.services.data;

import java.util.HashMap;
import java.util.Map;

public class MarketData {

    private String typeName;

    private Map<String, Double> outcomes;

    public MarketData() {
        outcomes = new HashMap<String, Double>();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Map<String, Double> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Map<String, Double> outcomes) {
        this.outcomes = outcomes;
    }
}
