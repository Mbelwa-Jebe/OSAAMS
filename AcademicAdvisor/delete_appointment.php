<?php
require_once 'connection.php';

//if($_SERVER['REQUEST_METHOD']=='POST') {
	

   // $apointment_id = trim($_POST['appointment_id']) ;
  
   $apointment_id = "5";

     $sql = "DELETE FROM `appointments` WHERE `appointments`.`appointment_id` = $apointment_id ";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
//}

?>