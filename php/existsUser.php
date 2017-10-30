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

       $query = createQuerySearch($USERNAME, $PASSWORD);

       $result = mysqli_query($connect, $query);
       processStatementExists($result->fetch_object()->NUMRESULT);
       mysqli_close($connect);

}

function createQuerySearch($USERNAME, $PASSWORD){
	//print_r($connect);
	$query1 = "SELECT count(*) AS NUMRESULT FROM USERS WHERE USERNAME = '$USERNAME' AND PASSWORD = '$PASSWORD'";
	return $query1;
}

function processStatementExists($numrows){
		if($numrows){
			//SUCCESS
			echo 0;
			return 0;
		}else{
			//success
			echo 1;
			return 1;
		}
}

?>

