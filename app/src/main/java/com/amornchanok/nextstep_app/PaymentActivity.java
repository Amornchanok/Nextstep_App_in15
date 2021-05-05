package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amornchanok.nextstep_app.firebaseConnect.Booking;
import com.amornchanok.nextstep_app.partnerRegister.PartnerConditionActivity;
import com.amornchanok.nextstep_app.userBottomNavigation.BookingActivity;
import com.amornchanok.nextstep_app.userBottomNavigation.UserProfileActivity;

import java.util.Set;

public class PaymentActivity extends AppCompatActivity {

    Button btnPayment;
    Button btnBooking;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myDialog = new Dialog(this);

        btnPayment = findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();

//                Toast.makeText(PaymentActivity.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void ShowPopup() {
        myDialog.setContentView(R.layout.activity_popup_noti_booking);
        btnBooking = (Button) myDialog.findViewById(R.id.btnBooking);
        //Button = (Button) myDialog.findViewById(R.id)
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent partnerMode = new Intent(PaymentActivity.this, BookingActivity.class);
                startActivity(partnerMode);
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}