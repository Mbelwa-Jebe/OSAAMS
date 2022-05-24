<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $faqs = array();
        $data = $db->prepare('SELECT * FROM faqs');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($faqs,array(
                   
                    'faqs_id' => $OutputData['faqs_id'],
                    'faqs_question' => $OutputData['faqs_question'],
                    'faqs_answer' => $OutputData['faqs_answer'],
                
    
            ));
       
        }

       $faqs = [ 'faqs'=> $faqs];

        echo  json_encode($faqs,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>