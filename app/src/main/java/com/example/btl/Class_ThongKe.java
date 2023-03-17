package com.example.btl;

public class Class_ThongKe {
    private String TenDanhMuc;
    private int Sotien;

    public Class_ThongKe(){}

    public Class_ThongKe(String tenDanhMuc, int sotien) {
        TenDanhMuc = tenDanhMuc;
        Sotien = sotien;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public int getSotien() {
        return Sotien;
    }

    public void setSotien(int sotien) {
        Sotien = sotien;
    }
}
