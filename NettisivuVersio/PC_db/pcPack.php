<?php 

// Connecting to database

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "tietokone_paketit";

$conn = mysqli_connect($servername, $username, $password, $dbname);

if (!$conn) {
	die("Connection failed: " . mysqli_connect_error());
} 
	
// --------------------------------------------------
//           Show products part of code
// --------------------------------------------------

$sqlSearchProduct = "SELECT * FROM paketit";
$result = $conn->query($sqlSearchProduct);

$buffer = "";
$counter = 1;

// Here starts the code to build the table that shows products

if ($result->num_rows > 0) {

	$buffer .= '<table class="paketitTable">';
	$buffer .= "<tr>";
	
// Building the top of the table
	
	$buffer .= '<td class="ctr"><b>ID</b></td>
		<td class="ctr"><b>Emolevy ID</b></td>
		<td class="ctr"><b>Prosessori ID</b></td>
		<td class="ctr"><b>Virtalähde ID</b></td>
		<td class="ctr"><b>Muisti ID</b></td>
		<td class="ctr"><b>Näytönohjain ID</b></td>
		<td class="ctr"><b>Levy ID</b></td>
		<td class="ctr"><b>Kotelo ID</b></td>';
		
	$buffer .= "</tr>";
	
// Getting the package information
	
	while($row = $result->fetch_assoc()) {
		
		$buffer .= '<tr id="row'. $counter .'">';
		
// Gathering package information
		
		$buffer .= '<td class="ctr" name="id">'. $row["id"].
			'</td><td class="ctr">'. $row["mb_id"].
			'</td><td class="ctr">'. $row["cpu_id"].
			'</td><td class="ctr">'. $row["pwr_id"].
			'</td><td class="ctr">'. $row["ram_id"].
			'</td><td class="ctr">'. $row["gpu_id"]. 
			'</td><td class="ctr">'. $row["strg_id"]. 
			'</td><td class="ctr">'. $row["case_id"]. "</td>";
		
// Adding edit + delete buttons
		
		$buffer .= '<td style="width:30px"><button type="button" class="editBTN '. $counter . '">🛠</button></td>';
		$buffer .= '<td style="width:30px"><button type="button" onClick = "deleteConfirmation('. $counter .')" class="delBTN '. $counter . '">🗙</button></td>';
		$buffer .= "</tr>";
		$counter++;
	}
	
	$buffer .= "</tr>";
	$buffer .= "</table>";
	
} else {
	$buffer .= "0 results";
}

$conn->close();
?>


<!---------------------------------------------------------------------------------------------------->
<!--                            Here starts the HTML + CSS part of code                             -->
<!---------------------------------------------------------------------------------------------------->


<!DOCTYPE html>
<html>
	<head>
		<link rel="Stylesheet" href="css/stylesPack.css" type="text/css">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400&display=swap" rel="stylesheet">

<!-- Heres the JavaScript for the page -->
		
		<script>
			
		// Functions for Deleting products
			
			function deleteConfirmation(rowNum) {
				document.getElementById("delWarnBG").style.display = "initial";
				sessionStorage.setItem('rowNum', rowNum);
			}
			
			function deleteCancel() {
				document.getElementById("delWarnBG").style.display = "none";
				console.log("Product deletion cancelled.");
			}
			
			function deleteConfirm() {
				var sessionRowNum = sessionStorage.getItem('rowNum')
				
				document.getElementById("delWarnBG").style.display = "none";
				
				var rowProdID = document.getElementById("row" + sessionRowNum).firstChild.innerHTML;  + rowProdID
				console.log("Row number: " + sessionRowNum + "\nRows product ID: " + rowProdID);

				console.log("Product deleted.");
			}
			
			function redirProducts() {
				window.location.replace('http://localhost/PC_db/pcComps.php');
			}
			
		</script>
		
<!-- Continuation of HTML code -->
		
	</head>
	<body>
		<div id="navBar">
			<div id="title">Computer Database App</div>
			<button type="button" onClick="redirProducts()" id="productSide">Products</button>
			<button type="button" disabled onClick="" id="packageSide">PC Packages</button>
		</div>
		<div id="tableBG"><?php echo($buffer); ?></div>
		<div id="cr">© Topias Laitinen 2022</div>
		
<!-- HTML related to product edit + delete -->
		
		<div id="editBG">
			<div id="editTitle"><b>Editing and Deleting Packages</b></div>
			<div id="warning">NOTE: This function has yet to be implemented!</div>
			<div id="editHelp">The buttons for this function have been implemented,-
			<br> but the buttons themselves dont have any functions yet.
			<br>The plan is to implement these functions in the near future.</div>
		</div>
		
		<div id="delWarnBG">
			<div id="delWarnContainer">
				<div id="delWarnTXT1">WARNING</div>
				<div id="delWarnTXT2">Are you sure that you wish to delete your chosen package?
				<br>If you press delete, the package will be deleted from the database forever.</div>
				<button type="button" onClick="deleteCancel()" id="delCancel">No, Go back</button>
				<form name="deleteForm" method="POST" action="index.php">
					<button type="button" onClick="deleteConfirm()" id="delConfirm">Yes, Delete package</button>
				</form>
			</div>
		</div>
		
<!-- HTML related to adding products -->
		
		<div id="addBG">
			<div id="addTitle"><b>Adding a Package</b></div>
			<div id="addHelp">Fill in the ID:s of the products you want to be included in the package.
			<br>NOTE: Currently the adding only supports one product of each category per package.</div>
		</div>
		
	</body>
</html>