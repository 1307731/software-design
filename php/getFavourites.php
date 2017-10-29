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
	$USER_ID = $_POST["USER_ID"];
	
	global $connect;
	
	if (!$connect) {
    	die("Connection failed: " . mysqli_connect_error());
	}

	$sql = "SELECT H.* FROM HOUSES AS H JOIN FAVOURITES AS F ON H.house_id = F.HOUSE_ID WHERE F.USER_ID = $USER_ID";
	$result = mysqli_query($connect, $sql);
	if (mysqli_num_rows($result) > 0) {
    // output data of each row
    	$HOUSES = array();
    	$i = 0;
    	while($row = mysqli_fetch_assoc($result)) {
            $HOUSE = array();
            $HOUSE["HOUSE_ID"] = $row["house_id"];
            $HOUSE["ADDRESS"] = $row["address"];
            $HOUSE["PLOT_AREA"] = $row["plot_area"];
            $HOUSE["HOUSE_AREA"] = $row["house_area"];
            $HOUSE["POOL"] = $row["pool"];
            $HOUSE["LATITUDE"] = $row["latitude"];
            $HOUSE["LONGITUDE"] = $row["longitude"];
            $HOUSE["SUBURB"] = $row["suburb"];
            $HOUSE["NUM_BEDROOMS"] = $row["num_bedrooms"];
            $HOUSE["NUM_BATHROOMS"] = $row["num_bathrooms"];
            $HOUSE["NUM_GARAGES"] = $row["num_garages"];
            $HOUSE["EVALUATION"] = $row["evaluation"];
    	    $HOUSES[$i] = array();
    	    array_push($HOUSES[$i], $HOUSE);
    	    $i = $i + 1;
	    }
	    $response["success"] = 1;
        // user node
        $response["HOUSE"] = array();

        array_push($response["HOUSE"], $HOUSES);

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
