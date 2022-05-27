<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $registration_no = trim($_POST['registration_no']) ;
    $password = trim($_POST['password']) ;
	
    $sql = "SELECT * FROM students WHERE registration_no = '$registration_no' && password = '$password'";
	$resultset = mysqli_query($conn, $sql) or die("database error:". mysqli_error($conn));
	$row = mysqli_fetch_assoc($resultset);	
	
				
	if($row['password']==$password){				
		
    
		echo "success";		
		
	}
	
	
	else {				
		echo "email or password does not exist."; // wrong details 
	       }		
}

?>