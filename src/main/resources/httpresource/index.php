<!DOCTYPE html>
<html>
<head>
	<title>ForEx Monitor</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<?php // preset historical range parameters for first page load
	$fromDate = date("Y-m-d");
	$toDate = date("Y-m-d");	
?>

</head>
<body onload="getLatestRate()">
<h2>ForEx Monitor</h2>

<form id="optionSelector">
	<fieldset>
		<legend>Select option</legend>		
		<div id="getLatestRate">
			<input type="button" value="Get latest rate" onclick="getLatestRate();" />
		</div>
		<hr>
		<div id="getHistoricalRates">
			<input type="button" value="Get historical rates" onclick="getHistoricalRates();" />
			<label for="fromDate">From date:</label>
			<input type="date" name="fromDate" value="<?php echo $fromDate ?>" onchange="promijeniDatum();" />
			<label for="toDate">To date:</label>
			<input type="date" name="toDate" value="<?php echo $toDate ?>" onchange="promijeniDatum();" />
		</div>
	</fieldset>
</form>
	
<div id="resultSet">
</div>

<script> 
	function $(x) {return document.getElementById(x);}
	
	function getLatestRate() {
		var optionSelector = $("optionSelector");
		var resultSet = $("resultSet");
		// create and run ajax call
		var xhttp = new XMLHttpRequest();
  		xhttp.onreadystatechange = function() {
   		if (this.readyState == 4 && this.status == 200) {
   			resultSet.innerHTML = this.responseText;
   	 	}
  		}
  		xhttp.open("GET", "getLatestRate.php", true);
  		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  		xhttp.send();
	}
	
	function getHistoricalRates() {
		var optionSelector = $("optionSelector");
		var resultSet = $("resultSet");
		var fromDate = optionSelector.elements["fromDate"];
		var toDate = optionSelector.elements["toDate"];
		var xhttp = new XMLHttpRequest();
  		xhttp.onreadystatechange = function() {
   		if (this.readyState == 4 && this.status == 200) {
   			resultSet.innerHTML = this.responseText;
   	 	}
  		}
  		xhttp.open("GET", "getHistoricalRates.php?fromDate=" + fromDate.value + "&toDate=" + toDate.value, true);
  		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  		xhttp.send();
	}
</script>

</body>
</html>