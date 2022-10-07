<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $registration_no = trim($_POST['registration_no']) ;
    $content = trim($_POST['message']) ;
//    $student_id = trim($_POST['report_stdntid']) ;
  // $registration_no = "1234";
  // $content = "naomba kuuliza swali";
  $from = "student";
	
    $data = ("SELECT * FROM students WHERE registration_no = '$registration_no'");
    $results = mysqli_query($conn,$data);
    $OutputData = mysqli_fetch_assoc($results);
    $advisor_id = $OutputData['advisor_id'];
     $sql = "INSERT INTO `messages` (`message_id`, `student_id`, `advisor_id`, `timestamp`, `content`, `message_from`) VALUES (NULL, '$registration_no', '$advisor_id', current_timestamp(), '$content', '$from')";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>