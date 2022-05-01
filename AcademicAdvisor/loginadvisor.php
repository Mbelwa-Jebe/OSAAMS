<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $advisor_id = trim($_POST['advisor_id']) ;
    $password = trim($_POST['password']) ;
	
    $sql = "SELECT * FROM advisors WHERE advisor_id = '$advisor_id' && password = '$password'";
	$resultset = mysqli_query($conn, $sql) or die("database error:". mysqli_error($conn));
	$row = mysqli_fetch_assoc($resultset);	
	
				
	if($row['password']==$password){				
		echo "success";			
	}
	
	
	else {				
		echo "id or password does not exist."; // wrong details 
	       }		
}

?>