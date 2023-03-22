package com.example.btl.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.models.Class_GiaoDich;
import com.example.btl.R;

import java.util.ArrayList;

public class Adapter_ListView_ThongKe extends BaseAdapter {
    //Nguồn dữ liệu cho adapter
    private ArrayList<Class_GiaoDich> data;
    private ArrayList<Class_GiaoDich> databackup;
    //Ngữ cảnh ứng dụng
    private Activity context;
    //Đối tượng phân tích layout
    private LayoutInflater inflater;

    public Adapter_ListView_ThongKe(){

    }

    public Adapter_ListView_ThongKe(ArrayList<Class_GiaoDich> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Class_GiaoDich> getData() {
        return data;
    }

    public void setData(ArrayList<Class_GiaoDich> data) {
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
            v = inflater.inflate(R.layout.activity_listview_thongke, null);
        TextView tvDM = v.findViewById(R.id.tvDM);
        tvDM.setText(data.get(i).getTenDanhMuc());
        TextView tvTienDM = v.findViewById(R.id.tvTienDM);
        tvTienDM.setText(String.valueOf(data.get(i).getSoTienNhap()) + " đ");
        return v;
    }
}
