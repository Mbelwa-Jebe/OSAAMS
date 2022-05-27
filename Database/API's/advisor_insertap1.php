<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $registration_no = trim($_POST['registration_no']) ;
    $request_info = trim($_POST['request_info']) ;
   $student_id = trim($_POST['student_idtxt']) ;
  // $registration_no = "1234";
  //$request_info = " advisor insert_test";
  //$student_id = "1234";
	
//$data = ("SELECT * FROM students WHERE registration_no = '$registration_no'");
   // $results = mysqli_query($conn,$data);
   // $OutputData = mysqli_fetch_assoc($results);
  //  $advisor_id = $OutputData['advisor_id'];
     $sql = "INSERT INTO `appointments` (`appointment_id`, `student_id`, `advisor_id`, `response`, `request_info`, `timestamp`) VALUES (NULL, '$student_id', '$registration_no', 'waiting', '$request_info', current_timestamp())";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>