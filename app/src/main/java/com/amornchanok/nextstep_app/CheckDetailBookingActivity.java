package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckDetailBookingActivity extends AppCompatActivity {

    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detail_booking);

        btnBooking = findViewById(R.id.btnBooking);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CheckDetailBookingActivity.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);

            }
        });
    }
}