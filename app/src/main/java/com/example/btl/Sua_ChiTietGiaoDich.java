package com.example.btl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Sua_ChiTietGiaoDich extends AppCompatActivity {

    private Spinner spinner_Ngay;
    private Spinner spinner_Thang;
    private Spinner spinner_Nam;
    private TextView textView_TienSua;
    private EditText editText_TienSua;
    private EditText editText_TenSua;
    private Button button_ChinhSua;
    private String MaGiaoDich;
    private String LoaiGiaoDich;
    private ImageButton imageButton_Back;
    private ArrayAdapter arrayAdapter_Ngay;
    private ArrayList<String> ngay;

    public void AnhXa()
    {
        spinner_Ngay = findViewById(R.id.id_spinner_Ngay);
        spinner_Thang = findViewById(R.id.id_spinner_Thang);
        spinner_Nam = findViewById(R.id.id_spinner_Nam);
        textView_TienSua = findViewById(R.id.id_textView_TienSua);
        editText_TienSua = findViewById(R.id.id_editText_TienSua);
        editText_TenSua = findViewById(R.id.id_editText_TenSua);
        button_ChinhSua = findViewById(R.id.id_button_ChinhSua);
        imageButton_Back = findViewById(R.id.id_imageButton_Back);

    }
    public void ThemNgayNamThang(Intent intent, Bundle bundle)
    {
        // năm
        ArrayList<String> nam = new ArrayList<>();
        nam.add("---");
        nam.add(String.valueOf(java.time.LocalDate.now().getYear()));
        nam.add(String.valueOf(java.time.LocalDate.now().getYear()-1));
        nam.add(String.valueOf(java.time.LocalDate.now().getYear()-2));
        ArrayAdapter arrayAdapter_nam = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nam);
        // dãn dàng
        arrayAdapter_nam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Nam.setAdapter(arrayAdapter_nam);
        spinner_Nam.setSelection(arrayAdapter_nam.getPosition(String.valueOf(bundle.getInt("NamGiaoDich"))));

        // tháng
        ArrayList<String> thang = new ArrayList<>();
        thang.add("---");
        for(int i=1; i<=12; i++)
            thang.add(String.valueOf(i));
        ArrayAdapter arrayAdapter_thang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, thang);
        arrayAdapter_thang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Thang.setAdapter(arrayAdapter_thang);
        spinner_Thang.setSelection(arrayAdapter_thang.getPosition(String.valueOf(bundle.getInt("ThangGiaoDich"))));


        // ngày
        ngay = new ArrayList<>();
        ngay.add("---");
        for(int i=1; i<=31; i++)
            ngay.add(String.valueOf(i));
        arrayAdapter_Ngay = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ngay);
        arrayAdapter_Ngay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Ngay.setAdapter(arrayAdapter_Ngay);
        spinner_Ngay.setSelection(arrayAdapter_Ngay.getPosition(String.valueOf(bundle.getInt("NgayGiaoDich"))));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_chi_tiet_giao_dich);
        AnhXa();

        // lay Intent tu Activity sang
        Intent intent = getIntent();
        // lay Bundle
        Bundle bundle = intent.getExtras();
        ThemNgayNamThang(intent, bundle);

        if(bundle != null)
        {
            MaGiaoDich = bundle.getString("MaGiaoDich");
            Toast.makeText(this, MaGiaoDich, Toast.LENGTH_SHORT).show();
            LoaiGiaoDich = bundle.getString("LoaiGiaoDich");
            String loai = textView_TienSua.getText() + String.valueOf(bundle.getString("LoaiGiaoDich"));
            textView_TienSua.setText(loai);
            editText_TienSua.setText(String.valueOf(bundle.getDouble("TienGiaoDich")));
            editText_TenSua.setText(String.valueOf(bundle.getString("TenDanhMuc")));
            loai = button_ChinhSua.getText() + String.valueOf(bundle.getString("LoaiGiaoDich"));
            button_ChinhSua.setText(loai);
        }
        spinner_Thang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean isFirstItemSelected = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isFirstItemSelected) {
                    isFirstItemSelected = false;
                    return;
                }

                if(position == 0)
                    return;

                String ngayhientai = (String) spinner_Ngay.getSelectedItem();
                //Toast.makeText(Sua_ChiTietGiaoDich.this, "hello", Toast.LENGTH_SHORT).show();
                String namhientai = (String) spinner_Nam.getSelectedItem();
                if(namhientai.equals("---"))
                    return;
                ngay = new ArrayList<>();
                switch (position)
                {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        ngay.add("---");
                        for(int i=1; i<=31; i++)
                            ngay.add(String.valueOf(i));
                        break;
                    case 2:
                        if(Integer.parseInt(namhientai)%4==0
                                && Integer.parseInt(namhientai)
                                %100==0 && Integer.parseInt(namhientai)%400==0)
                        {
                            ngay.add("---");
                            for(int i=1; i<=28; i++)
                                ngay.add(String.valueOf(i));
                            break;
                        }
                        else{
                            ngay.add("---");
                            for(int i=1; i<=29; i++)
                                ngay.add(String.valueOf(i));
                            break;
                        }

                    case 4:
                    case 6:
                    case 9:
                    case 11:

                        ngay.add("---");
                        for(int i=1; i<=30; i++)
                            ngay.add(String.valueOf(i));
                        break;

                }
                ArrayAdapter arrayAdapter_Ngay = new ArrayAdapter(Sua_ChiTietGiaoDich.this, android.R.layout.simple_spinner_item, ngay);
                arrayAdapter_Ngay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Ngay.setAdapter(arrayAdapter_Ngay);
                spinner_Ngay.setSelection(arrayAdapter_Ngay.getPosition(ngayhientai));

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_Nam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean isFirstItemSelected = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isFirstItemSelected) {
                    isFirstItemSelected = false;
                    return;
                }

                if(position == 0)
                {
                    return;
                }
                String ngayhientai = (String) spinner_Ngay.getSelectedItem();
                String nam = (String) spinner_Nam.getSelectedItem();
                String thanghientai = (String) spinner_Thang.getSelectedItem();
                ngay = new ArrayList<>();
                if(thanghientai.equals("---"))
                {
                    return;
                }
                if(Integer.parseInt(nam)%4==0
                        && Integer.parseInt(nam)
                        %100==0 && Integer.parseInt(nam)%400==0
                        && Integer.parseInt(thanghientai)==2)
                {
                    ngay.add("---");
                    for(int i=1; i<=28; i++)
                        ngay.add(String.valueOf(i));
                }
                else
                {

                    if(thanghientai.equals("1") ||
                            thanghientai.equals("3") ||
                            thanghientai.equals("5") ||
                            thanghientai.equals("7") ||
                            thanghientai.equals("8") ||
                            thanghientai.equals("10") ||
                            thanghientai.equals("12"))
                    {
                        ngay.add("---");
                        for(int i=1; i<=31; i++)
                            ngay.add(String.valueOf(i));
                    }
                    if(thanghientai.equals("4") ||
                            thanghientai.equals("6") ||
                            thanghientai.equals("9") ||
                            thanghientai.equals("11"))
                    {
                        ngay.add("---");
                        for(int i=1; i<=30; i++)
                            ngay.add(String.valueOf(i));
                    }
                    if(thanghientai.equals("2"))
                    {
                        ngay.add("---");
                        for(int i=1; i<=29; i++)
                            ngay.add(String.valueOf(i));
                    }

                }

                arrayAdapter_Ngay = new ArrayAdapter(Sua_ChiTietGiaoDich.this, android.R.layout.simple_spinner_item, ngay);
                arrayAdapter_Ngay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Ngay.setAdapter(arrayAdapter_Ngay);
                spinner_Ngay.setSelection(arrayAdapter_Ngay.getPosition(ngayhientai));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        imageButton_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(400, intent);
                finish();
            }
        });
        button_ChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ngay = spinner_Ngay.getSelectedItem().toString();
                String thang = spinner_Thang.getSelectedItem().toString();
                String nam = spinner_Nam.getSelectedItem().toString();
                String tendanhmuc = editText_TenSua.getText().toString();
                Double tiengiaodich = Double.parseDouble(editText_TienSua.getText().toString());


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Sua_ChiTietGiaoDich.this);
                alertDialog.setTitle("Thông báo!");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                if(tiengiaodich<1000)
                {
                    alertDialog.setMessage("Số tiền bạn nhập không được nhỏ hơn 1000!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText_TienSua.requestFocus();
                        }
                    });
                    alertDialog.create().show();

                }
                else if(tendanhmuc.equals(""))
                {
                    alertDialog.setMessage("Bạn không được để trống tên giao dịch!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText_TenSua.requestFocus();
                        }
                    });
                    alertDialog.create().show();
                }
                else if(ngay.equals("---"))
                {
                    alertDialog.setMessage("Bạn chưa chọn ngày!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            spinner_Ngay.requestFocus();
                        }
                    });
                    alertDialog.create().show();
                }
                else if(thang.equals("---"))
                {
                    alertDialog.setMessage("Bạn chưa chọn tháng!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            spinner_Thang.requestFocus();
                        }
                    });
                    alertDialog.create().show();
                }
                else if(nam.equals("---"))
                {
                    alertDialog.setMessage("Bạn chưa chọn năm!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            spinner_Nam.requestFocus();
                        }
                    });
                    alertDialog.create().show();
                }
                else
                {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("NgayGiaoDich", Integer.valueOf(ngay));
                    bundle.putInt("ThangGiaoDich", Integer.valueOf(thang));
                    bundle.putInt("NamGiaoDich", Integer.valueOf(nam));
                    bundle.putString("TenDanhMuc", tendanhmuc);
                    bundle.putString("MaGiaoDich", MaGiaoDich);
                    bundle.putString("LoaiGiaoDich", LoaiGiaoDich);
                    bundle.putDouble("TienGiaoDich", tiengiaodich);

                    intent.putExtras(bundle);
                    setResult(100, intent);
                    alertDialog.setMessage("Sửa thành công !");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alertDialog.create().show();

                }



            }
        });
    }


}
