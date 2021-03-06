package com.amornchanok.nextstep_app.partnerBottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.PartnerAddRoomActivity;
import com.amornchanok.nextstep_app.PartnerBusinessManageActivity;
import com.amornchanok.nextstep_app.PartnerCheckQueueActivity;
import com.amornchanok.nextstep_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PartnerManageActivity extends AppCompatActivity {

    Button btManageBusiness,btBookingFromUser;
    Button btAddRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_manage);

        btAddRoom = findViewById(R.id.btAddRoom);
        btBookingFromUser  = findViewById(R.id.btBookingFromUser);

        btBookingFromUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerManageActivity.this, PartnerCheckQueueActivity.class);
                startActivity(intent);
            }
        });

        btAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerManageActivity.this, PartnerAddRoomActivity.class);
                startActivity(intent);
            }
        });

        btManageBusiness = (Button) findViewById(R.id.btManageBusiness);
        btManageBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerManageActivity.this, PartnerBusinessManageActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomBarPartner = findViewById(R.id.bottomBarPartner);
        bottomBarPartner.setSelectedItemId(R.id.managePartnerMode);
        bottomBarPartner.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homePartnerMode:
                        startActivity(new Intent(getApplicationContext(), HomePartnerActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notiPartnerMode:
                        startActivity(new Intent(getApplicationContext(), NotiPartnerModeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bookingPartnerMode:
                        startActivity(new Intent(getApplicationContext(), BookingPartnerModeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.managePartnerMode:
                        return true;
                    case R.id.profilePartnerMode:
                        startActivity(new Intent(getApplicationContext(), PartnerProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
