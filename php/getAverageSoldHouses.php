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

	$sql = createSelectAverage();
	$result = sendRequestSelectAverage($connect, $sql);

	if (checkResult($result)) {
    // output data of each row
    	$HOUSE = array();
    	while($row = mysqli_fetch_assoc($result)) {
            $HOUSE["PLOT_AREA"] = $row["AVG(plot_area)"];
            $HOUSE["HOUSE_AREA"] = $row["AVG(house_area)"];
            $HOUSE["NUM_BEDROOMS"] = $row["AVG(num_bedrooms)"];
            $HOUSE["NUM_BATHROOMS"] = $row["AVG(num_bathrooms)"];
            $HOUSE["NUM_GARAGES"] = $row["AVG(num_garages)"];
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
        $response["message"] = "No Houses";
        // echo no users JSON
        echo json_encode($response);
	}

mysqli_close($connect);
}

function createSelectAverage(){
	$sql1 = "SELECT AVG(plot_area), AVG(house_area), AVG(num_bedrooms), AVG(num_bathrooms), AVG(num_garages), AVG(sold_price) FROM SOLDHOUSES";
	return $sql1;
}

function sendRequestSelectAverage($connect, $sql){

	$result = mysqli_query($connect, $sql);
	print_r($result);
	return $result;

}

function checkResult($result){

	if(mysqli_num_rows($result)>0){
		return 1;
	}
	
	return 0;
}



?>
