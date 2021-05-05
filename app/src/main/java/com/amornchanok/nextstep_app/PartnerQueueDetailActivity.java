package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amornchanok.nextstep_app.userBottomNavigation.BookingActivity;

public class PartnerQueueDetailActivity extends AppCompatActivity {

    Button btnAddOption;
    Dialog myDialog;
    Button btnAddRoom,btnAddPro,btnAddQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_queue_detail);

        myDialog = new Dialog(this);

        btnAddOption = findViewById(R.id.btnAddOption);
        btnAddOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });
    }

    private void ShowPopup() {
        myDialog.setContentView(R.layout.activity_popup_add_option);
        btnAddRoom = (Button) myDialog.findViewById(R.id.btnAddRoom);
        btnAddQueue = (Button) myDialog.findViewById(R.id.btnAddQueue);
        btnAddPro = (Button) myDialog.findViewById(R.id.btnAddPro);
        //Button = (Button) myDialog.findViewById(R.id)
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PartnerQueueDetailActivity.this, PartnerAddRoomActivity.class);
                startActivity(intent1);
            }
        });
        btnAddQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2  = new Intent(PartnerQueueDetailActivity.this, PartnerAddBookingActivity.class);
                startActivity(intent2);
            }
        });
        btnAddPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PartnerQueueDetailActivity.this, PartnerAddPromotion.class);
                startActivity(intent3);
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}