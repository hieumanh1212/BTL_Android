package com.example.btl;


public class BaoCaoLichSu
{
    private String MaGiaoDich;
    private String LoaiGiaoDich;
    private double TienGiaoDich;
    private String TenDanhMuc;
    private int NgayGiaoDich;
    private int thangGiaoDich;
    private int NamGiaoDich;


    public BaoCaoLichSu(String maGiaoDich, String loaiGiaoDich, double tienGiaoDich, String tenDanhMuc, int ngayGiaoDich, int thangGiaoDich, int namGiaoDich) {
        MaGiaoDich = maGiaoDich;
        LoaiGiaoDich = loaiGiaoDich;
        TienGiaoDich = tienGiaoDich;
        TenDanhMuc = tenDanhMuc;
        NgayGiaoDich = ngayGiaoDich;
        this.thangGiaoDich = thangGiaoDich;
        NamGiaoDich = namGiaoDich;
    }
    public BaoCaoLichSu(){}

    public String getMaGiaoDich() {
        return MaGiaoDich;
    }
    public String getLoaiGiaoDich() {
        return LoaiGiaoDich;
    }

    public double getTienGiaoDich() {
        return TienGiaoDich;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public int getNgayGiaoDich() {
        return NgayGiaoDich;
    }

    public int getThangGiaoDich() {
        return thangGiaoDich;
    }

    public int getNamGiaoDich() {
        return NamGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        MaGiaoDich = maGiaoDich;
    }
    public void setLoaiGiaoDich(String loaiGiaoDich) {
        LoaiGiaoDich = loaiGiaoDich;
    }

    public void setTienGiaoDich(double tienGiaoDich) {
        TienGiaoDich = tienGiaoDich;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public void setNgayGiaoDich(int ngayGiaoDich) {
        NgayGiaoDich = ngayGiaoDich;
    }

    public void setThangGiaoDich(int thangGiaoDich) {
        this.thangGiaoDich = thangGiaoDich;
    }

    public void setNamGiaoDich(int namGiaoDich) {
        NamGiaoDich = namGiaoDich;
    }
}
