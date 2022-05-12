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

if(isset($_GET["search"])){
	$search = $_GET["search"];
}

else{
	$search = "prosessorit";
}

$sqlSearchProduct = "SELECT * FROM " . $search;
$result = $conn->query($sqlSearchProduct);

$buffer = "";
$counter = 1;

// Here starts the code to build the table that shows products

if ($result->num_rows > 0) {

	$buffer .= '<table class="tuoteTable">';
	$buffer .= "<tr>";
	
// Checking what product category is shown
	
// CPU + Motherboard Table
	
	if ($search == "prosessorit" || $search == "emolevyt") {
		$buffer .= '<td class="ctr"><b>ID</b></td>
			<td class="pad"><b>Manifacturer</b></td>
			<td class="pad"><b>Model</b></td>
			<td class="pad"><b>Socket</b></td>
			<td class="ctr"><b>Price</b></td>
			<td class="ctr"><b>In Storage</b></td>';
	
// RAM Table
	
	} else if ($search == "muistit") {
		$buffer .= '<td class="ctr"><b>ID</b></td>
			<td class="pad"><b>Manifacturer</b></td>
			<td class="pad"><b>Model</b></td>
			<td class="ctr"><b>Total Capacity</b></td>
			<td class="ctr"><b>Sticks</b></td>
			<td class="ctr"><b>Price</b></td>
			<td class="ctr"><b>In Storage</b></td>';
	
// HDD + SSD Table
	
	} else if ($search == "levyt") {
		$buffer .= '<td class="ctr"><b>ID</b></td>
			<td class="pad"><b>Manifacturer</b></td>
			<td class="pad"><b>Model</b></td>
			<td class="ctr"><b>Type</b></td>
			<td class="ctr"><b>Capacity</b></td>
			<td class="ctr"><b>Price</b></td>
			<td class="ctr"><b>In Storage</b></td>';
	
// Powersupply Table
	
	} else if ($search == "powerit") {
		$buffer .= '<td class="ctr"><b>ID</b></td>
			<td class="pad"><b>Manifacturer</b></td>
			<td class="pad"><b>Model</b></td>
			<td class="ctr"><b>Wattage</b></td>
			<td class="ctr"><b>Price</b></td>
			<td class="ctr"><b>In Storage</b></td>';
	
// Other Tables
	
	} else {
		$buffer .= '<td class="ctr"><b>ID</b></td>
			<td class="pad"><b>Manifacturer</b></td>
			<td class="pad"><b>Model</b></td>
			<td class="ctr"><b>Price</b></td>
			<td class="ctr"><b>In Storage</b></td>';
	}
	
	$buffer .= "</tr>";
	
// Getting the information based on what category was chosen
	
	while($row = $result->fetch_assoc()) {
		
		$buffer .= '<tr id="row'. $counter .'">';
		
// CPU + Motherboard information search
		
		if ($search == "prosessorit" || $search == "emolevyt") {
			$buffer .= '<td class="ctr" name="id">'. $row["id"].
			'</td><td class="pad">'. $row["valmistaja"].
			'</td><td class="pad">'. $row["malli"].
			'</td><td class="pad">'. $row["kanta"].
			'</td><td class="ctr">'. $row["hinta"]." €".
			'</td><td class="ctr">'. $row["varastossa"]. "</td>";
		
// RAM information search
		
		} else if ($search == "muistit") {
			$buffer .= '<td class="ctr" name="id'. $counter .'">'. $row["id"].
			'</td><td class="pad">'. $row["valmistaja"].
			'</td><td class="pad">'. $row["malli"].
			'</td><td class="ctr">'. $row["koko"]. " GB".
			'</td><td class="ctr">'. $row["maara"].
			'</td><td class="ctr">'. $row["hinta"]." €".
			'</td><td class="ctr">'. $row["varastossa"]. "</td>";
		
// HDD + SSD information search
		
		} else if ($search == "levyt") {
			$buffer .= '<td class="ctr" name="id'. $counter .'">'. $row["id"].
			'</td><td class="pad">'. $row["valmistaja"].
			'</td><td class="pad">'. $row["malli"]. 
			'</td><td class="ctr">'. $row["tyyppi"].
			'</td><td class="ctr">'. $row["koko"]. " GB".
			'</td><td class="ctr">'. $row["hinta"]." €".
			'</td><td class="ctr">'. $row["varastossa"]. "</td>";
		
// Powersupply information search
		
		} else if ($search == "powerit") {
			$buffer .= '<td class="ctr" name="id'. $counter .'">'. $row["id"].
				'</td><td class="pad">'. $row["valmistaja"].
				'</td><td class="pad">'. $row["malli"].
				'</td><td class="ctr">'. $row["teho"]." W".
				'</td><td class="ctr">'. $row["hinta"]." €".
				'</td><td class="ctr">'. $row["varastossa"]. "</td>";
		
// Information search for other categories
		
		} else {
			$buffer .= '<td class="ctr" name="id'. $counter .'">'. $row["id"].
				'</td><td class="pad">'. $row["valmistaja"]. 
				'</td><td class="pad">'. $row["malli"].
				'</td><td class="ctr">'. $row["hinta"]. " €".
				'</td><td class="ctr">'. $row["varastossa"]. "</td>";
		}
		
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

// --------------------------------------------------------------------------
//             Adding products part of code
// --------------------------------------------------------------------------

/* Code for adding Motherboards */

if(isset($_POST['addMB'])) {
	MBAddProduct();
}

function MBAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$moboManifacturer = $_POST['valmistaja'];
	$moboModel = $_POST['malli'];
	$moboSocket = $_POST['kanta'];
	$moboPrice = $_POST['hinta'];
	$moboStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO emolevyt (valmistaja, malli, kanta, hinta, varastossa) VALUES ('$moboManifacturer','$moboModel','$moboSocket','$moboPrice','$moboStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding CPU:s */

if(isset($_POST['addCPU'])) {
	CPUAddProduct();
}

function CPUAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$cpuManifacturer = $_POST['valmistaja'];
	$cpuModel = $_POST['malli'];
	$cpuSocket = $_POST['kanta'];
	$cpuPrice = $_POST['hinta'];
	$cpuStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO prosessorit (valmistaja, malli, kanta, hinta, varastossa) VALUES ('$cpuManifacturer','$cpuModel','$cpuSocket','$cpuPrice','$cpuStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding RAM */

if(isset($_POST['addRAM'])) {
	RAMAddProduct();
}

function RAMAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$ramManifacturer = $_POST['valmistaja'];
	$ramModel = $_POST['malli'];
	$ramTotal = $_POST['koko'];
	$ramCount = $_POST['maara'];
	$ramPrice = $_POST['hinta'];
	$ramStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO muistit (valmistaja, malli, koko, maara, hinta, varastossa) VALUES ('$ramManifacturer','$ramModel','$ramTotal','$ramCount','$ramPrice','$ramStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding GPU:s */

if(isset($_POST['addGPU'])) {
	GPUAddProduct();
}

function GPUAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$gpuManifacturer = $_POST['valmistaja'];
	$gpuModel = $_POST['malli'];
	$gpuPrice = $_POST['hinta'];
	$gpuStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO naytonohjaimet (valmistaja, malli, hinta, varastossa) VALUES ('$gpuManifacturer','$gpuModel','$gpuPrice','$gpuStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding HDD:s and SSD:s */

if(isset($_POST['addSTRG'])) {
	STRGAddProduct();
}

function STRGAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$strgManifacturer = $_POST['valmistaja'];
	$strgModel = $_POST['malli'];
	$strgType = $_POST['hdd/ssd'];
	$strgCapacity = $_POST['koko'];
	$strgPrice = $_POST['hinta'];
	$strgStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO levyt (valmistaja, malli, tyyppi, koko, hinta, varastossa) VALUES ('$strgManifacturer','$strgModel','$strgType','$strgCapacity','$strgPrice','$strgStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding Powersupplies */

if(isset($_POST['addPWR'])) {
	PWRAddProduct();
}

function PWRAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$powerManifacturer = $_POST['valmistaja'];
	$powerModel = $_POST['malli'];
	$powerWattage = $_POST['teho'];
	$poweriPrice = $_POST['hinta'];
	$poweriStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO powerit (valmistaja, malli, teho, hinta, varastossa) VALUES ('$powerManifacturer','$powerModel','$powerWattage','$poweriPrice','$poweriStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

/* Code for adding Cases */

if(isset($_POST['addCASE'])) {
	CASEAddProduct();
}

function CASEAddProduct() {
	
	/* Connecting to SQL-server */

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tietokone_paketit";
	
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	$caseManifacturer = $_POST['valmistaja'];
	$caseModel = $_POST['malli'];
	$casePrice = $_POST['hinta'];
	$caseStorage = $_POST['varastossa'];

	$sqlAddProduct = "INSERT INTO kotelot (valmistaja, malli, hinta, varastossa) VALUES ('$caseManifacturer','$caseModel','$casePrice','$caseStorage')";

	if(mysqli_query($conn, $sqlAddProduct)) {
		echo "<script type=\"text/javascript\">window.location.replace('http://localhost/PC_db/redir.html');</script>";
	} else {
		echo "ERROR: ". mysqli_error($conn);
	}
}

if(isset($_POST['delConfirm'])) {
	deleteProduct();
}

function deleteProduct() {
	
}

$conn->close();
?>


<!---------------------------------------------------------------------------------------------------->
<!--                            Here starts the HTML + CSS part of code                             -->
<!---------------------------------------------------------------------------------------------------->


<!DOCTYPE html>
<html>
	<head>
		<link rel="Stylesheet" href="css/stylesComps.css" type="text/css">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400&display=swap" rel="stylesheet">

<!-- Heres the JavaScript for the page -->
		
		<script>
			
		// Function for Adding products
			
			function addProduct(what) {
				if (!what || what == null) {
					var category = document.getElementById("addCategory").innerHTML;
				}
				var category = what;
				var buffer = "";
				
				if (category == "MB") {
					
			// Form for adding Motherboards
					
					buffer += '<form name="MBInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Socket</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="kanta" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addMB" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "CPU") {
			
			// Form for adding CPU:s
			
					buffer += '<form name="CPUInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Socket</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="kanta" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addCPU" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "RAM") {
					
			// Form for adding RAM
					
					buffer += '<form name="RAMInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model, DDR_</b></td>' + 
						'<td><b>Total Capacity</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="koko" type="text"></td>';
					buffer += '</tr><tr>';
					buffer += '<td><b>Sticks</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += '</tr><tr>';
					buffer += '<td><input name="maara" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addRAM" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "GPU") {
					
			// Form for adding GPU:s
					
					buffer += '<form name="GPUInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addGPU" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "STRG") {
					
			// Form for adding HHD:s + SSD:s
					
					buffer += '<form name="STRGInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Type</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input type="radio" name="hdd/ssd" value="HDD">';
					buffer += '<label for="HDD">HDD</label>';
					buffer += '<input type="radio" name="hdd/ssd" value="SSD">';
					buffer += '<label for="SSD">SSD</label></td>';
					buffer += '</tr><tr>';
					buffer += '<td><b>Capacity (GB)</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += '</tr><tr>';
					buffer += '<td><input name="koko" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addSTRG" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "PWR") {
					
			// Form for adding Powersupplies
					
					buffer += '<p>    WARNING! Please input the wattage of a powersupply only as numbers!' +
						'<br>    Example: 550 OR 1000</p>';
					
					buffer += '<form name="PWRInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Wattage</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="teho" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addPWR" value="Add" />';
					
					buffer += "</form>";
					
				} else if (category == "CASE") {
					
			// Form for adding Cases
					
					buffer += '<form name="CASEInfoSend" action = "#" method = "POST">';
					buffer += '<table class="addTable">';
					buffer += '<tr>';
					
					buffer += '<td><b>Manifacturer</b></td>' + 
						'<td><b>Model</b></td>' + 
						'<td><b>Price</b></td>' + 
						'<td><b>In Storage</b></td>';
					buffer += "</tr>";
					
					buffer += "<tr>";
					buffer += '<td><input name="valmistaja" type="text"></td>';
					buffer += '<td><input name="malli" type="text"></td>';
					buffer += '<td><input name="hinta" type="text"></td>';
					buffer += '<td><input name="varastossa" type="text"></td>';
					
					buffer += "</tr>";
					buffer += "</table>";
					
					buffer += '<input type="submit" name="addCASE" value="Add" />';
					
					buffer += "</form>";
					
				}
				document.getElementById("productInfo").innerHTML = buffer;
			}
			
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
			
			function redirPackage() {
				window.location.replace('http://localhost/PC_db/pcPack.php');
			}
			
		</script>
		
<!-- Continuation of HTML code -->
		
	</head>
	<body>
		<body onLoad="addProduct()">
		<div id="navBar">
			<div id="title">Computer Database App</div>
			<button type="button" disabled onClick="" id="productSide">Products</button>
			<button type="button" onClick="redirPackage()" id="packageSide">PC Packages</button>
		</div>
		<div id="tableBG"><?php echo($buffer); ?></div>
		<div id="cr">© Topias Laitinen 2022</div>
		
<!-- HTML related to product search -->
		
		<div id="searchBG">
			<div id="searchTitle"><b>Product Search</b></div>
			<div id="searchHelp">Choose the category of products you wish to see.
			<br>After that, press the "Search" button, and the table on the right will update.
			<br>
			<br>Note: The table does become scrollable after it reaches a certain height.</div>
			<form name="searchForm" method="GET" action="pcComps.php">
				<select id="tuotteet" name="search">
				<option value="emolevyt">Motherboards</option>
				<option value="prosessorit">CPU:s</option>
				<option value="muistit">RAM</option>
				<option value="naytonohjaimet">Graphics Cards</option>
				<option value="levyt">HDD:s / SSD:s</option>
				<option value="powerit">Powersupplies</option>
				<option value="kotelot">Cases</option>
				</select>
				<input type="submit" id="hakuBTN" value="Search" />
			</form>
		</div>
		
<!-- HTML related to product edit + delete -->
		
		<div id="editBG">
			<div id="editTitle"><b>Editing and Deleting Product information</b></div>
			<div id="warning">NOTE: This function has yet to be implemented!</div>
			<div id="editHelp">The buttons for this function have been implemented,-
			<br> but the buttons themselves dont have any functions yet.
			<br>The plan is to implement these functions in the near future.</div>
		</div>
		
		<div id="delWarnBG">
			<div id="delWarnContainer">
				<div id="delWarnTXT1">WARNING</div>
				<div id="delWarnTXT2">Are you sure that you wish to delete your chosen product?
				<br>If you press delete, the product will be deleted from the database forever.</div>
				<button type="button" onClick="deleteCancel()" id="delCancel">No, Go back</button>
				<form name="deleteForm" method="POST" action="index.php">
					<button type="button" onClick="deleteConfirm()" id="delConfirm">Yes, Delete product</button>
				</form>
			</div>
		</div>
		
<!-- HTML related to adding products -->
		
		<div id="addBG">
			<div id="addTitle"><b>Adding a Product</b></div>
			<div id="addHelp">Choose a category from the selection box, in which you wish to add a product.
			<br>After that, fill in the information of the product,-
			<br> and then press "Add" to submit the info to the database.</div>
			<select id="addCategory" name="addCategory" onChange = "addProduct(this.value);">
				<option>Pick a category</option>
				<option value="MB">Motherboard</option>
				<option value="CPU">CPU</option>
				<option value="RAM">RAM</option>
				<option value="GPU">Graphics Card</option>
				<option value="STRG">HDD / SDD</option>
				<option value="PWR">Powersupply</option>
				<option value="CASE">Case</option>
			</select>
			<div id="productInfo"></div>
		</div>
		
	</body>
</html>