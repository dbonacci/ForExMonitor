package forexmonitor;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.math.BigDecimal;

//*******************************************
//*******************************************
// A Proper classes
//*******************************************
//*******************************************

abstract class Market{
	//*******************************************
	// 1a Properties
	//*******************************************
	String marketName;
	LocalTime openTime;
	LocalTime closeTime;
	ZoneId zoneId;

	//*******************************************
	// 1b Setters & Getters
	//*******************************************	
	String getMarketName() {
		return marketName;
	}
	LocalTime getOpenTime() {
		return openTime;
	}
	LocalTime getCloseTime() {
		return closeTime;
	}
	ZoneId getZoneId() {
		return zoneId;
	}
	
	//*******************************************
	// 2a Constructors
	//*******************************************


	//*******************************************
	// 2b Switches
	//*******************************************
	

	//*******************************************
	// 2c Interfaces
	//*******************************************	
	Quote getLatestQuote(Instant instant) {
		// check if the market is open
		Quote quote = null; // set default value of quote to null
		LocalDateTime marketLDT = LocalDateTime.ofInstant(instant, zoneId);
		DayOfWeek day = marketLDT.getDayOfWeek();
        if((day != DayOfWeek.SATURDAY) && (day != DayOfWeek.SUNDAY)) { // check if work day
        	LocalTime marketLT = marketLDT.toLocalTime();
        	if(marketLT.isAfter(openTime) && marketLT.isBefore(closeTime)){ // check if office hours  
        		quote = getQuoteFromMarket(); // if open, set quote return value accordingly
        	}
        	else {
        		System.out.println("Market closed - out of office hours. (" +
        				"Market open time: " + openTime +
        				" / Market close time: " + closeTime +
        				" / Current local market time: " + LocalTime.from(instant.atZone(zoneId)) +
        				")");
        	}
        }
        else {
    		System.out.println("Market closed - weekend. (" +
    				"Current local market time: " + day.toString() +
    				")");
        }
        return quote;
	}
	
	abstract Quote getQuoteFromMarket();
	
	//*******************************************
	// 3a Private methods
	//*******************************************
	
	
	//*******************************************
	// 3b Private classes
	//*******************************************
}


//*******************************************
//*******************************************
// B Containers
//*******************************************
//*******************************************
class Quote{
	// 1a Properties
	String marketName;
	Instant timestamp;
	BigDecimal rate;
		
	// 2a Constructors
	public Quote(String marketName, Instant timestamp, BigDecimal rate){
		this.marketName = marketName;
		this.timestamp = timestamp;
		this.rate = rate;
	}	
	
	// 1b Setters & Getters
	String getMarketName() {
		return marketName;
	}
	Instant getInstant() {
		return timestamp;
	}	
	BigDecimal getRate() {
		return rate;
	}
}
