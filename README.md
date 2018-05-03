# ForExMonitor
Candidate task for senior Java developer with SearchMetrics

The project is composed of three tiers:
- harvesting engine, created as Java application
- database, drafted in MySQL but not implemented in this version of project
- web page / API, created in HTML/JavaScript/PHP

Graphical sketch of relations between the tiers is provided in file *system sketch.pdf* and detailed textual description is given below.

Harvesting engine
-----------------
**Code components (detailed visual description provided in file *ForExMonitor_JavaApp.png*)**
- *Main.java* 
- *ForExHarvester.java*; *Config.java*; *harvester.cfg*
- *Market.java*; *Market_InvestingCom.java*; *Market_Test.java*
- *Database.java*; *Database_Mock.java*
- *ForExMonitorTest.java*
- *forexmonitor.jar*

**Functional description**
Harvesting engine Java app can be used for parallel harvesting of quote data from a number of different quote sources (Markets).

For each market to be harvested, a specific concrete extension of abstract *Market* class has to be constructed, with respective implementation of *getQuoteFromMarket()* method. Dedicated harvester can then be compiled with that *Market* class extension and run as distinct service on Java server, alongside any others. 

Data harvested by all harvesters is saved to the same database.

Harveting frequency of harvesters can be adjusted by setting the parameter in file *harvester.cfg*.

Junit business logic tests are packed into file *ForExMonitorTest.java*, executable using **mvn test** command.

File *forexmonitor.jar* is executable jar file which displays the harvester's functioning.

Database
--------
**Code components (detailed visual description provided in MySQL Workbench file "forexmonitor.mwb"**
Database has a single table, "quote", with three columns:
- quote_market (VARCHAR(20) name of the market fromm which the quote is obtained),
- quote_utctimestamp (BIGINT representation of timestamp when the sale with the respective quote was closed) and 
- quote_rate (DECIMAL(8,5) value of the quote).

It also contains three stored procedures: 
- *saveQuote*, required by the Java harvester for saving harvested data and
- *getLatesQuote* and *getHistoricalQuotes*, required by the web page / API

**Functional description**
Upon obtaining data on new quote, harvester(s) save data to database by calling the *saveQuote* storeproc.
Upon user's http request, web page / API produces a php call that triggers *getLatesQuote* or *getHistoricalQuotes* storeproc and returns the requesteed quote data to the caller.

Web page / API
--------------
**Code components**
Web page / API includes three files:
- *index.php*, home page and human user interface to the system, enables user to request either latest quote or historical quotes for a specified date range.
- *getLatestRate.php*, file that can be called either by humam user throught *index.php* or directly by an application as an API endpoint
- *getHistoricalRates.php*, file that can be called either by human user through *index.php* or directly by an application as an API endpoint, using following GET call: *../getHisoricalRates.php?fromDate=xxxx-xx-xx&toDate=yyyy-yy-yy
