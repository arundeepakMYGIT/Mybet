package com.mybet.java.services;

import com.mybet.java.entities.*;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbService {

    private static String DB_NAME = "mybet-test.db";

    private Connection connection;

    private EntityManagerFactory entityManagerFactory;

    public DbService() throws IOException, SQLException {
        entityManagerFactory = Persistence.createEntityManagerFactory(DB_NAME);
        initLinkTables();
        initData();
    }

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
        }
        return connection;
    }

    public List<Bet> findBetsForUser(int userId) throws SQLException {
        String sql = "SELECT * FROM BETS WHERE USER_ID = ?";
        return executeSelect(Bet.class, sql, userId);
    }

    public List<Event> findAllEvents() throws SQLException {
        String sql = "SELECT * FROM EVENTS ORDER BY EVENT_DATE DESC";
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return getEntites(Event.class, resultSet);
        } finally {
            connection.close(); 
        }
    }

    public List<User> findAllUsers() throws SQLException {
        String sql = "SELECT * FROM USERS ORDER BY ID";
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return getEntites(User.class, resultSet);
        } finally {
            connection.close();
        }
    }

    @Nullable
    public MarketType getMarketTypeForName(String typeName) throws SQLException {
        String sql = "SELECT * FROM MARKET_TYPES WHERE NAME = ?";
        return executeSelect(MarketType.class, sql, typeName).get(0);
    }

    public void link(Event event, Market market) throws SQLException {
        connection = connect();
        String sql = "INSERT INTO EVENT_MARKETS (EVENT_ID, MARKET_ID) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, event.getId());
        statement.setInt(2, market.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    public void link(Market market, Outcome outcome) throws SQLException {
        connection = connect();
        String sql = "INSERT INTO MARKET_OUTCOMES (MARKET_ID, OUTCOME_ID) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, market.getId());
        statement.setInt(2, outcome.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    public void link(Bet bet, Tip tip) throws SQLException {
        connection = connect();
        String sql = "INSERT INTO BET_TIPS (BET_ID, TIP_ID) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, bet.getId());
        statement.setInt(2, tip.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    public <E> void persist(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <E> E findById(Class<E> entityClass, int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        E entity = entityManager.find(entityClass, id);
        entityManager.close();
        return entity;
    }

    public User findUser(String userName) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";
        return executeSelect(User.class, sql, userName).get(0);
    }

    public Outcome findOutcome(int marketId, String outcomeName) throws SQLException {
        connection = connect();
        String sql = "" +
                " SELECT *" +
                " FROM   OUTCOMES o, MARKET_OUTCOMES mo" +
                " WHERE  o.ID = mo.OUTCOME_ID AND mo.MARKET_ID = ? AND o.NAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, marketId);
        statement.setString(2, outcomeName);
        ResultSet outcomeResultSet = statement.executeQuery();
        Outcome outcome = getEntites(Outcome.class, outcomeResultSet).get(0);
        connection.close();
        return outcome;
    }

    public List<Market> findMarkets(Integer eventId) throws SQLException {
        String sql = "SELECT * FROM MARKETS m, EVENT_MARKETS em WHERE m.ID = em.MARKET_ID AND em.EVENT_ID = ?";
        return executeSelect(Market.class, sql, eventId);
    }

    public List<Outcome> findOutcomes(Integer marketId) throws SQLException {
        String sql = "SELECT * FROM OUTCOMES o, MARKET_OUTCOMES mo WHERE o.ID = mo.OUTCOME_ID AND mo.MARKET_ID = ?";
        return executeSelect(Outcome.class, sql, marketId);
    }

    public List<Tip> findTips(int betId) throws SQLException {
        String sql = "SELECT * FROM TIPS t, BET_TIPS bt WHERE t.ID = bt.TIP_ID AND bt.BET_ID = ?";
        return executeSelect(Tip.class, sql, betId);
    }

    public <E> void delete(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.detach(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private <E> List<E> executeSelect(Class<E> entityClass, String sql, int... params) throws SQLException {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                statement.setInt(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            return getEntites(entityClass, resultSet);
        }
    }

    private <E> List<E> executeSelect(Class<E> entityClass, String sql, String... params) throws SQLException {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            return getEntites(entityClass, resultSet);
        }
    }

    private <E> List<E> getEntites(Class<E> entityClass, ResultSet resultSet) throws SQLException {
        BeanPropertyRowMapper<E> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
        List<E> result = new ArrayList<E>();
        int count = 0;
        while (resultSet.next()) {
            result.add(rowMapper.mapRow(resultSet, count++));
        }
        resultSet.close();
        return result;
    }

    private void initLinkTables() throws SQLException, IOException {
        runSqlScript("init_linkTables.sql");
    }

    private void initData() throws SQLException, IOException {
        MarketType marketType1X2 = new MarketType();
        marketType1X2.setName("1X2");
        marketType1X2.setNumberOfOutcomes(3);
        MarketType marketTypeOU25 = new MarketType();
        marketTypeOU25.setName("O/U 2.5");
        marketTypeOU25.setNumberOfOutcomes(2);
        User maximus = new User();
        maximus.setCountryCode("DE");
        maximus.setFirstName("Max");
        maximus.setGender("m");
        maximus.setLanguageCode("de");
        maximus.setLastName("Mustermann");
        maximus.setPwHash("123456");
        maximus.setUserName("maximus");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(marketType1X2);
        entityManager.persist(marketTypeOU25);
        entityManager.persist(maximus);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void runSqlScript(String fileName) throws SQLException, IOException {
        connection = connect();
        Statement statement = connection.createStatement();
        String sql = loadSqlFromResource(fileName);
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    private String loadSqlFromResource(String sqlFileName) throws IOException {
        StringBuilder sql = new StringBuilder();
        try (InputStream stream = ClassLoader.getSystemResourceAsStream(sqlFileName)) {
            try (InputStreamReader reader = new InputStreamReader(stream)) {
                try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sql.append(line);
                    }
                }
            }
        }
        return sql.toString();
    }
}
