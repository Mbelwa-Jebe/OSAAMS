<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $students = array();
        $data = $db->prepare('SELECT * FROM students ');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($students,array(

                'registration_no' => $OutputData['registration_no'],
                    'student_fname' => $OutputData['student_fname'],
                    'student_lname' => $OutputData['student_lname'],
                    'programme' => $OutputData['programme'],
                    'advisor_id' => $OutputData['advisor_id'],
            ));
       
        }

       $students = [ 'students'=> $students];

        echo  json_encode($students,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>