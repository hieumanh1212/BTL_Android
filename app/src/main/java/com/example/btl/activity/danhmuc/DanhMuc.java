package com.example.btl.activity.danhmuc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btl.database.DBQuanLyChiTieu;
import com.example.btl.R;
import com.example.btl.activity.lichsugiaodich.LichSuGiaoDich;
import com.example.btl.activity.nhapthuchi.NhapThuChi;
import com.example.btl.activity.thongke.ThongKe;
import com.example.btl.activity.trangchu.TrangChu;
import com.example.btl.adapter.Adapter_DanhMuc;
import com.example.btl.models.Class_DanhMuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhMuc extends AppCompatActivity {

    //Khai báo đối tượng lưu trữ danh sách các contact
    private ArrayList<Class_DanhMuc> ContactList;
    private Adapter_DanhMuc ListAdapter;
    private ListView lstContact;
    private FloatingActionButton btnAdd;
    private int SelectedItemId;
    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;
    private EditText edTimkiem;

    private DBQuanLyChiTieu db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        //Ánh xạ
        lstContact = findViewById(R.id.ID_LISTVIEW);
        btnAdd = findViewById(R.id.buttonAdd);
        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);
        edTimkiem = findViewById(R.id.edTimKiem);

        //Button Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc.this, DanhMuc_Sub.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
            }
        });

        ContactList = new ArrayList<>();

        //Tạo mới CSDL
        db = new DBQuanLyChiTieu(this, "DatabaseQuanLyChiTieu", null, 99);
//        db.close();
//        this.deleteDatabase("DanhMucDB");
        //Thêm dữ liệu lần đầu vào db
//        db.addDanhMuc(new Class_DanhMuc("DM01", "Ăn uống", "Chi"));
//        db.addDanhMuc(new Class_DanhMuc("DM02", "Giải trí", "Chi"));
//        db.addDanhMuc(new Class_DanhMuc("DM03", "Lương", "Thu"));

        ContactList = db.getAllDanhMuc();
        //Thiết lập dữ liệu mẫu

//        ContactList.add(new Class_DanhMuc("DM01", "Ăn uống", "Thu"));
//        ContactList.add(new Class_DanhMuc("DM02", "Lương", "Thu"));
//        ContactList.add(new Class_DanhMuc("DM03", "Giải trí", "Chi"));
//

        ListAdapter = new Adapter_DanhMuc(ContactList, this);
        lstContact.setAdapter(ListAdapter);


        //Gắn MenuPopup
        registerForContextMenu(lstContact);

        //Long click
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedItemId = position;
                return false;
            }
        });

        //Tìm kiếm
        edTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                ListAdapter.getFilter().filter(charSequence.toString());
                ListAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Trang chủ
        imgTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc.this, TrangChu.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
                finish();
            }
        });
        //Lịch sử giao dịch
        imgLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc.this, LichSuGiaoDich.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
                finish();
            }
        });

        //Tạo giao dịch
        btnTaoGiaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc.this, NhapThuChi.class);
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
                Intent intent = new Intent(DanhMuc.this, ThongKe.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 400);
                finish();
            }
        });
        //Danh mục



    }


    //Hết onCreate
    //Trả về từ SubActivity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String id = b.getString("ID");
        String name = b.getString("Name");
        String loai = b.getString("Loai");
        Class_DanhMuc newdanhmuc = new Class_DanhMuc(id, name, loai);
        if(requestCode == 100 && resultCode == 150)
        {
            //Truong hop them
//            ContactList.add(newdanhmuc);
//            ListAdapter = new Adapter(ContactList, this);
//            lstContact.setAdapter(ListAdapter);
            //Truong hop them db
            db.addDanhMuc(newdanhmuc);
            ContactList = db.getAllDanhMuc();
            ListAdapter = new Adapter_DanhMuc(ContactList, this);
            lstContact.setAdapter(ListAdapter);

            //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        }
        else if(requestCode == 200 && resultCode == 150)
        {
            //Truong hop sua
//            for(Class_DanhMuc c: ContactList)
//            {
//                if(c.getMaDanhMuc().contains(id))
//                {
//                    Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
//                    c.setTenDanhMuc(name);
//                    c.setLoaiDanhMuc(loai);
//                }
//            }
            //Truong hop sua voi Database
            for(Class_DanhMuc c: ContactList)
            {
                if(c.getMaDanhMuc().contains(id))
                {
                    Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
                    c.setTenDanhMuc(name);
                    c.setLoaiDanhMuc(loai);
                    //db.updateDanhMuc(id, c);
                }
            }
            ListAdapter = new Adapter_DanhMuc(ContactList, this);
            lstContact.setAdapter(ListAdapter);

        }

    }

    //Tạo MenuPupup
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menupopup_danhmuc, menu);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Class_DanhMuc danhMuc_class = ContactList.get(SelectedItemId);
        switch(item.getItemId())
        {
            case R.id.mnuSuaDM:
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc.this, DanhMuc_Sub.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                Class_DanhMuc c = ContactList.get(SelectedItemId);
                Bundle b = new Bundle();
                b.putString("Id", c.getMaDanhMuc());
                b.putString("Name", c.getTenDanhMuc());
                b.putString("Loai", c.getLoaiDanhMuc());
                intent.putExtras(b);
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
                break;
            case R.id.mnuXoaDM:
                //Tạo Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        ContactList.remove(SelectedItemId);
//                        ListAdapter = new Adapter(ContactList, DanhMuc.this);
//                        lstContact.setAdapter(ListAdapter);
                    //    Toast.makeText(DanhMuc.this, danhMuc_class.getMaDanhMuc(), Toast.LENGTH_SHORT).show();
                        //Xóa ở database
                        db.deleteDanhMuc(danhMuc_class.getMaDanhMuc());
                        ContactList = db.getAllDanhMuc();
                        ListAdapter = new Adapter_DanhMuc(ContactList, DanhMuc.this);
                        lstContact.setAdapter(ListAdapter);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

                break;
        }
        return super.onContextItemSelected(item);
    }
}