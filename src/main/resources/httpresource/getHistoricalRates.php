<?php // preuzimanje parametara iz pozivajuće stranice
	$fromDate = $_GET["fromDate"];
	$toDate = $_GET["toDate"];
?>

<?php
	echo "<fieldset>";
		echo "<h3>Historical ForEx rates for EUR/USD</h3>";
		echo "<div>";
			echo "(historical rates from {$fromDate} to {$toDate})";
		echo "</div>";
	echo "</fieldset>";
?>