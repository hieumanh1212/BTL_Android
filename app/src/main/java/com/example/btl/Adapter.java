package com.example.btl;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    //Nguồn dữ liệu cho adapter
    private ArrayList<DanhMuc_Class> data;
    private ArrayList<DanhMuc_Class> databackup;
    //Ngữ cảnh ứng dụng
    private Activity context;
    //Đối tượng phân tích layout
    private LayoutInflater inflater;

    public Adapter(){

    }

    public Adapter(ArrayList<DanhMuc_Class> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<DanhMuc_Class> getData() {
        return data;
    }

    public void setData(ArrayList<DanhMuc_Class> data) {
        this.data = data;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null)
            v = inflater.inflate(R.layout.danhmucitem, null);
        TextView tvMaDM = v.findViewById(R.id.tvMaDM);
        tvMaDM.setText(data.get(i).getMaDanhMuc());
        TextView tvTenDM = v.findViewById(R.id.tvTenDM);
        tvTenDM.setText(data.get(i).getTenDanhMuc());
        TextView tvLoaiDM = v.findViewById(R.id.tvLoaiDM);
        tvLoaiDM.setText(data.get(i).getLoaiDanhMuc());
        return v;
    }
}
