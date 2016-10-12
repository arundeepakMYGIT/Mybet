package com.mybet.java.entities;

import javax.annotation.Nonnegative;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "BETS")
public class Bet {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "betSeq")
    @SequenceGenerator(name = "betSeq", sequenceName = "bet_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "PLACEMENT_DATE")
    private Timestamp placementDate;

    @Column(name = "STAKE")
    @Nonnegative
    private double stake;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "USER_ID")
    private int userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getPlacementDate() {
        return placementDate;
    }

    public String getPlacementDateAsString() {
        Date date = new Date(getPlacementDate().getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public void setPlacementDate(Timestamp placementDate) {
        this.placementDate = placementDate;
    }

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
}
