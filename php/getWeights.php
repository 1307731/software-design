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
	
	global $connect;
	
	if (!$connect) {
    	die("Connection failed: " . mysqli_connect_error());
	}

	$sql = "SELECT * FROM WEIGHTS";
	$result = mysqli_query($connect, $sql);

	if (mysqli_num_rows($result) > 0) {
    // output data of each row
    	while($row = mysqli_fetch_assoc($result)) {
            $WEIGHTS = array();
            $WEIGHTS["SUBURB"] = $row["W_SUBURB"];
            $WEIGHTS["STREET_ADDRESS"] = $row["W_STREET_ADDRESS"];
            $WEIGHTS["PLOT_SIZE"] = $row["W_PLOT_SIZE"];
            $WEIGHTS["HOUSE_SIZE"] = $row["W_HOUSE_SIZE"];
            $WEIGHTS["NUM_BEDROOMS"] = $row["W_NUM_BEDROOMS"];
            $WEIGHTS["NUM_BATHROOMS"] = $row["W_NUM_BATHROOMS"];
            $WEIGHTS["NUM_GARAGES"] = $row["W_NUM_GARAGES"];
            $WEIGHTS["POOL"] = $row["W_POOL"];
    	    //echo "USERNAME: " . $row["USERNAME"]. " - PASSWORD: " . $row["PASSWORD"]. "<br>";
	    }
	    $response["success"] = 1;
        // user node
        $response["WEIGHTS"] = array();

        array_push($response["WEIGHTS"], $WEIGHTS);

        // echoing JSON response
        echo json_encode($response);
        
	} else {
	    // no users found
        $response["success"] = 0;
        $response["message"] = "No WEIGHTS found";
        // echo no users JSON
        echo json_encode($response);
	}

mysqli_close($connect);
}


?>
