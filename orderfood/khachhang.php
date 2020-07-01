<?php
require "dbCon.php";
$tenkhachhang="khanh";
$sdt="0914170417";
$diachi="Quang tri";
$query="INSERT INTO order VALUES('khanh','0914170417','quang tri')";
if(mysqli_query($connect,$query))
{
    echo"Success"; 
}
else{
    echo"Error";
}
 ?>