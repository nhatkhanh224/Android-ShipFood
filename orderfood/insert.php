<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "orderfood";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
$tenkhachhang=$_POST['tenkhachhang'];
$sdt=$_POST['sdt'];
$diachi=$_POST['diachi'];
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO `orderfood`.`order`(`id`, `tenkhachhang`, `sdt`, `diachi`) VALUES (null, '$tenkhachhang','$sdt', '$diachi')";

if ($conn->query($sql) === TRUE) {
  $id_donhang=$conn->insert_id;
  echo $id_donhang;
  
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>