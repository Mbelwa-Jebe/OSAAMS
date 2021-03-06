<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $advisors = array();
        $data = $db->prepare('SELECT * FROM students,advisors WHERE students.advisor_id = advisors.advisor_id GROUP BY `registration_no`');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($advisors,array(
                   
                    'advisor_fname' => $OutputData['advisor_fname'],
                    'advisor_lname' => $OutputData['advisor_lname'],
                    'advisor_id' => $OutputData['advisor_id'],
                    'advisor_office' => $OutputData['advisor_office'],
                    'advisor_rank' => $OutputData['advisor_rank'],
                    'advisor_contacts' => $OutputData['advisor_contacts'],
                    'student_id' => $OutputData['registration_no'],
    
            ));
       
        }

       $advisors = [ 'advisors'=> $advisors];

        echo  json_encode($advisors,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>