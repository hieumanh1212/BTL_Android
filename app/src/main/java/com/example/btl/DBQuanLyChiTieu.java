package com.example.btl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBQuanLyChiTieu extends SQLiteOpenHelper {

    public static final String TableGiaoDich = "GiaoDich";
    public static final String MaGiaoDich = "MaGiaoDich";
    public static final String LoaiGiaoDich = "LoaiGiaoDich";
    public static final String NgayGiaoDich = "NgayGiaoDich";
    public static final String GhiChu = "GhiChu";
    public static final String SoTienNhap = "SoTienNhap";

    public static final String TableDanhMuc = "DanhMuc";
    public static final String MaDanhMuc = "MaDanhMuc";
    public static final String TenDanhMuc = "TenDanhMuc";
    public static final String LoaiDanhMuc = "TenDanhMuc";

    public DBQuanLyChiTieu(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo câu SQL để tạo bảng GiaoDich
        String sqlCreate = " Create table if not exists " + TableGiaoDich + "("
                + MaGiaoDich + " Text Primary key, "
                + LoaiGiaoDich + " Integer, "
                + NgayGiaoDich + " Date, "
                + GhiChu + " Text, "
                + SoTienNhap + " Integer)";
        //Chạy câu truy vấn SQL để tạo bảng
        db.execSQL(sqlCreate);
        //Tạo câu SQL để tạo bảng DanhMuc
        String sqlCreate2 = " Create table if not exists " + TableDanhMuc + "("
                + MaDanhMuc + " Text Primary key, "
                + TenDanhMuc + " Text, "
                + LoaiDanhMuc + " Integer)";
        db.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
