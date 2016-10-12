package com.mybet.java.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPS")
public class Tip {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipSeq")
    @SequenceGenerator(name = "tipSeq", sequenceName = "tip_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "CURRENT_ODDS")
    private double currentOdds;

    @Column(name = "ODDS_NAME")
    private String oddsName;

    @Column(name = "MARKET_ID")
    private int marketId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCurrentOdds() {
        return currentOdds;
    }

    public void setCurrentOdds(double currentOdds) {
        this.currentOdds = currentOdds;
    }

    public String getOddsName() {
        return oddsName;
    }

    public void setOddsName(String oddsName) {
        this.oddsName = oddsName;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }
}
