package com.mybet.java.services;

import com.google.gson.Gson;
import com.mybet.java.entities.Bet;
import com.mybet.java.entities.Market;
import com.mybet.java.entities.Outcome;
import com.mybet.java.entities.Tip;
import com.mybet.java.entities.User;
import com.mybet.java.services.data.BetData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BetService {

    private static Logger log = LoggerFactory.getLogger(BetService.class);

    @Autowired
    private DbService dbService;

    public Bet placeBet(int userId, BetData betData) throws Exception {
        Bet bet = new Bet();
        User user = dbService.findById(User.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("The specified user does not exist, bet can not be placed.");
        }
        bet.setPlacementDate(new Timestamp(new Date().getTime()));
        bet.setUserId(userId);
        bet.setStake(betData.getStake());
        bet.setCurrencyCode(betData.getCurrencyCode());
        dbService.persist(bet);
        for (Integer marketId : betData.getTips().keySet()) {
            Market market = dbService.findById(Market.class, marketId);
            if (market == null) {
                throw  new IllegalArgumentException("The specified market does not exist, bet could not be placed.");
            }
            Map<String, Double> tipsForMarket = betData.getTipsForMarket(marketId);
            for (String outcomeName : tipsForMarket.keySet()) {
                Outcome outcome = dbService.findOutcome(marketId, outcomeName);
                if (outcome == null) {
                    throw  new IllegalArgumentException("The specified outcome does not exist, bet could not be placed.");
                }
                Tip tip = new Tip();
                tip.setMarketId(marketId);
                tip.setOddsName(outcomeName);
                tip.setCurrentOdds(tipsForMarket.get(outcomeName));
                dbService.persist(tip);
                dbService.link(bet, tip);
            }
        }
        log.info("Bet {} placed successfully at {}.", new Gson().toJson(bet), bet.getPlacementDateAsString());
        return bet;
    }

    public Double getPotentialWin(int betId) throws SQLException {
        Bet bet = dbService.findById(Bet.class, betId);
        double totalOdds = 1.0;
        List<Tip> tips = dbService.findTips(betId);
        for (Tip tip : tips) {
            totalOdds *= tip.getCurrentOdds();
        }
        return bet.getStake() * totalOdds;
    }

    public String getPlacementDateAsString(int betId) {
        Bet bet = dbService.findById(Bet.class, betId);
        return bet.getPlacementDateAsString();
    }
}
