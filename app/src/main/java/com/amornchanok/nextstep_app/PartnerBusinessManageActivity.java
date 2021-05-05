package com.amornchanok.nextstep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerManageActivity;
import com.amornchanok.nextstep_app.partnerRegister.PartnerRegisterOtpActivity;

public class PartnerBusinessManageActivity extends AppCompatActivity {

    Button btnSelectOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_business_manage);

        btnSelectOption = findViewById(R.id.btnSelectOption);
        btnSelectOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerBusinessManageActivity.this, BusinessSelectActivity.class);
                startActivity(intent);
            }
        });


    }
}