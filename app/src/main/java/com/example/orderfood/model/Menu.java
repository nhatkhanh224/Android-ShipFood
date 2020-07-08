package com.example.orderfood.model;

public class Menu {
    public int Id;
    public String Tenloaisp;
    public int image;

    public Menu() {
    }

    public Menu(int id, String tenloaisp, int image) {
        Id = id;
        Tenloaisp = tenloaisp;
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
