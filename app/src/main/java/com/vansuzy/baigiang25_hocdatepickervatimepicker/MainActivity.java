package com.vansuzy.baigiang25_hocdatepickervatimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView txtDate, txtTime;
    ImageButton btnDate, btnTime;
    Calendar calendar = Calendar.getInstance(); // Calendar.getInstance(): trả về ngày -  tháng - năm, giờ - phút - giây hiện tại
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiDatePicker();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiTimePicker();
            }
        });
    }

    private void xuLyHienThiTimePicker() {
        final TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                txtTime.setText(sdf2.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this,
                callback,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true    // true: định dạng 24 giờ, false: định dạng 12 giờ
        );
        timePickerDialog.show();
    }

    private void xuLyHienThiDatePicker() {
        final DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {   // onDateSet: lắng nghe sự thay đổi mà người dùng thay đổi ngày tháng năm trên giao diện
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txtDate.setText(sdf1.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                callback,   // lắng nghe sự thay đổi của khách hàng
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ); // đè phím Ctrl và click chuột vào DatePickerDialog (sau): đối số đầu tiên truyền vào (Context) là màn hình hiện tại sử dụng nó, đối số thứ hai (OnDateSetListener) là 1 Variable as listener, 3 đối số đằng sau (int, int, int) là năm tháng ngày
        datePickerDialog.show();    // hiển thị cửa sổ DatePickerDialog lên (tức là cho phép chúng ta chỉnh ngày tháng năm trên đó).
    }

    private void addControls() {
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);
        btnDate = (ImageButton) findViewById(R.id.btnDate);
        btnTime = (ImageButton) findViewById(R.id.btnTime);

        calendar = Calendar.getInstance();
        txtDate.setText(sdf1.format(calendar.getTime()));
        txtTime.setText(sdf2.format(calendar.getTime()));
    }
}
