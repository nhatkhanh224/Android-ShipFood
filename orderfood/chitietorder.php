<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "orderfood";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
$json=$_POST['json'];
// $json='[{"madonhang":22,"masanpham":23,"tensanpham":"khanh","gia":"10000","soluong":100}]';
$data=json_decode($json,true);
foreach($data as $value) {
    $madonhang=$value['madonhang'];
    $masanpham=$value['masanpham'];
    $tensanpham=$value['tensanpham'];
    $gia=$value['gia'];
    $soluong=$value['soluong'];
    $query = "INSERT INTO `chitietorder`(`id`, `madonhang`,`masanpham`,`tensanpham`, `gia`, `soluong`) VALUES (null, '$madonhang','$masanpham','$tensanpham','$gia', '$soluong')";
    $Dta=mysqli_query($conn,$query);
}
if($Dta){
    echo "1";
}else
{
    echo "0";
}
 ?>