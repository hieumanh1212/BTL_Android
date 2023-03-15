package com.example.btl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterChiTietGiaoDichTheoNgay extends BaseAdapter implements Filterable {

    // nguon du lieu cho Adapter
    private ArrayList<BaoCaoLichSu> data;
    // sao luu nguon du lieu
    private ArrayList<BaoCaoLichSu> databackup;

    // ngu canh cua ung dung
    private Activity context;

    // doi tuong de phan tich lay out
    private LayoutInflater inflater;

    public AdapterChiTietGiaoDichTheoNgay() {
    }

    public AdapterChiTietGiaoDichTheoNgay(ArrayList<BaoCaoLichSu> data, Activity context) {
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<BaoCaoLichSu> getData() {
        return data;
    }

    public Activity getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setData(ArrayList<BaoCaoLichSu> data) {
        this.data = data;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public Object getItem(int position) {
        return data.get(position);
    }


    @Override
    public long getItemId(int position) {
        //return data.get(position).getLoaiGiaoDich();
        return -1;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null)
            v = inflater.inflate(R.layout.activity_chi_tiet_giao_dich_theo_ngay, null);

        TextView TenGiaoDich = v.findViewById(R.id.id_textView_TenDanhMuc);
        TenGiaoDich.setText(data.get(position).getTenDanhMuc());

        TextView ThoiGianGiaoDich = v.findViewById(R.id.id_textView_ThoiGianGiaoDich);
        ThoiGianGiaoDich.setText(
                String.valueOf(data.get(position).getNgayGiaoDich()) + "-" +
                        String.valueOf(data.get(position).getThangGiaoDich()) + "-" +
                        String.valueOf(data.get(position).getNamGiaoDich()));


        TextView MaGiaoDich = v.findViewById(R.id.id_textView_MaGiaoDich);
        MaGiaoDich.setText(data.get(position).getMaGiaoDich());
        TextView LoaiGiaoDich = v.findViewById(R.id.id_textView_LoaiGiaoDich);
        LoaiGiaoDich.setText(data.get(position).getLoaiGiaoDich());
        TextView TienGiaoDich = v.findViewById(R.id.id_textView_TienGiaoDich);
        if (LoaiGiaoDich.getText().equals("Chi")) {
            LoaiGiaoDich.setTextColor(Color.parseColor("#E91E63"));
            TienGiaoDich.setText(String.valueOf(-data.get(position).getTienGiaoDich()));
        } else {
            LoaiGiaoDich.setTextColor(Color.parseColor("#2196F3"));
            TienGiaoDich.setText(String.valueOf(data.get(position).getTienGiaoDich()));
        }

        return v;

    }

    @Override
    public Filter getFilter() {

        return null;
    }
}
