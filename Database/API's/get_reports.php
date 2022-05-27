<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $reports = array();
        $data = $db->prepare('SELECT consultation_id,consultations.advisor_id,student_id,student_fname,student_lname,advisors.advisor_lname,advisors.advisor_fname,report,report_date FROM consultations,students,advisors WHERE consultations.advisor_id=advisors.advisor_id && consultations.student_id=students.registration_no GROUP BY consultation_id');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($reports,array(

                'consultation_id' => $OutputData['consultation_id'],
                'advisor_id' => $OutputData['advisor_id'],
                    'student_id' => $OutputData['student_id'],
    
                   'student_fname' => $OutputData['student_fname'],
                    'student_lname' => $OutputData['student_lname'],
                    'advisor_id' => $OutputData['advisor_id'],
    
                    'advisor_fname' => $OutputData['advisor_fname'],
                    'advisor_lname' => $OutputData['advisor_lname'],
                    'report' => $OutputData['report'],
    
                    
                    'report_date' => $OutputData['report_date']
            ));
       
        }

       $reports = [ 'reports'=> $reports];

        echo  json_encode($reports,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>