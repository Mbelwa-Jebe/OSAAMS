<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

   $advisor_id = trim($_POST['registration_no']) ;
   $password = trim($_POST['password']) ;
//    $advisor_id = "101";
//   $password = "101";

     $sql = " UPDATE `advisors` SET `advisor_password` = MD5('$password') WHERE `advisors`.`advisor_id`  = '$advisor_id' " ;
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>