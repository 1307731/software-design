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

       // $stmt = $connect->prepare("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
       // $stmt->bind_param("ss",$USERNAME,$PASSWORD);
        $USERNAME = $_POST["USERNAME"];
        $PASSWORD = $_POST["PASSWORD"];
       // $stmt->execute();

       // $numrows = $stmt->affected_rows;
       // if(!$numrows){
      //          echo "1";
       // }else{
       //         echo "0";
       // }

       $query = "SELECT count(*) AS NUMRESULT FROM USERS WHERE USERNAME = '$USERNAME' AND PASSWORD = '$PASSWORD'";

       $result = mysqli_query($connect, $query);
       if($result->fetch_object()->NUMRESULT == 1){
       		//success
       		echo 0;
       }else{
       		//failure
       		echo 1;
       }
		//echo ($result->fetch_object()->NUMRESULT);
        mysqli_close($connect);
	
       // $stmt->close();
        //$connect->close();
}

?>

