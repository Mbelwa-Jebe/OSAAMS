<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $consultation_id = trim($_POST['apointment_id']) ;
  
  // $apointment_id = "5";

     $sql = "DELETE FROM consultations WHERE consultation_id = $consultation_id ";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>