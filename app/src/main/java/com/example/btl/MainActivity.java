package com.example.btl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Khai báo đối tượng lưu trữ danh sách các contact
    private ArrayList<DanhMuc_Class> ContactList;
    private Adapter ListAdapter;
    private ListView lstContact;
    private FloatingActionButton btnAdd;
    private int SelectedItemId;
    private ImageView trangchu, lichsugiaodich, taogiaodich, thongke, danhmuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        //Ánh xạ
        lstContact = findViewById(R.id.ID_LISTVIEW);
        btnAdd = findViewById(R.id.buttonAdd);
        trangchu = findViewById(R.id.TrangChu);
        lichsugiaodich = findViewById(R.id.LichSuGiaoDich);
        taogiaodich = findViewById(R.id.TaoGiaoDich);
        thongke = findViewById(R.id.ThongKe);
        danhmuc = findViewById(R.id.DanhMuc);

        //Button Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(MainActivity.this, DanhMuc_Sub.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
            }
        });

        //Thiết lập dữ liệu mẫu
        ContactList = new ArrayList<>();
        ContactList.add(new DanhMuc_Class("DM01", "Ăn uống", "Chi"));
        ContactList.add(new DanhMuc_Class("DM02", "Lương", "Thu"));
        ContactList.add(new DanhMuc_Class("DM03", "Giải trí", "Chi"));


        ListAdapter = new Adapter(ContactList, this);
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
    }
    //Hết onCreate
    //Trả về từ SubActivity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String id = b.getString("ID");
        String name = b.getString("Name");
        String loai = b.getString("Loai");
        DanhMuc_Class newdanhmuc = new DanhMuc_Class(id, name, loai);
        if(requestCode == 100 && resultCode == 150)
        {
            //Truong hop them
            ContactList.add(newdanhmuc);
            ListAdapter = new Adapter(ContactList, this);
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
        DanhMuc_Class danhMuc_class = ContactList.get(SelectedItemId);
        switch(item.getItemId())
        {
            case R.id.mnuSuaDM:
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(MainActivity.this, DanhMuc_Sub.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                DanhMuc_Class c = ContactList.get(SelectedItemId);
                Bundle b = new Bundle();
                b.putString("Id", c.getLoaiDanhMuc());
                b.putString("Name", c.getTenDanhMuc());
                b.putString("Loai", c.getLoaiDanhMuc());
                intent.putExtras(b);
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
                break;
            case R.id.mnuXoaDM:
                //Tạo Dialog
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Thông Báo");
//                builder.setMessage("Bạn muốn xóa không ?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        db.deleteContact(con.getId());
//                        ContactList = db.getAllContact();
//                        //ContactList.remove(SelectedItemId);
//                        ListAdapter = new Adapter(ContactList, MainActivity.this);
//                        lstContact.setAdapter(ListAdapter);
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();

                break;
        }
        return super.onContextItemSelected(item);
    }
}