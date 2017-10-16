<?php
//post method used
//check if recieved method is post

// array for JSON response
$response = array();

if($_SERVER["REQUEST_METHOD"]=="POST"){

        require 'dbConfig.php';
        getHouse();

}

function getHouse(){
	$ADDRESS = $_POST["ADDRESS"];
	
	global $connect;
	
	if (!$connect) {
    	die("Connection failed: " . mysqli_connect_error());
	}

	$sql = "SELECT evaluation_amount FROM HOUSE WHERE ADDRESS LIKE '$ADDRESS'";
	$result = mysqli_query($connect, $sql);

	if (mysqli_num_rows($result) > 0) {
    // output data of each row
    	while($row = mysqli_fetch_assoc($result)) {
            $HOUSE = array();
            $HOUSE["evaluation_amount"] = $row["evaluation_amount"];
            print_r($HOUSE);
    	    //echo "USERNAME: " . $row["USERNAME"]. " - PASSWORD: " . $row["PASSWORD"]. "<br>";
	    }
	    $response["success"] = 1;
        // user node
        $response["HOUSE"] = array();

        array_push($response["HOUSE"], $HOUSE);

        // echoing JSON response
        echo json_encode($response);
        
	} else {
	    // no users found
        $response["success"] = 0;
        $response["message"] = "No PRICE found";
        // echo no users JSON
        echo json_encode($response);
	}

mysqli_close($connect);
}


?>
