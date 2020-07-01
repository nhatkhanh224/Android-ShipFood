package com.example.orderfood.model;

public class LoaiSP {
    private int id_loaisp;
    private String tenloai;
    private String anh_loai;
    private String url;

    public LoaiSP(int id_loaisp, String tenloai, String anh_loai, String url) {
        this.id_loaisp = id_loaisp;
        this.tenloai = tenloai;
        this.anh_loai = anh_loai;
        this.url = url;
    }

    public LoaiSP() {
    }

    public int getId_loaisp() {
        return id_loaisp;
    }

    public void setId_loaisp(int id_loaisp) {
        this.id_loaisp = id_loaisp;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getAnh_loai() {
        return anh_loai;
    }

    public void setAnh_loai(String anh_loai) {
        this.anh_loai = anh_loai;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
