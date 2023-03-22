package com.example.btl.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.btl.models.Class_DanhMuc;
import com.example.btl.R;

import java.util.ArrayList;

public class Adapter_DanhMuc extends BaseAdapter {
    //Nguồn dữ liệu cho adapter
    private ArrayList<Class_DanhMuc> data;
    private ArrayList<Class_DanhMuc> databackup;
    //Ngữ cảnh ứng dụng
    private Activity context;
    //Đối tượng phân tích layout
    private LayoutInflater inflater;

    public Adapter_DanhMuc(){

    }

    public Adapter_DanhMuc(ArrayList<Class_DanhMuc> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Class_DanhMuc> getData() {
        return data;
    }

    public void setData(ArrayList<Class_DanhMuc> data) {
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
        TextView tvTenDM = v.findViewById(R.id.tvTenDM);
        tvTenDM.setText(data.get(i).getTenDanhMuc());
        TextView tvLoaiDM = v.findViewById(R.id.tvLoaiDM);
        tvLoaiDM.setText(data.get(i).getLoaiDanhMuc());
        return v;
    }

    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                //Backup dữ liệu: lưu tạm data vào databackup
                if(databackup == null)
                    databackup = new ArrayList<>(data);
                //Nếu chuỗi để filter là rỗng thì khôi phục dữ liệu
                if(charSequence == null || charSequence.length() == 0)
                {
                    fr.count = databackup.size();
                    fr.values = databackup;
                }
                //CÒn nếu rỗng thì thực hiện filter
                else
                {
                    ArrayList<Class_DanhMuc> newdata = new ArrayList<>();
                    for(Class_DanhMuc c:databackup)
                        if(c.getTenDanhMuc().toLowerCase().contains(
                                charSequence.toString().toLowerCase()))
                            newdata.add(c);
                    fr.count = newdata.size();
                    fr.values = newdata;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                data = new ArrayList<Class_DanhMuc>();
                ArrayList<Class_DanhMuc> tmp = (ArrayList<Class_DanhMuc>)filterResults.values;
                for(Class_DanhMuc c:tmp)
                    data.add(c);
                notifyDataSetChanged();
            }
        };
        return f;
    }
}
