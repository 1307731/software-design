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
    $SUBURB_AVG_PRICE = $_POST["SUBURB_AVG_PRICE"];
    $PLOT_AREA = $_POST["PLOT_AREA"];
	$HOUSE_AREA = $_POST["HOUSE_AREA"];
	$BEDROOMS_NUM = $_POST["BEDROOMS_NUM"];
	$BATHROOMS_NUM = $_POST["BATHROOMS_NUM "];
	$POOL = $_POST["POOL"];
	$EVALUATION_AMOUNT = $_POST["EVALUATION_AMOUNT"];
	

		$stmt = $connect->prepare("INSERT INTO HOUSE(house_id,address,suburb,suburb_avg_price,plot_area,house_area,bedrooms_num,bathrooms_num) VALUES (?,?,?,?,?,?,?,?,?)");
		$stmt->bind_param("issdiiiiid",$null,$ADDRESS,$SUBURB,$SUBURB_AVG_PRICE,$PLOT_AREA,$HOUSE_AREA,$BEDROOMS_NUM,$BATHROOMS_NUM,$POOL,$EVALUATION_AMOUNT);
		$stmt->execute();
	
        $numrows = $stmt->affected_rows;
		if(!$numrows){
			//fail
			echo 1;
		}else{
			//success
			echo 0;
		}

        $stmt->close();
	}
	
    $connect->close();
}

?>
