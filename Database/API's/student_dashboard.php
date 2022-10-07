<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $student_dash = array();
        $data = $db->prepare('SELECT registration_no, COUNT(DISTINCT consultations.consultation_id) AS reports,advisor_fname,advisor_lname, COUNT(DISTINCT appointments.appointment_id) AS ap_to_count 
        FROM students
        LEFT JOIN consultations 
        ON students.registration_no = consultations.student_id

        LEFT JOIN appointments
        ON students.registration_no = appointments.ap_to
    

        LEFT JOIN advisors
        ON students.advisor_id = advisors.advisor_id
         GROUP BY registration_no');
        $data->execute();
        //students.registration_no = appointments.ap_from && students.registration_no = appointments.ap_to 
        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($student_dash,array(

                'registration_no' => $OutputData['registration_no'],
                    'reports' => $OutputData['reports'],
                    'appointments_received' => $OutputData['ap_to_count'],
                    'advisor_fname' => $OutputData['advisor_fname'],
                    'advisor_lname' => $OutputData['advisor_lname'],
            ));
       
        }

       $student_dash = [ 'student_dash'=> $student_dash];

        echo  json_encode($student_dash,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>