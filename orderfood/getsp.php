<?php
require "dbCon.php";

$page=$_GET['page'];
$idsp=$_POST['idsanpham'];
$space=5;
$limit=($page-1)*$space;
// $idloai=$_POST['id_loaisp'];
$query="SELECT * FROM sanpham where id_loaisp=$idsp LIMIT $limit,$space ";
$data=mysqli_query($connect,$query);

class SanPham{
    function SanPham($id_sanpham,$id_loaisp,$ten_sanpham,$url_sp,$mieuta,$price)
    {
        $this->ID=$id_sanpham;
        $this->ID_LOAI=$id_loaisp;
        $this->SanPham=$ten_sanpham;
        $this->UrlSP=$url_sp;
        $this->MieuTa=$mieuta;
        $this->Gia=$price;
    }
}
    $mangSP= array();
    while($row=mysqli_fetch_assoc($data))
{
    array_push($mangSP,new SanPham($row['id_sanpham'],$row['id_loaisp'],$row['ten_sanpham'],$row['url_sp'],$row['mieuta'],$row['price']));
}
    //chuyen thanh dinh dang json
    echo json_encode($mangSP);
?>