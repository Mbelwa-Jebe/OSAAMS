<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $consultation_id = trim($_POST['consultation_id']) ;
    $report = trim($_POST['updated_report']) ;
   //$consultation_id = "4";
  // $report = "tested";

     $sql = "UPDATE `consultations` SET `report` = '$report' WHERE `consultations`.`consultation_id` = '$consultation_id'" ;
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>