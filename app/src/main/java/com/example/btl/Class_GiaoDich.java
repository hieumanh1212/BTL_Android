package com.example.btl;

import java.util.Date;

public class Class_GiaoDich {
    private String maGiaoDich;
    private int loaiGiaoDich;
    private Date ngayGiaoDich;
    private String ghiChu;
    private int soTienNhap;


    public Class_GiaoDich() { }

    public Class_GiaoDich(String maGiaoDich, int loaiGiaoDich, Date ngayGiaoDich, String ghiChu, int soTienNhap) {
        this.maGiaoDich = maGiaoDich;
        this.loaiGiaoDich = loaiGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.ghiChu = ghiChu;
        this.soTienNhap = soTienNhap;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public int getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(int loaiGiaoDich) {
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
}
