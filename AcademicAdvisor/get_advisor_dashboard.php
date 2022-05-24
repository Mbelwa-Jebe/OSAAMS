<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $adviror_dashboard = array();
        $data = $db->prepare('SELECT (select count(*) from consultations )');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($adviror_dashboard,array(
                   
                    'faqs_id' => $OutputData['faqs_id'],
                    'faqs_question' => $OutputData['faqs_question'],
                    'faqs_answer' => $OutputData['faqs_answer'],
                
    
            ));
       
        }

       $faqs = [ 'faqs'=> $adviror_dashboard];

        echo  json_encode($adviror_dashboard,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>