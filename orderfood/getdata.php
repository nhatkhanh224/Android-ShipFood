<?php
require "dbCon.php";
$query="SELECT * FROM loaisanpham";
$data=mysqli_query($connect,$query);

class LoaiSanPham{
    function LoaiSanPham($id_loaisp,$tenloai,$anh_loai,$url)
    {
        $this->ID=$id_loaisp;
        $this->TenLoai=$tenloai;
        $this->AnhLoai=$anh_loai;
        $this->Url=$url;
    }
}
    $mangLoai= array();
    while($row=mysqli_fetch_assoc($data))
{
    array_push($mangLoai,new LoaiSanPham($row['id_loaisp'],$row['tenloai'],$row['anh_loai'],$row['url']));
}
    //chuyen thanh dinh dang json
    echo json_encode($mangLoai);
?>