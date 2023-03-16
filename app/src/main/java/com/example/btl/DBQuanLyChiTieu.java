package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
    public static final String LoaiDanhMuc = "LoaiDanhMuc";

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
                + LoaiDanhMuc + " Text)";
        db.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xóa bảng TableContact đã có
        db.execSQL("Drop table if exists " + TableDanhMuc);
        //Tạo lại
        onCreate(db);
    }

    //Lấy tất cả các dòng của bảng TableDanhMuc trả về dạnh ArrayList
    public ArrayList<Class_DanhMuc> getAllDanhMuc() {
        ArrayList<Class_DanhMuc> list = new ArrayList<>();
        //Câu truy vấn
        String sql = "Select * from " + TableDanhMuc;
        //Lấy đối tượng CSDL SQLITE
        SQLiteDatabase db = this.getReadableDatabase();
        //Chạy câu truy vấn trả về dạng Cursor
        Cursor cursor = db.rawQuery(sql, null);
        //Tạo ArrayList<Contact> để trả về
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Class_DanhMuc contact = new Class_DanhMuc(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2));
                list.add(contact);
            }
        }
        return list;
    }

    //Hàm thêm một contact vào bảng TableDanhMuc
    public void addDanhMuc(Class_DanhMuc contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MaDanhMuc, contact.getMaDanhMuc());
        value.put(TenDanhMuc, contact.getTenDanhMuc());
        value.put(LoaiDanhMuc, contact.getLoaiDanhMuc());
        db.insert(TableDanhMuc, null, value);
        db.close();
    }

    public void updateDanhMuc(String madanhmuc, Class_DanhMuc contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        //value.put(Id, contact.getId());
        value.put(MaDanhMuc, contact.getMaDanhMuc());
        value.put(TenDanhMuc, contact.getTenDanhMuc());
        value.put(LoaiDanhMuc, contact.getLoaiDanhMuc());

        db.update(TableDanhMuc, value, "MaDanhMuc = '" + madanhmuc + "'", null);
        db.close();
    }

    public void deleteDanhMuc(String madanhmuc)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TableDanhMuc, "MaDanhMuc = '" + madanhmuc + "'", null);
    }

}
