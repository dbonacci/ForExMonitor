package forexmonitor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

class Market_Test extends Market{
	//*******************************************
	// 1a Properties
	//*******************************************
	

	//*******************************************
	// 1b Setters & Getters
	//*******************************************	
	
	
	//*******************************************
	// 2a Constructors
	//*******************************************
	Market_Test() {
		super.marketName = "Test";
		super.openTime = LocalTime.of(9, 0, 0);
		super.closeTime = LocalTime.of(17, 0, 0);
		super.zoneId = ZoneId.of("UTC+2");
	}

	//*******************************************
	// 2b Switches
	//*******************************************
	

	//*******************************************
	// 2c Interfaces
	//*******************************************
	@Override
	Quote getQuoteFromMarket() {
		return new Quote(marketName, Instant.now(), BigDecimal.ONE);
	}
}
