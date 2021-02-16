package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amornchanok.nextstep_app.userBottomNavigation.BookingActivity;

import java.util.Set;

public class PaymentActivity extends AppCompatActivity {

    Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnPayment = findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PaymentActivity.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                startActivity(intent);
            }
        });
    }
}