<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

   $student_id = trim($_POST['registration_no']) ;
   $password = trim($_POST['password']) ;
   $oldpass2 = trim($_POST['oldpass2']);
//    $student_id = "6789";
//   $password = "6789";
$data = "SELECT * FROM students WHERE registration_no = '$student_id'";
    $results = mysqli_query($conn,$data);
    $OutputData = mysqli_fetch_assoc($results);
    $old_pass1 = $OutputData['student_password'];
if($old_pass1 == $oldpass2){

    $sql = " UPDATE `students` SET `student_password` = '$password' WHERE `students`.`registration_no` = '$student_id' " ;
   mysqli_query($conn,$sql);
				

		echo "success";	
}
    else echo "failed" 	;

	
}

?>