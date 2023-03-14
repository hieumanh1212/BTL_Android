package com.example.btl;

import java.util.Date;

public class GiaoDich {
    private String maGiaoDich;
    private Date ngayGiaoDich;
    private int loaiGiaoDich;
    private String chuThich;
    private int soTienNhap;
    private String tenDanhMuc;

    public GiaoDich() {
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public GiaoDich(String maGiaoDich, Date ngayGiaoDich, int loaiGiaoDich, String chuThich, int soTienNhap, String tenDanhMuc) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiGiaoDich = loaiGiaoDich;
        this.chuThich = chuThich;
        this.soTienNhap = soTienNhap;
        this.tenDanhMuc = tenDanhMuc;
    }

    public GiaoDich(String tenDanhMuc, int soTienNhap)
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

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public int getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(int loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public int getSoTienNhap() {
        return soTienNhap;
    }

    public void setSoTienNhap(int soTienNhap) {
        this.soTienNhap = soTienNhap;
    }
}

