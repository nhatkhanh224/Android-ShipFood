package com.example.orderfood.model;

public class KhachHang {
    private int id_khachhang;
    private String ten;
    private String sdt;
    private String diachi;

    public KhachHang(int id_khachhang, String ten, String sdt, String diachi) {
        this.id_khachhang = id_khachhang;
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public KhachHang() {
    }

    public KhachHang(String ten) {
        this.ten = ten;
    }

    public int getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(int id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
