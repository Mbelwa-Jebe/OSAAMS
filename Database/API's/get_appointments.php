<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $appointments = array();
        $data = $db->prepare('SELECT * FROM appointments,advisors,students GROUP BY appointment_id');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($appointments,array(

                'appointment_id' => $OutputData['appointment_id'],
                    'request_info' => $OutputData['request_info'],
                    'response' => $OutputData['response'],
                    'student_id' => $OutputData['student_id'],
    
                    'student_fname' => $OutputData['student_fname'],
                    'student_lname' => $OutputData['student_lname'],
    
                    'advisor_fname' => $OutputData['advisor_fname'],
                    'advisor_lname' => $OutputData['advisor_lname'],
    
                    'advisor_id' => $OutputData['advisor_id'],
                    'ap_from' => $OutputData['ap_from'],
                    'ap_to' => $OutputData['ap_to'],
                    
                    'time_stamp' => $OutputData['timestamp']
            ));
       
        }

       $appointments = [ 'appointments'=> $appointments];

        echo  json_encode($appointments,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>