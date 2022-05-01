<?php
require_once 'connection.php';

//if($_SERVER['REQUEST_METHOD']=='POST') {
	

   // $apointment_id = trim($_POST['appointment_id']) ;
  
   $consultation_id = "4";
   $report = "rejected";

     $sql = "UPDATE `consultations` SET `report` = '$report' WHERE `consultations`.`consultation_id` = '$consultation_id'" ;
   mysqli_query($conn,$sql);
				

		echo "success";		

	
//}

?>