<?php
//post method used
//check if recieved method is post
if($_SERVER["REQUEST_METHOD"]=="POST"){

        require 'dbConfig.php';
        createHouse();

}

function createHouse(){
    //var outside function
    global $connect;

    	$address = $_POST["address"];
    	$no_floors = $_POST["no_floors"];
    	$plot_area = $_POST["plot_area"];
    	$house_area = $_POST["house_area"];

	$stmt = $connect->prepare("INSERT INTO Houses(house_id,address,no_floors,plot_area,house_area) VALUES (?,?,?,?,?)");
	$stmt->bind_param("isiii",$null,$address,$no_floors,$plot_area,$house_area);
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


?>

