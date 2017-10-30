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

    $USER_ID = $_POST["USER_ID"];
    $HOUSE_ID = $_POST["HOUSE_ID"];
	
	$stmt2 = $connect->prepare("SELECT count(*) FROM FAVOURITES WHERE USER_ID = ? AND HOUSE_ID = ?");
	$stmt2->bind_param("ii", $USER_ID, $HOUSE_ID);
	$stmt2->execute();
	$stmt2->bind_result($num);
	$stmt2->fetch();
	$stmt2->close();
	if($num==1){
		//existing user
		echo 5;
	}else{
		$stmt = $connect->prepare("INSERT INTO FAVOURITES(USER_ID, HOUSE_ID) VALUES (?,?)");
		$stmt->bind_param("ii", $USER_ID, $HOUSE_ID);
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

