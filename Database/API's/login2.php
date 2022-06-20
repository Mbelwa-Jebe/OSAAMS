<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $registration_no = trim($_POST['registration_no']) ;
   $password = trim($_POST['password']) ;

  // $registration_no = "1234";
  // $password = "1234";
	
    $sql = "SELECT students.*,advisors.* FROM students,advisors WHERE student_password = MD5('$password') OR advisor_password = MD5('$password') ";
	$resultset = mysqli_query($conn, $sql) or die("database error:". mysqli_error($conn));
	$row = mysqli_fetch_assoc($resultset);	
	

	
		
		if($row['registration_no']==$registration_no && $row['student_password']==MD5($password) ){	
					

				echo "student";
			
			}
		

		else if($row['advisor_id']==$registration_no && $row['advisor_password']==MD5($password)){
						
		
		echo "advisor";
	
	}

			else
		echo "wrong psd";

		//echo "success";		
				
}

?>