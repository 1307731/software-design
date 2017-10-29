<?php
//post method used
//check if recieved method is post
if($_SERVER["REQUEST_METHOD"]=="POST"){

        require 'dbConfig.php';
        createUser();

}

function insertUser(){
    //var outside function
    global $connect;

    $USERNAME = $_POST["USERNAME"];
    $PASSWORD = $_POST["PASSWORD"];
    $EMAIL = $_POST["EMAIL"];
    $USER_TYPE = $_POST["USER_TYPE"];
	$PHONENUMBER = $_POST["PHONENUMBER"];
	$NAME = $_POST["NAME"];
	$SURNAME = $_POST["SURNAME"];
	
	$stmt2 = userCheckStatement($connect);
	
	$stmt2->bind_param("s", $USERNAME);
	$stmt2->execute();
	$stmt2->bind_result($num);
	$stmt2->fetch();
	$stmt2->close();
	if($num==1){
		//existing user
		echo 5;
	}else{
	
		$stmt = createStatementInsert($connect);
		
		$testthing = $stmt->param_count;
		//echo $testthing;
		//echo '\n';
		$stmt->bind_param("isssssss",$null,$USERNAME,$PASSWORD,$EMAIL,$USER_TYPE,$PHONENUMBER,$NAME,$SURNAME);
        //echo '\n';
		$stmt->execute();
		$numrows = $stmt->affected_rows;
		
		processStatementInsert($numrows);

        $stmt->close();
	}
	
    $connect->close();
}

function processStatementInsert($numrows){
        
		if(!$numrows){
			//fail
			echo 1;
			return 1;
		}else{
			//success
			echo 0;
			return 0;
		}
}

function createStatementInsert($connect){
	//print_r($connect);
	$stmt1 = $connect->prepare("INSERT INTO USERS(ID,USERNAME,PASSWORD,EMAIL,USER_TYPE,PHONENUMBER,NAME,SURNAME) VALUES (?,?,?,?,?,?,?,?)");
	//print_r($stmt1);
	return $stmt1;
}

function userCheckStatement($connect){
	$stmt1 = $connect->prepare("SELECT count(*) FROM USERS WHERE USERNAME = ?");
	//print_r($stmt1);
	return $stmt1;
}
//delete from USERS where password like "123";
?>

