<?php 
/*
 * All database connection variables
 */

define('user', "s1037363"); // db user
define('password', "mmldk"); // db password (mention your db password here)
define('databaseName', "d1037363"); // database name
define('hostname', "127.0.0.1"); // db server

$connect = mysqli_connect(hostname, user, password, databaseName);

?>

