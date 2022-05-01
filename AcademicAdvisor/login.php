<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    $registration_no = $_POST['registration_no'];
    $password = $_POST['password'];
    require_once('connection.php');
    $sql = "SELECT * FROM students WHERE registration_no = '$registration_no' AND password = '$password'";
    $result = mysqli_query($conn,$sql);
    $check = mysqli_fetch_array($result);
    if(isset($check)){
        echo 'success';
    }else{
        echo "failure";
    }

}
?>