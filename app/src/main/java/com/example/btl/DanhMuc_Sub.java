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

import java.util.ArrayList;

public class DanhMuc_Sub extends AppCompatActivity {

    private EditText etTenDM, etMaDM;
    private Spinner spinnerLoai;
    private Button btnThem, btnHuy;
    private String idfromDanhMuc;

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
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putString("ID", idfromDanhMuc);
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
    }

    public static String generateCategoryId(int id) {
        String categoryId = String.format("DM%02d", id);
        return categoryId;
    }
}