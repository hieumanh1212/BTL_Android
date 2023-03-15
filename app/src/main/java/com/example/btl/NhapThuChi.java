package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NhapThuChi extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TextView tvNgay;
    private TextView tvGhiChu;
    private TextView tvDanhMuc;
    private Button btnTienThu;
    private Button btnTienChi;
    private EditText etGhiChu;
    private Spinner spnDanhMuc;
    private Button btnNhapKhoan;
    private TextView tvTien;
    private EditText etTien;
    private TextView tvDong;
    private ArrayList<GiaoDich> arrayGiaoDich = new ArrayList<>();
    private String selectDanhMuc;
    public ArrayAdapter adtDanhMuc;
    public Date datePick;
    int dayPick, monthPick, yearPick;
    private static int checkThuChi = 1; // Nếu =1 thì là nhập tiền thu, nếu =2 thì là nhập tiền chi
    private int checkClickDate=0;

    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;

    // Hàm trả về theo ngày chọn
    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        return makeDateString(day, month, year);
    }

    // Set thứ ngày tháng trong button
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                checkClickDate=1;
                month += 1;
                dayPick = day;
                monthPick = month;
                yearPick = year;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        //datePickerDialog.show();
    }

    // Trả về định dạng thứ ngày tháng
    private String makeDateString(int day, int month, int year) {
        return getFormatMonth(month) + " - " + day + " - " + year;
    }

    // Hiển thị tháng trên màn hình
    private String getFormatMonth(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUST";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }

    // hiển thị ngày tháng năm
    public void openDatePicker(View view) {
        Toast.makeText(this, "22", Toast.LENGTH_SHORT).show();
        datePickerDialog.show();
    }

    // đổi chữ mỗi khi bấm sang bút khác
    public void changeText(TextView tv1, EditText editText, String s) {
        tv1.setText(s);
        editText.setHint(s);
    }
    // thông báo alert
    public void thongBao(String s) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(s);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    // Hàm kiểm tra xem đúng hợp lệ chưa
    public boolean checkError(){
        String tien = etTien.getText().toString();
        if(tien.isEmpty()==true){
            thongBao("Không được để mục tiền trống");
            etTien.setFocusable(true);
            return true;
        }
        if(Integer.parseInt(tien)<1000){
            thongBao("Không được nhập số tiền < 1000");
            etTien.setFocusable(true);
            return true;
        }
        return false;
    }
    // reset lại sau khi add thành công
    public void resetText(){
        etGhiChu.setText("");
        etTien.setText("");
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thu_chi);
        initDatePicker();

        // ánh xạ
        dateButton = findViewById(R.id.datePickerButton);
        tvNgay = findViewById(R.id.tvNgay);
        tvGhiChu = findViewById(R.id.tvGhiChu);
        tvDanhMuc = findViewById(R.id.tvDanhMuc);
        btnTienThu = findViewById(R.id.btnTienThu);
        btnTienChi = findViewById(R.id.btnTienChi);
        etGhiChu = findViewById(R.id.txtGhiChu);
        spnDanhMuc = findViewById(R.id.spnDanhMuc);
        btnNhapKhoan = findViewById(R.id.btnNhapKhoan);
        tvTien = findViewById(R.id.tvTien);
        etTien = findViewById(R.id.txtTien);
        tvDong = findViewById(R.id.tvD);

        //Ánh xạ

        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);

        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            dayPick = now.getDayOfMonth();
            monthPick = now.getMonthValue();
            yearPick = now.getYear();
            dateButton.setText(makeDateString(dayPick,monthPick,yearPick));
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            yearPick = now.getYear();
            monthPick = now.getMonthValue();
            dayPick = now.getDayOfMonth();
        }



        // Set data trong spinner Danh mục
        ArrayList<String> danhMucThu = new ArrayList<>();
        danhMucThu.add("Tiền lương");
        danhMucThu.add("Tiền trọ");
        danhMucThu.add("Tiền thụ động");

        ArrayList<String> danhMucChi = new ArrayList<>();
        danhMucChi.add("Tiền ăn");
        danhMucChi.add("Tiền đi chơi");
        danhMucChi.add("Tiền du lịch");

        // ArrayAdapter để xử lý spnDanhMuc
        if (checkThuChi == 1) {
            adtDanhMuc = new ArrayAdapter<>(NhapThuChi.this, android.R.layout.simple_spinner_item, danhMucThu);
            adtDanhMuc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnDanhMuc.setAdapter(adtDanhMuc);
        }
        // Xử lý đổi giao diện mỗi khi bấm nút tiền thu
        btnTienChi.setBackgroundColor(Color.LTGRAY);
        btnTienThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTienThu.setBackgroundColor(Color.rgb(104, 4, 236));
                btnTienChi.setBackgroundColor(Color.LTGRAY);
                changeText(tvTien, etTien, "Tiền thu");
                checkThuChi = 1;
                btnNhapKhoan.setText("Nhập khoản thu");

                // reset Text
                resetText();
                // set spinnerDanhMuc khi bấm btnTienThu
                adtDanhMuc = new ArrayAdapter<>(NhapThuChi.this, android.R.layout.simple_spinner_item, danhMucThu);
                adtDanhMuc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnDanhMuc.setAdapter(adtDanhMuc);
            }
        });

        // Xử lý đổi giao diện mỗi khi bấm nút tiền chi
        btnTienChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTienChi.setBackgroundColor(Color.rgb(104, 4, 236));
                btnTienThu.setBackgroundColor(Color.LTGRAY);
                changeText(tvTien, etTien, "Tiền chi");
                checkThuChi = 2;
                btnNhapKhoan.setText("Nhập khoản chi");

                // reset Text
                resetText();
                // set spinnerDanhMuc khi bấm btnTienChi
                adtDanhMuc = new ArrayAdapter<>(NhapThuChi.this, android.R.layout.simple_spinner_item, danhMucChi);
                adtDanhMuc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnDanhMuc.setAdapter(adtDanhMuc);

            }
        });
        int id=0;
        btnNhapKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ngày người dùng chọn
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, yearPick); // set năm
                calendar1.set(Calendar.MONTH, monthPick-1); // set tháng (0 - 11)
                calendar1.set(Calendar.DAY_OF_MONTH, dayPick); // set ngày
                Date currentDatePicker = calendar1.getTime(); // lấy đối tượng Date

                // Kiểm tra xem tiền có trống ko
                if(checkError()==true){
                    //không làm gì cả
                }
                if(checkError()==false){
                    arrayGiaoDich.add(new GiaoDich(String.valueOf(arrayGiaoDich.size()),currentDatePicker,checkThuChi,
                            etGhiChu.getText().toString(),Integer.parseInt(etTien.getText().toString()),spnDanhMuc.getSelectedItem().toString()));
                    //Toast.makeText(NhapThuChi.this, "", Toast.LENGTH_SHORT).show();
                    String s = String.valueOf(arrayGiaoDich.size())+" - "+currentDatePicker+" - "+checkThuChi+" - "+
                            etGhiChu.getText().toString()+" - "+Integer.parseInt(etTien.getText().toString())+" - "+spnDanhMuc.getSelectedItem().toString();
                    Toast.makeText(NhapThuChi.this, s, Toast.LENGTH_SHORT).show();
                    resetText();
                    thongBao("add thành công");
                }
            }
        });

        //Trang chủ
        imgTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(NhapThuChi.this, TrangChu.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
            }
        });

        //Lịch sử giao dịch
        imgLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(NhapThuChi.this, LichSuGiaoDich.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
            }
        });

        //Tạo giao dịch

        //Thống kê
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(NhapThuChi.this, ThongKe.class);
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
                Intent intent = new Intent(NhapThuChi.this, DanhMuc.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 500);
            }
        });
    }


}
