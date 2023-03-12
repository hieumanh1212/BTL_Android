package com.example.btl;

public class DanhMuc_Class {
    private String MaDanhMuc;
    private String TenDanhMuc;
    private String LoaiDanhMuc;

    public DanhMuc_Class() {

    }
    public DanhMuc_Class(String maDanhMuc, String tenDanhMuc, String loaiDanhMuc) {
        MaDanhMuc = maDanhMuc;
        TenDanhMuc = tenDanhMuc;
        LoaiDanhMuc = loaiDanhMuc;
    }

    public String getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getLoaiDanhMuc() {
        return LoaiDanhMuc;
    }

    public void setLoaiDanhMuc(String loaiDanhMuc) {
        LoaiDanhMuc = loaiDanhMuc;
    }
}
