<?php
require_once 'connection.php';

if($_SERVER['REQUEST_METHOD']=='POST') {
	

    $question = trim($_POST['consultation_id']) ;
    $answer = trim($_POST['consultation_id']) ;

  
  $question = "question";
  $answer ="answer";

     $sql = "INSERT INTO `faqs` (`faqs_id`, `faqs_question`, `faqs_answer`) VALUES (NULL, '$question', '$answer')";
   mysqli_query($conn,$sql);
				

		echo "success";		

	
}

?>