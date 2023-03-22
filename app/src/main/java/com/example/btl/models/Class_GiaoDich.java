package com.example.btl.models;

import java.util.Date;

public class Class_GiaoDich {
    private String maGiaoDich;
    private String loaiGiaoDich;
    private Date ngayGiaoDich;
    private String ghiChu;
    private int soTienNhap;
    private String tenDanhMuc;


    public Class_GiaoDich() { }

    public Class_GiaoDich(String maGiaoDich, String loaiGiaoDich, Date ngayGiaoDich, String ghiChu, int soTienNhap, String tenDanhMuc) {
        this.maGiaoDich = maGiaoDich;
        this.loaiGiaoDich = loaiGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.ghiChu = ghiChu;
        this.soTienNhap = soTienNhap;
        this.tenDanhMuc = tenDanhMuc;
    }

    public Class_GiaoDich(String tenDanhMuc, int soTienNhap)
    {
        this.tenDanhMuc = tenDanhMuc;
        this.soTienNhap = soTienNhap;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoTienNhap() {
        return soTienNhap;
    }

    public void setSoTienNhap(int soTienNhap) {
        this.soTienNhap = soTienNhap;
    }


    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
