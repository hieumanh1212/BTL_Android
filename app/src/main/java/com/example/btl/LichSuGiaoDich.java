package com.example.btl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LichSuGiaoDich extends AppCompatActivity {





    private Spinner  spinner_Nam;
    private Spinner  spinner_Thang;
    private TextView textView_TongGiaoDich;
    private TextView textView_NgayHomNay;
    private TextView textView_TongThuChi;
    private TextView textView_Thu;
    private TextView textView_Chi;
    private ArrayList<BaoCaoLichSu> BaoCaoLichSuList;
    private ListView ListChiTietBaoCao;
    private AdapterChiTietGiaoDichTheoNgay Adapter;
    private ArrayAdapter arrayAdapter_thang;
    private ArrayAdapter arrayAdapter_nam;
    private int indexListView=-1;
    private int check_thang = 0;
    private int check_nam = 0;
    private ArrayList<BaoCaoLichSu> dataSearch_thang;
    private ArrayList<BaoCaoLichSu> dataSearch_nam;
    private ArrayList<BaoCaoLichSu> data_search;

    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;
    public void AnhXa()
    {

        spinner_Thang = findViewById(R.id.id_Spinner_Thang);
        spinner_Nam = findViewById(R.id.id_Spinner_Nam);
        ListChiTietBaoCao = findViewById(R.id.id_ListView_BaoCao);
        textView_TongThuChi = findViewById(R.id.id_textView_TongThuChi);
        textView_Thu = findViewById(R.id.id_textview_Thu);
        textView_Chi= findViewById(R.id.id_textview_Chi);

    }
    public void TongThuChi(ArrayList<BaoCaoLichSu> DuLieu)
    {
        // Tong thu chi
        double thu = 0, chi = 0, s = 0;
        for(int i=0; i<DuLieu.size(); i++)
        {
            if(DuLieu.get(i).getLoaiGiaoDich().equals("Chi"))
                chi += DuLieu.get(i).getTienGiaoDich();
            else
                thu += DuLieu.get(i).getTienGiaoDich();
        }
        textView_TongThuChi.setText(String.valueOf(thu - chi));
        if(thu - chi<0)
            textView_TongThuChi.setTextColor(Color.parseColor("#E91E63"));
        else
            textView_TongThuChi.setTextColor(Color.parseColor("#2196F3"));
        textView_Thu.setText(String.valueOf(thu));
        textView_Chi.setText(String.valueOf(chi));
    }

    public void ThemNamThang()
    {
        // năm
        ArrayList<String> nam = new ArrayList<>();
        nam.add("---");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nam.add(String.valueOf(LocalDate.now().getYear()));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nam.add(String.valueOf(LocalDate.now().getYear()-1));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nam.add(String.valueOf(LocalDate.now().getYear()-2));
        }
        arrayAdapter_nam = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nam);
        // dãn dàng
        arrayAdapter_nam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Nam.setAdapter(arrayAdapter_nam);



        // tháng
        ArrayList<String> thang = new ArrayList<>();
        thang.add("---");
        for(int i=1; i<=12; i++)
            thang.add(String.valueOf(i));

        arrayAdapter_thang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, thang);
        arrayAdapter_thang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Thang.setAdapter(arrayAdapter_thang);

    }

    public void ThemDuLieu()
    {
        // đưa dữ liệu chi tiết báo cáo lên listView
        BaoCaoLichSuList = new ArrayList<>();
        BaoCaoLichSuList.add(new BaoCaoLichSu("1","Chi", 100000, "Ăn uống", 3, 3,2021));
        BaoCaoLichSuList.add(new BaoCaoLichSu("2", "Chi", 100000, "Mua sắm", 3, 9,2021));
        BaoCaoLichSuList.add(new BaoCaoLichSu("3","Thu", 700000, "Tiền lương", 3, 1,2023));
        BaoCaoLichSuList.add(new BaoCaoLichSu("4","Chi", 200000, "Đi chơi", 4, 1,2023));
        BaoCaoLichSuList.add(new BaoCaoLichSu("5","Chi", 100000, "Trả nợ", 2, 1,2023));
        BaoCaoLichSuList.add(new BaoCaoLichSu("6","Thu", 200000, "Chúng số", 12, 4,2021));
        BaoCaoLichSuList.add(new BaoCaoLichSu("7","Chi", 30000, "Sửa điện thoại", 10, 7,2021));
        BaoCaoLichSuList.add(new BaoCaoLichSu("8","Chi", 50000, "Ăn vặt", 1, 9,2022));
        BaoCaoLichSuList.add(new BaoCaoLichSu("9","Thu", 100000, "Tiền lương", 2, 9,2023));
        SapXepDuLieu(BaoCaoLichSuList);
        LoadDuLieu(BaoCaoLichSuList);

    }

    public void LoadDuLieu(ArrayList<BaoCaoLichSu> DuLieu)
    {
        Adapter = new AdapterChiTietGiaoDichTheoNgay(DuLieu, LichSuGiaoDich.this);
        ListChiTietBaoCao.setAdapter(Adapter);
    }

    public void SapXepDuLieu(ArrayList<BaoCaoLichSu> BaoCaoLichSuList)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            BaoCaoLichSuList.sort((p1, p2)->{
                if(p2.getNamGiaoDich()-p1.getNamGiaoDich()>0)
                    return 1;
                else if(p2.getNamGiaoDich()-p1.getNamGiaoDich()<0)
                    return -1;
                else
                {
                    if(p2.getThangGiaoDich()-p1.getThangGiaoDich()>0)
                        return 1;
                    else if(p2.getThangGiaoDich()-p1.getThangGiaoDich()<0)
                        return -1;
                    else
                    {
                        if(p2.getNgayGiaoDich()-p1.getNgayGiaoDich()>0)
                            return 1;
                        else if(p2.getNgayGiaoDich()-p1.getNgayGiaoDich()<0)
                            return -1;
                        else
                            return 0;
                    }

                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        AnhXa();

        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);

        ThemNamThang();
        ThemDuLieu();
        TongThuChi(BaoCaoLichSuList);
        ListChiTietBaoCao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indexListView = position;
                return false;
            }
        });
        // thao tac menu tren ListChiTietBaoCao
        registerForContextMenu(ListChiTietBaoCao);

        spinner_Thang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean isFirstItemSelected = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isFirstItemSelected) {
                    isFirstItemSelected = false;
                    return;
                }
                if(position == 0)
                {
                    check_thang = 0;
                    if(check_nam==1)
                    {
                        dataSearch_nam.clear();
                        for(BaoCaoLichSu i: BaoCaoLichSuList)
                        {

                            if(String.valueOf(i.getNamGiaoDich()).equals(spinner_Nam.getSelectedItem()))
                                dataSearch_nam.add(i);
                        }
                        SapXepDuLieu(dataSearch_nam);
                        TongThuChi(dataSearch_nam);
                        LoadDuLieu(dataSearch_nam);

                    }
                    else
                    {
                        LoadDuLieu(BaoCaoLichSuList);
                        TongThuChi(BaoCaoLichSuList);
                    }
                    //Toast.makeText(LichSuGiaoDich.this, String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                    return;
                }
                dataSearch_thang = new ArrayList<>();
                for(BaoCaoLichSu i: BaoCaoLichSuList)
                {
                    if(check_nam==1)
                    {
                        if(String.valueOf(i.getThangGiaoDich()).equals(String.valueOf(position))
                                && String.valueOf(i.getNamGiaoDich()).equals(spinner_Nam.getSelectedItem()))
                            dataSearch_thang.add(i);
                    }
                    else
                    {
                        if(String.valueOf(i.getThangGiaoDich()).equals(String.valueOf(position)) )
                            dataSearch_thang.add(i);
                    }
                }
                SapXepDuLieu(dataSearch_thang);
                TongThuChi(dataSearch_thang);
                LoadDuLieu(dataSearch_thang);

                //Toast.makeText(LichSuGiaoDich.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                check_thang = 1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_Nam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean isFirstItemSelected = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isFirstItemSelected)
                {
                    isFirstItemSelected = false;
                    return;
                }
                if(position == 0)
                {
                    check_nam = 0;
                    if(check_thang==1)
                    {
                        dataSearch_thang.clear();
                        for(BaoCaoLichSu i: BaoCaoLichSuList)
                        {
                            if(String.valueOf(i.getThangGiaoDich()).equals(spinner_Thang.getSelectedItem()) )
                                dataSearch_thang.add(i);
                        }
                        TongThuChi(dataSearch_thang);
                        SapXepDuLieu(dataSearch_thang);
                        LoadDuLieu(dataSearch_thang);

                    }
                    else
                    {
                        TongThuChi(BaoCaoLichSuList);
                        LoadDuLieu(BaoCaoLichSuList);
                    }
                    //Toast.makeText(LichSuGiaoDich.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    return;
                }
                dataSearch_nam = new ArrayList<>();
                for(BaoCaoLichSu i: BaoCaoLichSuList)
                {
                    if(check_thang==1)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(String.valueOf(i.getNamGiaoDich()).equals(String.valueOf(LocalDate.now().getYear() + 1 - position))
                                    && String.valueOf(i.getThangGiaoDich()).equals(spinner_Thang.getSelectedItem()))
                                dataSearch_nam.add(i);
                        }
                    }
                    else
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(String.valueOf(i.getNamGiaoDich()).equals(String.valueOf(String.valueOf(LocalDate.now().getYear() + 1- position))))
                                dataSearch_nam.add(i);
                        }
                    }
                }
                SapXepDuLieu(dataSearch_nam);
                TongThuChi(dataSearch_nam);
                LoadDuLieu(dataSearch_nam);

                //Toast.makeText(LichSuGiaoDich.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                check_nam = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Trang chủ
        imgTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(LichSuGiaoDich.this, TrangChu.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
            }
        });
        //Lịch sử giao dịch

        //Tạo giao dịch
        btnTaoGiaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(LichSuGiaoDich.this, NhapThuChi.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 300);
            }
        });
        //Thống kê
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(LichSuGiaoDich.this, ThongKe.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 400);
            }
        });
        //Danh mục
        imgDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(LichSuGiaoDich.this, DanhMuc.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 500);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menupopup_chitietgiaodich, menu);

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        data_search = new ArrayList<>();
        if(check_nam==1 || check_thang==1)
        {
            if(check_nam == 1)
                data_search = dataSearch_nam;
            if(check_thang == 1)
                data_search = dataSearch_thang;
        }
        else
        {
            data_search = BaoCaoLichSuList;
        }

        switch(item.getItemId())
        {
            case R.id.id_menu_sua_ChiTietGiaoDich:
                BaoCaoLichSu baoCaoLichSu = data_search.get(indexListView);
                Intent intentSua = new Intent(LichSuGiaoDich.this, Sua_ChiTietGiaoDich.class);
                Bundle b = new Bundle();
                b.putString("MaGiaoDich", baoCaoLichSu.getMaGiaoDich());
                b.putString("LoaiGiaoDich", baoCaoLichSu.getLoaiGiaoDich());
                b.putString("TenDanhMuc", baoCaoLichSu.getTenDanhMuc());
                b.putInt("NgayGiaoDich", baoCaoLichSu.getNgayGiaoDich());
                b.putInt("ThangGiaoDich", baoCaoLichSu.getThangGiaoDich());
                b.putInt("NamGiaoDich", baoCaoLichSu.getNamGiaoDich());
                b.putDouble("TienGiaoDich", baoCaoLichSu.getTienGiaoDich());
                // chuyen du lieu sang subActivity
                intentSua.putExtras(b);
                startActivityForResult(intentSua, 100);
                break;
            case R.id.id_menu_xoa_ChiTietGiaoDich:
                Dialog_xoa(data_search);

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        if(b!=null)
        {
            String MaGiaoDich = b.getString("MaGiaoDich");
            String TenDanhMuc = b.getString("TenDanhMuc");
            String LoaiGiaoDich = b.getString("LoaiGiaoDich");
            Double TienGiaoDich = b.getDouble("TienGiaoDich");
            int NgayGiaoDich = b.getInt("NgayGiaoDich");
            int ThangGiaoDich = b.getInt("ThangGiaoDich");
            int NamGiaoDich = b.getInt("NamGiaoDich");
            BaoCaoLichSu newBaoCaoLichSu = new BaoCaoLichSu(MaGiaoDich, LoaiGiaoDich, TienGiaoDich, TenDanhMuc, NgayGiaoDich, ThangGiaoDich, NamGiaoDich);

            if(resultCode == 100 && requestCode == 100)
            {
                // truong hop sua
                for(BaoCaoLichSu BCLS: BaoCaoLichSuList)
                {
                    if(BCLS.getMaGiaoDich().equals(MaGiaoDich))
                    {
                        BCLS.setNgayGiaoDich(NgayGiaoDich);
                        BCLS.setThangGiaoDich(ThangGiaoDich);
                        BCLS.setNamGiaoDich(NamGiaoDich);
                        BCLS.setTienGiaoDich(TienGiaoDich);
                        BCLS.setTenDanhMuc(TenDanhMuc);
                        break;
                    }
                }
                SapXepDuLieu(data_search);
                TongThuChi(data_search);
                LoadDuLieu(data_search);
                if(check_thang==1 || check_nam ==1 )
                {
                    if(check_nam==1 && check_thang == 0)
                    {
                        spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(NamGiaoDich)));
                        //Toast.makeText(LichSuGiaoDich.this, String.valueOf(spinner_Nam.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
                    }

                    else if(check_nam==0 && check_thang == 1)
                        spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(ThangGiaoDich)));
                    else
                    {
                        spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(NamGiaoDich)));
                        spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(ThangGiaoDich)));
                    }

                }
                else
                {
                    spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(NamGiaoDich)));
                    spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(ThangGiaoDich)));
                }

            }

        }

    }
    public void Dialog_xoa(ArrayList<BaoCaoLichSu> data_search)
    {
        // tao Dialog
        AlertDialog.Builder dlg_xoa = new AlertDialog.Builder(LichSuGiaoDich.this);
        // tieu de cho hop thoai
        dlg_xoa.setTitle("Question");
        // thong die dlg muon chuyen tai
        dlg_xoa.setMessage("Bạn có muốn xóa lịch sử này không ?");
        // icon
        dlg_xoa.setIcon(R.drawable.ic_baseline_accessibility_new_24);
        // tao hai button NO va YES
        dlg_xoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Toast.makeText(LichSuGiaoDich.this, String.valueOf(data_search.size()) + " " + String.valueOf(check_thang) + " " + String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                for(int i = BaoCaoLichSuList.size()-1; i>=0; i--)
                {
                    if(data_search.get(indexListView).getMaGiaoDich().equals(BaoCaoLichSuList.get(i).getMaGiaoDich()))
                    {
                        BaoCaoLichSuList.remove(i);
                        break;
                    }
                }
                if(check_nam == 1 || check_thang == 1)
                    data_search.remove(indexListView);
                LoadDuLieu(data_search);
                TongThuChi(data_search);
            }
        });
        dlg_xoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dlg_xoa.create().show();// hien thi dialog
    }

}
