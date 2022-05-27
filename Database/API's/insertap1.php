<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $registration_no = trim($_POST['registration_no']) ;
   $request_info = trim($_POST['request_info']) ;
 // $registration_no = "1234";
  // $request_info = "voila";
	
    $data = ("SELECT * FROM students WHERE registration_no = '$registration_no'");
    $results = mysqli_query($conn,$data);
    $OutputData = mysqli_fetch_assoc($results);
    $advisor_id = $OutputData['advisor_id'];
     $sql = "INSERT INTO `appointments` (`appointment_id`, `student_id`, `advisor_id`, `response`, `request_info`, `ap_from`, `ap_to`, `timestamp`) VALUES (NULL, '$registration_no', '$advisor_id', 'waiting', '$request_info','$registration_no','$advisor_id', current_timestamp())";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>