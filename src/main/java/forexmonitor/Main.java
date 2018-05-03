package forexmonitor;

import forexmonitor.ForExHarvester;

public class Main {	
	public static void main(String[] args) {
		Market market = new Market_InvestingCom();
		Database database = new Database_Mock();	
		new ForExHarvester(market, database);	
		System.out.println("*** ForEx Harvester started ***");		
	}
}
