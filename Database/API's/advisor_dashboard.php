<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $advisor_dash = array();
        $data = $db->prepare('SELECT advisors.advisor_id, COUNT(DISTINCT consultations.consultation_id) AS reports,COUNT(DISTINCT students.registration_no) AS students_allocated, COUNT(DISTINCT appointments.appointment_id) AS ap_to_count 
        FROM advisors
        LEFT JOIN consultations 
        ON advisors.advisor_id = consultations.advisor_id

        LEFT JOIN appointments
        ON advisors.advisor_id = appointments.ap_to

        LEFT JOIN students
        ON advisors.advisor_id = students.advisor_id

         GROUP BY advisor_id');
        $data->execute();
        //students.registration_no = appointments.ap_from && students.registration_no = appointments.ap_to 
        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($advisor_dash,array(

                'advisor_id' => $OutputData['advisor_id'],
                    'reports' => $OutputData['reports'],
                    'appointments_received' => $OutputData['ap_to_count'],
                    'students_allocated' => $OutputData['students_allocated'],
                
            ));
       
        }

       $advisor_dash = [ 'advisor_dash'=> $advisor_dash];

        echo  json_encode($advisor_dash,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>