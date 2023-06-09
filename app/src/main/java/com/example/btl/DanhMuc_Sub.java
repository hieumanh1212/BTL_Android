package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DanhMuc_Sub extends AppCompatActivity {

    private EditText etTenDM, etMaDM;
    private Spinner spinnerLoai;
    private Button btnThem, btnHuy;
    private String idfromDanhMuc;

    private static List<Integer> generatedNumbers;
    private ArrayList<Class_DanhMuc> getAllDanhMuc;
    private DBQuanLyChiTieu db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_sub);

        //Ánh xạ
        etTenDM = findViewById(R.id.ID_TenDanhMuc);
        spinnerLoai = findViewById(R.id.spinnerDanhMuc);
        btnThem = findViewById(R.id.buttonThem);
        btnHuy = findViewById(R.id.buttonHuy);

        //Gắn giá trị cho Spinner
        ArrayList<String> arrayLoai = new ArrayList<String>();
        arrayLoai.add("Chi");
        arrayLoai.add("Thu");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayLoai);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoai.setAdapter(arrayAdapter);

        //Lấy intent từ MainActivity chuyển sang
        Intent intent = getIntent();
        //Lấy bundle
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            idfromDanhMuc = bundle.getString("Id");
            String name = bundle.getString("Name");
            String loai = bundle.getString("Loai");
            etTenDM.setText(name);
            if(loai.contains("Thu"))
            {
                spinnerLoai.setSelection(1);
            }
            else
            {
                spinnerLoai.setSelection(0);
            }


            btnThem.setText("Sửa");
        }


        //Button Ok
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnThem.getText().toString().toLowerCase().contains("THÊM".toLowerCase()))
                {
                    //Lấy dữ liệu và gửi về cho MainActivity
                    String name = etTenDM.getText().toString();
                    String loai = spinnerLoai.getSelectedItem().toString();
                    String id = null;
                    try {
                        id = SinhMaDanhMuc();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putString("ID", id);
                    b.putString("Name", name);
                    b.putString("Loai", loai);
                    intent.putExtras(b);
                    setResult(150, intent);
                    finish();
                }
                else
                {
                    //Lấy dữ liệu và gửi về cho MainActivity
                    String name = etTenDM.getText().toString();
                    String loai = spinnerLoai.getSelectedItem().toString();
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putString("ID", idfromDanhMuc);
                    b.putString("Name", name);
                    b.putString("Loai", loai);
                    intent.putExtras(b);
                    setResult(150, intent);
                    finish();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(DanhMuc_Sub.this, DanhMuc.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 170);
                finish();
            }
        });

        //Kết nối database
        db = new DBQuanLyChiTieu(this, "DatabaseQuanLyChiTieu", null, 99);

        //Lấy tất cả bản ghi của GiaoDich
        getAllDanhMuc = db.getAllDanhMuc();
    }
    //Hết onCreate

    public String tachMa(String s){
        String res = s.substring(2,s.length());
        return res;
    }
    //Định dang DM01 DM99
    public static String generateCategoryId(int i) {
        String categoryId = String.format("DM%03d", i);
        return categoryId;
    }

    //Sinh số ngẫu nhiên từ 0-99 mà số sau không trùng số trước
    public String SinhMaDanhMuc() throws ParseException {
        getAllDanhMuc = db.getAllDanhMuc();
        if(getAllDanhMuc.size()==0){
            return "DM001";
        }
        else {
            String getLast = getAllDanhMuc.get(getAllDanhMuc.size()-1).getMaDanhMuc();
            getLast=tachMa(getLast);
            int idDanhMuc = Integer.parseInt(getLast)+1;
            return generateCategoryId(idDanhMuc);
        }
    }

}