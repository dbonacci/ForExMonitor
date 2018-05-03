package forexmonitor;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDateTime;

import org.junit.Test;

public class ForExMonitorTest {

	Market testMarket = new Market_Test();	// market at UTC+2
	
	@Test
	public void testGetLatestQuote_NoQuoteOnSaturday() {
		LocalDateTime saturday = LocalDateTime.of(2018, 4, 28, 10, 30); // saturday 10:30 at UTC+2
		Instant testInstant = saturday.atZone(testMarket.getZoneId()).toOffsetDateTime().toInstant();
		assertNull("No quote should be provided on saturday", testMarket.getLatestQuote(testInstant));
	}
	
	@Test
	public void testGetLatestQuote_NoQuoteOnSunday() {
		LocalDateTime sunday = LocalDateTime.of(2018, 4, 29, 10, 30); // sunday 10:30 at UTC+2
		Instant testInstant = sunday.atZone(testMarket.getZoneId()).toOffsetDateTime().toInstant();
		assertNull("No quote should be provided on sunday", testMarket.getLatestQuote(testInstant));
	}
	
	@Test
	public void testGetLatestQuote_NoQuoteBeforeMarketOpen() {
		LocalDateTime mondayBeforeOfficeHours = LocalDateTime.of(2018, 4, 30, 6, 30); // monday 06:30 at UTC+2
		Instant testInstant = mondayBeforeOfficeHours.atZone(testMarket.getZoneId()).toOffsetDateTime().toInstant();
		assertNull("No quote should be provided before the market opens", testMarket.getLatestQuote(testInstant));
	}	
	
	@Test
	public void testGetLatestQuote_NoQuoteAfterMarketClose() {
		LocalDateTime wednesdayAfterOfficeHorus = LocalDateTime.of(2018, 5, 2, 20, 30); // wednesday 20:30 at UTC+2
		Instant testInstant = wednesdayAfterOfficeHorus.atZone(testMarket.getZoneId()).toOffsetDateTime().toInstant();
		assertNull("No quote should be provided after market closes", testMarket.getLatestQuote(testInstant));
	}	
	
	@Test
	public void testGetLatestQuote_QuoteDuringMarketOfficeHours() {
		LocalDateTime fridayDuringOfficeHours = LocalDateTime.of(2018, 5, 4, 12, 30); // friday 12:30 at UTC+2
		Instant testInstant = fridayDuringOfficeHours.atZone(testMarket.getZoneId()).toOffsetDateTime().toInstant();
		assertNotNull("Quote should be provided during market office hours", testMarket.getLatestQuote(testInstant).getRate());
	}	
}
