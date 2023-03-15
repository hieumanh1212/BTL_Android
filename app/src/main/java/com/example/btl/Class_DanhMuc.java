package com.example.btl;

import java.util.Date;

public class Class_DanhMuc {

    private String maDanhMuc;
    private String tenDanhMuc;
    private String loaiDanhMuc;

    public Class_DanhMuc(){}

    public Class_DanhMuc(String maDanhMuc, String tenDanhMuc, String loaiDanhMuc) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.loaiDanhMuc = loaiDanhMuc;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getLoaiDanhMuc() {
        return loaiDanhMuc;
    }

    public void setLoaiDanhMuc(String loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
    }
}
