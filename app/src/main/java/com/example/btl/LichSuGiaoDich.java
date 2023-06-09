package com.example.btl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class LichSuGiaoDich extends AppCompatActivity {





    private Spinner  spinner_Nam;
    private Spinner  spinner_Thang;
    private TextView textView_TongGiaoDich;
    private TextView textView_NgayHomNay;
    private TextView textView_TongThuChi;
    private TextView textView_Thu;
    private TextView textView_Chi;
    private ArrayList<Class_GiaoDich> BaoCaoLichSuList;
    private ListView ListChiTietBaoCao;
    private AdapterChiTietGiaoDichTheoNgay Adapter;
    private ArrayAdapter arrayAdapter_thang;
    private ArrayAdapter arrayAdapter_nam;
    private int indexListView=-1;
    private int check_thang = 0;
    private int check_nam = 0;
    private ArrayList<Class_GiaoDich> dataSearch_thang;
    private ArrayList<Class_GiaoDich> dataSearch_nam;
    private ArrayList<Class_GiaoDich> data_search;
    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;
    private DBQuanLyChiTieu DB;

    public void AnhXa()
    {

        spinner_Thang = findViewById(R.id.id_Spinner_Thang);
        spinner_Nam = findViewById(R.id.id_Spinner_Nam);
        ListChiTietBaoCao = findViewById(R.id.id_ListView_BaoCao);
        textView_TongThuChi = findViewById(R.id.id_textView_TongThuChi);
        textView_Thu = findViewById(R.id.id_textview_Thu);
        textView_Chi= findViewById(R.id.id_textview_Chi);

        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);
    }
    public void TongThuChi(ArrayList<Class_GiaoDich> DuLieu)
    {
        // Tong thu chi
        double thu = 0, chi = 0, s = 0;
        for(int i=0; i<DuLieu.size(); i++)
        {
            if(DuLieu.get(i).getLoaiGiaoDich().equals("Chi"))
                chi += DuLieu.get(i).getSoTienNhap();
            else
                thu += DuLieu.get(i).getSoTienNhap();
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
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ThemDuLieu() throws ParseException {
        // đưa dữ liệu chi tiết báo cáo lên listView
        BaoCaoLichSuList = new ArrayList<>();
        DB = new DBQuanLyChiTieu(this, "DatabaseQuanLyChiTieu", null, 99);
        //Toast.makeText(LichSuGiaoDich.this, String.valueOf(LocalDate.now()), Toast.LENGTH_SHORT).show();

        String nam = String.valueOf(LocalDateTime.now().getYear());
        String thang = String.valueOf(LocalDateTime.now().getMonthValue());
        String ngay = String.valueOf(LocalDateTime.now().getDayOfMonth());
        String gio = String.valueOf(LocalDateTime.now().getHour());
        String phut = String.valueOf(LocalDateTime.now().getMinute());
        String giay = String.valueOf(LocalDateTime.now().getSecond());
        String NgayGiaoDich = nam +"-"+ thang +"-"+ ngay +" "+ gio +":"+ phut +":"+ giay;


        //DB.deleteAll();
        BaoCaoLichSuList = DB.getAllGiaoDich();
        SapXepDuLieu(BaoCaoLichSuList);
        TongThuChi(BaoCaoLichSuList);
        LoadDuLieu(BaoCaoLichSuList);

    }

    public void LoadDuLieu(ArrayList<Class_GiaoDich> DuLieu)
    {
        Adapter = new AdapterChiTietGiaoDichTheoNgay(DuLieu, LichSuGiaoDich.this);
        ListChiTietBaoCao.setAdapter(Adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SapXepDuLieu(ArrayList<Class_GiaoDich> BaoCaoLichSuList)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            BaoCaoLichSuList.sort((p1, p2)->{
                LocalDateTime d1, d2;
                Instant instant1, instant2 ;
                instant1 = null;
                instant2= null;

                instant1 = p1 .getNgayGiaoDich().toInstant();
                d1 = instant1.atZone(ZoneId.systemDefault()).toLocalDateTime();
                instant2 = p2 .getNgayGiaoDich().toInstant();
                d2 = instant2.atZone(ZoneId.systemDefault()).toLocalDateTime();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if(d2.getYear()-d1.getYear()>0)
                        return 1;
                    else if(d2.getYear()-d1.getYear() <0)
                        return -1;
                    else
                    {
                        if(d2.getMonthValue()-d1.getMonthValue()>0)
                            return 1;
                        else if(d2.getMonthValue()-d1.getMonthValue()<0)
                            return -1;
                        else
                        {
                            if(d2.getDayOfMonth()-d1.getDayOfMonth()>0)
                                return 1;
                            else if(d2.getDayOfMonth()-d1.getDayOfMonth()<0)
                                return -1;
                            else
                            {
                                if(d2.getHour()-d1.getHour()>0)
                                    return 1;
                                else if(d2.getHour()-d1.getHour()<0)
                                    return -1;
                                else
                                {
                                    if(d2.getMinute()-d1.getMinute()>0)
                                        return 1;
                                    else if(d2.getMinute()-d1.getMinute()<0)
                                        return -1;
                                    else
                                    {
                                        if(d2.getSecond()-d1.getSecond()>0)
                                            return 1;
                                        else if(d2.getSecond()-d1.getSecond()<0)
                                            return -1;
                                        else
                                            return 0;
                                    }
                                }

                            }
                        }

                    }
                }
                return 0;
            });
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        AnhXa();



        ThemNamThang();
        try {
            ThemDuLieu();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TongThuChi(BaoCaoLichSuList);
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

                dataSearch_thang = new ArrayList<>();
                if(position == 0)
                {
                    check_thang = 0;
                    if(check_nam==1)
                    {
                        dataSearch_nam.clear();
                        for(Class_GiaoDich i: BaoCaoLichSuList)
                        {
                            LocalDate localDate = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                localDate = i.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if(localDate.getYear() == Integer.parseInt((String) spinner_Nam.getSelectedItem()))
                                    dataSearch_nam.add(i);
                            }
                        }
                        SapXepDuLieu(dataSearch_nam);
                        TongThuChi(dataSearch_nam);
                        LoadDuLieu(dataSearch_nam);

                    }
                    else
                    {

                        SapXepDuLieu(BaoCaoLichSuList);
                        TongThuChi(BaoCaoLichSuList);
                        LoadDuLieu(BaoCaoLichSuList);
                    }
                    //Toast.makeText(LichSuGiaoDich.this, String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                    return;
                }

                for(Class_GiaoDich i: BaoCaoLichSuList)
                {
                    LocalDate localDate = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        localDate = i.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                    if(check_nam==1)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(localDate.getMonthValue() == position
                                    && localDate.getYear() == Integer.parseInt((String) spinner_Nam.getSelectedItem()))
                                dataSearch_thang.add(i);
                        }
                    }
                    else
                    {
                        //Toast.makeText(LichSuGiaoDich.this, String.valueOf(check_thang), Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(localDate.getMonthValue() == position)
                                dataSearch_thang.add(i);
                        }
                    }
                }
                SapXepDuLieu(dataSearch_thang);
                TongThuChi(dataSearch_thang);
                LoadDuLieu(dataSearch_thang);
                check_thang = 1;
                //Toast.makeText(LichSuGiaoDich.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

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
                dataSearch_nam = new ArrayList<>();
                if(position == 0)
                {
                    check_nam = 0;
                    if(check_thang==1)
                    {
                        dataSearch_thang.clear();
                        for(Class_GiaoDich i: BaoCaoLichSuList)
                        {
                            LocalDate localDate = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                localDate = i.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if(localDate.getMonthValue() == Integer.parseInt((String) spinner_Thang.getSelectedItem()))
                                    dataSearch_thang.add(i);
                            }
                        }
                        TongThuChi(dataSearch_thang);
                        SapXepDuLieu(dataSearch_thang);
                        LoadDuLieu(dataSearch_thang);

                    }
                    else
                    {
                        TongThuChi(BaoCaoLichSuList);
                        SapXepDuLieu(BaoCaoLichSuList);
                        LoadDuLieu(BaoCaoLichSuList);
                    }
                    //Toast.makeText(LichSuGiaoDich.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    return;
                }


                for(Class_GiaoDich i: BaoCaoLichSuList)
                {
                    LocalDate localDate = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        localDate = i.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                    if(check_thang==1)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(localDate.getYear()== (LocalDate.now().getYear() + 1 - position)
                                    && localDate.getMonthValue()== Integer.parseInt((String) spinner_Thang.getSelectedItem()))
                                dataSearch_nam.add(i);
                        }
                    }
                    else
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if(localDate.getYear() == (LocalDate.now().getYear() + 1- position))
                                dataSearch_nam.add(i);

                        }
                    }
                }
                SapXepDuLieu(dataSearch_nam);
                TongThuChi(dataSearch_nam);
                LoadDuLieu(dataSearch_nam);
                check_nam = 1;
                //Toast.makeText(LichSuGiaoDich.this, String.valueOf(postion), Toast.LENGTH_SHORT).show();

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
                finish();
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
                finish();
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
                finish();
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
                finish();
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
                Class_GiaoDich giaodich = data_search.get(indexListView);
                Intent intentSua = new Intent(LichSuGiaoDich.this, Sua_ChiTietGiaoDich.class);
                Bundle b = new Bundle();
                b.putString("MaGiaoDich", giaodich.getMaGiaoDich());
                b.putString("LoaiGiaoDich", giaodich.getLoaiGiaoDich());
                b.putString("TenDanhMuc", giaodich.getTenDanhMuc());
                b.putString("GhiChu", giaodich.getGhiChu());
                b.putString("NgayGiaoDich", sdf.format(giaodich.getNgayGiaoDich()));
                b.putInt("TienGiaoDich", giaodich.getSoTienNhap());
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        if(b!=null)
        {
            String MaGiaoDich = b.getString("MaGiaoDich");
            String TenDanhMuc = b.getString("TenDanhMuc");
            String GhiChu = b.getString("GhiChu");
            String LoaiGiaoDich = b.getString("LoaiGiaoDich");
            int TienGiaoDich = b.getInt("TienGiaoDich");
            Date date1 = null;
            try {
                date1 = sdf.parse(b.getString("NgayGiaoDich"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Instant instant = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant = date1.toInstant();
            }
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();


            Class_GiaoDich giaoDich = null;
            giaoDich = new Class_GiaoDich (MaGiaoDich, LoaiGiaoDich, date1, GhiChu, TienGiaoDich, TenDanhMuc);



            if(resultCode == 100 && requestCode == 100)
            {
                // truong hop sua
                DB.updateLichSu(MaGiaoDich, giaoDich);
                for(Class_GiaoDich GD: BaoCaoLichSuList)
                {
                    if(GD.getMaGiaoDich().equals(MaGiaoDich))
                    {

                        GD.setNgayGiaoDich(date1);
                        GD.setSoTienNhap(TienGiaoDich);
                        GD.setTenDanhMuc(TenDanhMuc);
                        GD.setGhiChu(GhiChu);
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
                        spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(localDateTime.getYear())));
                        //Toast.makeText(LichSuGiaoDich.this, String.valueOf(1)+" "+String.valueOf(check_thang)+" "+String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                    }

                    else if(check_nam==0 && check_thang == 1)
                    {
                        spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(localDateTime.getMonthValue())));
                        //Toast.makeText(LichSuGiaoDich.this,String.valueOf(2)+" "+ String.valueOf(check_thang)+" "+String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        //Toast.makeText(LichSuGiaoDich.this, String.valueOf(3)+" "+String.valueOf(check_thang)+" "+String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                        spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(localDateTime.getYear())));
                        spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(localDateTime.getMonthValue())));
                    }

                }
                else
                {
                    //Toast.makeText(LichSuGiaoDich.this, String.valueOf(4)+" "+String.valueOf(check_thang)+" "+String.valueOf(check_nam), Toast.LENGTH_SHORT).show();
                    spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(localDateTime.getYear())));
                    spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(localDateTime.getMonthValue())));
                }

            }

        }

    }
    public void Dialog_xoa(ArrayList<Class_GiaoDich> data_search)
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

                        DB.deleteLichSu(BaoCaoLichSuList.get(i).getMaGiaoDich());
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
