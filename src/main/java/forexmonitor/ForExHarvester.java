package forexmonitor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ForExHarvester extends TimerTask {
	//*******************************************
	// 1a Properties
	//*******************************************
	Config config; // configuration data for the app
	Market market; // market from which the quotes are harvested
	Database database; // database to which the quotes are to be saved
	Timer timer; // timer to execute consecutive quote harvests
	
	//*******************************************
	// 1b Setters & Getters
	//*******************************************	
	
	
	//*******************************************
	// 2a Constructors
	//*******************************************
	public ForExHarvester(Market market, Database database) {
		this.config = new Config("harvester");
		this.market = market;
		this.database = database;
		timer = new Timer();
		timer.schedule(this, 0, Long.parseLong(config.getProperty("checkPeriod")) * 1000);
	}
	
	//*******************************************
	// 2b Switches
	//*******************************************
	

	//*******************************************
	// 2c Interfaces
	//*******************************************	
	@Override
	public void run() {
		Quote quote = market.getLatestQuote(Instant.now());
		if (quote != null) {
			System.out.println("ForEx for EUR/USD quote harvested:" +
					" market - " + quote.marketName +
					" / local market quote timestamp - " + LocalDateTime.ofInstant(quote.getInstant(), market.getZoneId()) +
					" / quote rate  - " + quote.getRate());
			database.saveQuote(quote);
		}
	}
	
	//*******************************************
	// 3a Private methods
	//*******************************************
	
	
	//*******************************************
	// 3b Private classes
	//*******************************************
	
}