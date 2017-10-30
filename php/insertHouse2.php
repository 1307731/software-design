<?php
//post method used
//check if recieved method is post
if($_SERVER["REQUEST_METHOD"]=="POST"){

        require 'dbConfig.php';
        createUser();

}

function createUser(){
    //var outside function
    global $connect;

    $ADDRESS = $_POST["ADDRESS"];
    $SUBURB = $_POST["SUBURB"];
    //$SUBURB_AVG_PRICE = $_POST["SUBURB_AVG_PRICE"];
    $PLOT_AREA = $_POST["PLOT_AREA"];
	$HOUSE_AREA = $_POST["HOUSE_AREA"];
	$BEDROOMS_NUM = $_POST["BEDROOMS_NUM"];
	$BATHROOMS_NUM = $_POST["BATHROOMS_NUM"];
	$GARAGES_NUM = $_POST["GARAGES_NUM"];
	$POOL = $_POST["POOL"];
	$EVALUATION_AMOUNT = $_POST["EVALUATION_AMOUNT"];
	$USER_ID = $_POST["USER_ID"];
	

		$stmt = $connect->prepare("INSERT INTO HOUSES(house_id, address, plot_area, house_area, pool, latitude, longitude, suburb, num_bedrooms, num_bathrooms, num_garages, evaluation, user_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		$stmt->bind_param("isddiiisiiidi", $null, $ADDRESS, $PLOT_AREA, $HOUSE_AREA, $POOL, $null, $null, $SUBURB, $BEDROOMS_NUM, $BATHROOMS_NUM, $GARAGES_NUM, $EVALUATION_AMOUNT, $USER_ID);
		$stmt->execute();
		//print_r($stmt);
        $numrows = $stmt->affected_rows;
		if(!$numrows){
			//fail
			echo 1;
		}else{
			//success
			$sql = "SELECT house_id FROM HOUSES WHERE address LIKE '$ADDRESS' and evaluation LIKE '$EVALUATION_AMOUNT'";
			$result = mysqli_query($connect, $sql);
    		$row = mysqli_fetch_assoc($result);
            $HOUSE = $row["house_id"];
            echo 0 . ' ' . $HOUSE;
		}

        $stmt->close();
	
	
    $connect->close();
}

?>
