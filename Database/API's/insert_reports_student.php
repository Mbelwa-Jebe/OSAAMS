<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $student_id = trim($_POST['registration_no']) ;
    $report = trim($_POST['report']) ;
  // $student_id = trim($_POST['report_stdntid']) ;
  // $student_id = "1234";
  //$report = "student_upload_test";
 // $advisor_id = "100";
	
    $data = ("SELECT * FROM students WHERE registration_no = '$student_id'");
    $results = mysqli_query($conn,$data);
   $OutputData = mysqli_fetch_assoc($results);
    $advisor_id = $OutputData['advisor_id'];
     $sql = "INSERT INTO `consultations` (`consultation_id`, `advisor_id`, `student_id`, `report`, `report_date`) VALUES (NULL, '$advisor_id', '$student_id', '$report', current_timestamp())";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>