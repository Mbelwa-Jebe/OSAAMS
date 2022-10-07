<?php
require_once 'connection.php';

//if($_SERVER['REQUEST_METHOD']=='POST') {
	

    // $registration_no = trim($_POST['registration_no']) ;
    // $content = trim($_POST['report']) ;
    // $student_id = trim($_POST['report_stdntid']) ;
   $registration_no = "100";
   $content = "sawa uliza";
  $student_id= "1234";
  $from = "advisor";
	
     $sql = "INSERT INTO `messages` (`message_id`, `student_id`, `advisor_id`, `timestamp`, `content`, `message_from`) VALUES (NULL, '$student_id', '$registration_no', current_timestamp(), '$content', '$from')";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
//}

?>