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

    $USERNAME = $_POST["USERNAME"];
    $PASSWORD = $_POST["PASSWORD"];
    $EMAIL = $_POST["EMAIL"];
    $USER_TYPE = $_POST["USER_TYPE"];
	$PHONENUMBER = $_POST["PHONENUMBER"];
	$NAME = $_POST["NAME"];
	$SURNAME = $_POST["SURNAME"];
	
	$stmt2 = $connect->prepare("SELECT count(*) FROM USERS WHERE USERNAME = ?");
	$stmt2->bind_param("s", $USERNAME);
	$stmt2->execute();
	$stmt2->bind_result($num);
	$stmt2->fetch();
	$stmt2->close();
	if($num==1){
		//existing user
		echo 5;
	}else{
		$stmt = $connect->prepare("INSERT INTO USERS(ID,USERNAME,PASSWORD,EMAIL,USER_TYPE,PHONENUMBER,NAME,SURNAME) VALUES (?,?,?,?,?,?,?,?)");
		$stmt->bind_param("isssssss",$null,$USERNAME,$PASSWORD,$EMAIL,$USER_TYPE,$PHONENUMBER,$NAME,$SURNAME);
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

