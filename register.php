<?php
$hostname = "localhost";
$username = "root";
$password = "";
$dbname = "test";
 
 $con = mysqli_connect($hostname,$username,$password,$dbname);
 
 $name = $_POST['name'];
 $email = $_POST['email'];
 $password = $_POST['password'];
 $phone = $_POST['phone'];
 
  $Sql_Query = "insert into users (name,email,password,phone) values ('$name','$email','$password','$phone')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Inserted Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>