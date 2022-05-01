<?php


        $con = mysqli_connect("localhost","root","","academic_advisory");
        $appointments = array();
        
        
       

        //$advisordata= $db->prepare('SELECT advisor_fname FROM advisors ORDER BY advisor_id');
       if($con){
        $sql ="SELECT * FROM appointments,advisors,students ORDER BY appointment_id";
         $result = (mysqli_query($con,$sql));  
               if($result){
                $i = 0;

                while($row = mysqli_fetch_assoc($result)){
                        
                        
                       $appointments[$i]['appointment_id'] = $row ['appointment_id'];
                       $$appointments[$i]['request_info'] = $row ['request_info'];
                       $$appointments[$i]['response'] = $row ['response'];
                       $response[$i]['student_fname'] = $row ['student_fname'];
                      $response[$i]['student_lname'] = $row ['student_lname'];
                       $response[$i]['advisor_fname'] = $row ['advisor_fname'];
                      $response[$i]['advisor_lname'] = $row ['advisor_lname'];
                     $response[$i]['advisor_id'] = $row ['advisor_id'];
                       $$appointments[$i]['time_stamp'] = $row ['timestamp'];
                      
                        $i++;
    
                }
                
              echo json_encode($appointments,JSON_PRETTY_PRINT);
           }
               // 'request_info' =  $OutputData['request_info'];

                //'response' =  $OutputData['response'];

               // 'student_fname' =  $OutputData['student_fname'];
               // 'student_lname' = $OutputData['student_lname'],

               // 'advisor_fname' = $OutputData['advisor_fname'],
              //  'advisor_lname' = $OutputData['advisor_lname'],

               // 'advisor_id' = $OutputData['advisor_id'],
               // 'time_stamp' = $OutputData['timestamp'],
                
               // );


       }
       else{
               echo "db error";
       }
    
      // $appointments = json_encode(array('appointments' => $appointments),JSON_PRETTY_PRINT);
       // $appointments = (object)[ 'appointments'=> [$appointments]
            
        //];
       // echo  json_encode($appointments,JSON_PRETTY_PRINT,JSON_FORCE_OBJECT);
      // echo $appointments;


?>