package com.example.orderfood.model;

import java.io.Serializable;

public class SanPham implements Serializable{
    private int id_sanpham;
    private int id_loaisp;
    private String ten_sanpham;
    private String url_sp;
    private String mieuta;
    private Integer gia;

    public SanPham() {
    }

    public SanPham(int id_sanpham, int id_loaisp, String ten_sanpham, String url_sp, String mieuta, Integer gia) {
        this.id_sanpham = id_sanpham;
        this.id_loaisp = id_loaisp;
        this.ten_sanpham = ten_sanpham;
        this.url_sp = url_sp;
        this.mieuta = mieuta;
        this.gia = gia;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public int getId_loaisp() {
        return id_loaisp;
    }

    public void setId_loaisp(int id_loaisp) {
        this.id_loaisp = id_loaisp;
    }

    public String getTen_sanpham() {
        return ten_sanpham;
    }

    public void setTen_sanpham(String ten_sanpham) {
        this.ten_sanpham = ten_sanpham;
    }

    public String getUrl_sp() {
        return url_sp;
    }

    public void setUrl_sp(String url_sp) {
        this.url_sp = url_sp;
    }

    public String getMieuta() {
        return mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
