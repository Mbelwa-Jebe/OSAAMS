<?php
require_once 'connection.php';
$response = array();
if(isset($_GET['signin'])){
    if(isTheseParametersAvailable(array('registration_no','password'))){
        $registration_no = $_POST['registration_no'];
        $password = md5($_POST['password']);
        $stmt = $conn->prepare("SELECT * FROM students WHERE registration_no = ? AND password = ?");
        $stmt->bind_param("ss",$registration_no,$password);
        $stmt->execute();
        $stmt->store_result();
        if($stmt->num_rows > 0){
            $stmt->bind_result($registration_no,$student_fname,$student_lname,$programme,$year_of_study,$advisor_id);
            $stmt->fetch();
            $student = array(
                'registration_no'=>$registration_no,
                'student_fname'=>$student_fname,
                'student_lname'=>$student_lname,
                'programme'=>$programme,
                'year_of_study'=>$year_of_study
            );

            $response['error'] = false;
            $response['message'] = 'Login successful';
            $response['user'] = $registration_no;
        }
        else{
            $response['error'] = false;
            $response['message'] = 'invalid username or password';
        }
    }
    
}
    else{
        $respose['error'] = true;
        $response['message'] = 'invalid API call';
    }
    function isTheseParametersAvailable($params){
        foreach($params as $param){
            if(!isset($_POST[$param]))
            return false;
        }
    }
?>