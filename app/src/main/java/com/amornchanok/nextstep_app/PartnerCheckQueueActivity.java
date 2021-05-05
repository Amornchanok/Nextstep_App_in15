package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerManageActivity;

public class PartnerCheckQueueActivity extends AppCompatActivity {

    TableRow tbrRoom1,tbrRoom2,tbrRoom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_check_queue);

        tbrRoom1 = findViewById(R.id.tbrRoom1);
        tbrRoom2 = findViewById(R.id.tbrRoom2);
        tbrRoom3 = findViewById(R.id.tbrRoom3);

        tbrRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerCheckQueueActivity.this, PartnerQueueDetailActivity.class);
                startActivity(intent);
            }
        });

        tbrRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerCheckQueueActivity.this, PartnerQueueDetailActivity.class);
                startActivity(intent);
            }
        });

        tbrRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerCheckQueueActivity.this, PartnerQueueDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}