<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "academic_advisory";
$conn = new mysqli($servername,$username,$password,$database);
if ($conn->connect_error){
    die("connection failed".$connect_error);
}
else
echo(" ");
?>