package forexmonitor;

class Database_Mock extends Database{

	@Override
	boolean saveQuote(Quote quote) {
		System.out.println("Quote data saved to database");
		return true;
	}

}
