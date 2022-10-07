<?php
require_once 'config.php';
class API {
    function select(){
        $db = new Connect;
        $messages = array();
        $data = $db->prepare('SELECT message_id,student_id,advisor_id,timestamp, content, message_from  FROM messages WHERE content = (SELECT MAX(content)
         FROM students,messages WHERE registration_no = student_id
        ORDER BY message_id DESC) GROUP BY student_id
    
       ');
        $data->execute();

        while($OutputData = $data->fetch(PDO::FETCH_ASSOC)){

            array_push($messages,array(
                   
                    'message_id' => $OutputData['message_id'],
                    'student_id' => $OutputData['student_id'],
                    'advisor_id' => $OutputData['advisor_id'],
                    'time' => $OutputData['timestamp'],
                    'content' => $OutputData['content'],
                    'message_from' => $OutputData['message_from'],
                
    
            ));
       
        }

       $messages = [ 'messages'=> $messages];

        echo  json_encode($messages,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
    }

}


$API = new API;
header('Content-Type: application/json');
echo $API->select();

?>