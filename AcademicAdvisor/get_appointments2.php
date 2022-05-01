<?php

$con = mysqli_connect("localhost","root","","academic_advisory");
$sql = ('SELECT * FROM appointments ');
$response = array();
$results = mysqli_query($con,$sql);

        if($results){
                $i = 0;
            while($row = mysqli_fetch_assoc($results)){
                    
                    for($i=0;$i < count($row);$i++){
                   $response[$i]['appointment_id'] = $row ['appointment_id'];
                   $response[$i]['request_info'] = $row ['request_info'];
                   $response[$i]['response'] = $row ['response'];
                  // $response[$i] ['student_fname'] = $row['student_fname'];
                  // $response[$i] ['student_lname'] = $row['student_lname'];
                  // $response[$i] ['advisor_fname'] = $row['advisor_fname'];
                 //  $response[$i] ['advisor_lname'] = $row['advisor_lname'];
                 //  $response[$i] ['advisor_id'] = $row['advisor_id'];
                   $response[$i]['time_stamp'] = $row ['timestamp'];
                  $i++;
                    }
                    $response = json_encode(array('appointments' => $response),JSON_PRETTY_PRINT);

            }echo $response;
            //echo json_encode($response,JSON_PRETTY_PRINT);
        }

?>