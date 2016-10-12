package com.mybet.test;

import com.google.gson.Gson;
import com.mybet.java.entities.Event;
import com.mybet.java.entities.Market;
import com.mybet.java.entities.User;
import com.mybet.java.services.EventService;
import com.mybet.java.services.data.EventData;
import com.mybet.java.services.data.MarketData;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
public abstract class SpringContextBasedTest {

	private static Map<String, Long> collectedTimes = new HashMap<String, Long>();
	private static Logger log = LoggerFactory
			.getLogger(SpringContextBasedTest.class);

	@Autowired
	private EventService eventService;

	@Rule
	public TestRule watchman = new TestWatcher() {

		private long startTime = 0L;

		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}

		@Override
		protected void starting(Description description) {
			startTime = System.currentTimeMillis();
			super.starting(description);
		}

		@Override
		protected void finished(Description description) {
			collectedTimes.put(
					description.getClassName() + "#"
							+ description.getMethodName(),
					System.currentTimeMillis() - startTime);
			super.finished(description);
		}
	};

	@AfterClass
	public static void evaluateTimes() {
		log.info("##### Test performance profile:");
		long sum = 0L;
		for (String name : collectedTimes.keySet()) {
			log.info("##### Test method {}: {} ms", name,
					collectedTimes.get(name));
			sum += collectedTimes.get(name);
		}
		log.info("##### Total: {} ms", sum);
		log.info("##### Finished.");
	}

	/*
	 * @Nullable protected Event createTestEvent() { EventData eventData = new
	 * EventData(); eventData.setMarkets(new ArrayList<>(2));
	 * eventData.setEventDate(new Date(System.currentTimeMillis() + 60000L));
	 * eventData.setGuestName("FC Schalke 04");
	 * eventData.setHomeName("Hertha BSC Berlin"); MarketData market1X2 = new
	 * MarketData(); market1X2.setTypeName("1X2"); Map<String, Double>
	 * outcomes1X2 = new HashMap<String, Double>(3); outcomes1X2.put("1", 2.0);
	 * outcomes1X2.put("X", 3.0); outcomes1X2.put("2", 4.0);
	 * market1X2.setOutcomes(outcomes1X2);
	 * eventData.getMarkets().add(market1X2); MarketData marketOU25 = new
	 * MarketData(); marketOU25.setTypeName("O/U 2.5"); Map<String, Double>
	 * outcomesOU25 = new HashMap<String, Double>(2); outcomesOU25.put("O 2.5",
	 * 1.7); outcomesOU25.put("U 2.5", 2.0);
	 * marketOU25.setOutcomes(outcomesOU25);
	 * eventData.getMarkets().add(marketOU25); try { return
	 * eventService.create(eventData); } catch (SQLException e) {
	 * log.error("Test event {} could not be created", new
	 * Gson().toJson(eventData), e); } return null; }
	 */

	// Verifying for creating test event data with Markt 1 ,,1X2"
	@Nullable
	protected Event createTestEventForMarkt1() {
		EventData eventData = new EventData();
		eventData.setMarkets(new ArrayList<>(2));
		eventData.setEventDate(new Date(System.currentTimeMillis() + 60000L));
		eventData.setGuestName("FC Schalke 04");
		eventData.setHomeName("Hertha BSC Berlin");

		MarketData market1X2 = new MarketData();
		market1X2.setTypeName("1X2");
		Map<String, Double> outcomes1X2 = new HashMap<String, Double>(3);
		outcomes1X2.put("1", 2.0);
		outcomes1X2.put("X", 3.0);
		outcomes1X2.put("2", 4.0);
		market1X2.setOutcomes(outcomes1X2);
		eventData.getMarkets().add(market1X2);

		try {
			return eventService.create(eventData);
		} catch (SQLException e) {
			log.error("Test event data for Market1 (1X2) could not be created",
					new Gson().toJson(eventData), e);
		}
		return null;
	}

	// Verifying for creating test event data with Markt 1 ,,O/U2.5"
	@Nullable
	protected Event createTestEventForMarkt2() {
		EventData eventData = new EventData();
		eventData.setMarkets(new ArrayList<>(2));
		eventData.setEventDate(new Date(System.currentTimeMillis() + 60000L));
		eventData.setGuestName("FC Test 05");
		eventData.setHomeName("Test Muenchen");

		MarketData marketOU25 = new MarketData();
		marketOU25.setTypeName("O/U 2.5");
		Map<String, Double> outcomesOU25 = new HashMap<String, Double>(2);
		outcomesOU25.put("O 2.5", 1.7);
		outcomesOU25.put("U 2.5", 2.0);
		marketOU25.setOutcomes(outcomesOU25);
		eventData.getMarkets().add(marketOU25);

		try {
			return eventService.create(eventData);
		} catch (SQLException e) {
			log.error(
					"Test event data for Market2 (O/U 2.5) could not be created",
					new Gson().toJson(eventData), e);
		}
		return null;
	}

	// Verifying for the creating test market data with Markt 1 ,,1X2"
	@Nullable
	protected Market createTestMarketForMarkt1() {
		MarketData market1X2 = new MarketData();
		market1X2.setTypeName("1X2");
		Map<String, Double> outcomes1X2 = new HashMap<String, Double>(3);
		outcomes1X2.put("1", 2.0);
		outcomes1X2.put("X", 3.0);
		outcomes1X2.put("2", 4.0);
		// market1X2.setOutcomes(outcomes1X2);
		market1X2.getOutcomes();

		try {
			return eventService.create(market1X2);
		} catch (SQLException e) {
			log.error(
					"Test market data for Market1 (1X2) could not be created",
					new Gson().toJson(market1X2), e);
		}
		return null;
	}

	// Verifying for the creating test market data with Markt 2 ,,O/U2.5"
	@Nullable
	protected Market createTestMarketForMarkt2() {
		MarketData marketOU25 = new MarketData();
		marketOU25.setTypeName("O/U 2.5");
		Map<String, Double> outcomesOU25 = new HashMap<String, Double>(2);
		outcomesOU25.put("O 2.5", 1.7);
		outcomesOU25.put("U 2.5", 2.0);
		//marketOU25.setOutcomes(outcomesOU25);
		marketOU25.getOutcomes();

		try {
			return eventService.create(marketOU25);
		} catch (SQLException e) {
			log.error(
					"Test market data for Market2 (O/U2.5) could not be created",
					new Gson().toJson(marketOU25), e);
		}
		return null;
	}

}
