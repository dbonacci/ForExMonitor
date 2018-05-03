package forexmonitor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import hr.voxpopuli.komponente.citacURLa.CitacURLa;

class Market_InvestingCom extends Market{
	//*******************************************
	// 1a Properties
	//*******************************************
	// web address of the data source
	String sourceAddress = "https://www.investing.com/currencies/eur-usd";
	// parameters for parsing data from the html source 
	String rateStart = "<span class=\"arial_26 inlineblock pid-1-last\" id=\"last_last\" dir=\"ltr\">";
	String rateEnd = "</span>";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s");
	String timestampStart = "<span class=\"bold pid-1-time\">";
	String timestampEnd = "</span>";

	//*******************************************
	// 1b Setters & Getters
	//*******************************************	
	
	
	//*******************************************
	// 2a Constructors
	//*******************************************
	Market_InvestingCom() {
		super.marketName = "InvestingCom";
		super.openTime = LocalTime.of(0, 0, 0);
		super.closeTime = LocalTime.of(23, 59, 59);
		super.zoneId = ZoneId.of("UTC-4");
	}

	//*******************************************
	// 2b Switches
	//*******************************************
	

	//*******************************************
	// 2c Interfaces
	//*******************************************
	@Override
	Quote getQuoteFromMarket() {
		BigDecimal rate; // variable for storing value of obtained ForEx quote
		Instant timestamp; // variale for storing timestamp of obtained ForEx quote
		String htmlSource; // variable for temporary storing of HTML source returned by ForEx quote provider
		int start; // variable for notifying starting point of quote timestamp/value parsing from HTML source
		int end; // variable for notifying ending point of qoute timestamp/value parsing from HTML source	
		Quote quote = null;
		try {
			htmlSource = CitacURLa.citajURL(sourceAddress);
			// get rate of obtained quote
			start = htmlSource.indexOf(rateStart) + rateStart.length();
			end = htmlSource.indexOf(rateEnd, start);
			rate = new BigDecimal(htmlSource.substring(start, end));
			// get timestamp of obtained quote
			LocalDateTime marketLDT = LocalDateTime.ofInstant(Instant.now(), zoneId);
			LocalDate quoteLD = marketLDT.toLocalDate();
			start = htmlSource.indexOf(timestampStart) + timestampStart.length();
			end = htmlSource.indexOf(timestampEnd, start);
			LocalTime quoteLT = LocalTime.parse(htmlSource.substring(start, end), formatter);
			LocalDateTime quoteLDT = LocalDateTime.of(quoteLD, quoteLT);
			timestamp = quoteLDT.atZone(zoneId).toOffsetDateTime().toInstant();
			quote = new Quote(marketName, timestamp, rate);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return quote;
	}
}
