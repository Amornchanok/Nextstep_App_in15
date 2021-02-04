package com.amornchanok.nextstep_app.partnerBottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.userBottomNavigation.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingPartnerModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_partner_mode);

        BottomNavigationView bottomBarPartner = findViewById(R.id.bottomBarPartner);
        bottomBarPartner.setSelectedItemId(R.id.bookingPartnerMode);
        bottomBarPartner.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.homePartnerMode:
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.notiPartnerMode:
                        startActivity(new Intent(getApplicationContext(), NotiPartnerModeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bookingPartnerMode:
                        return true;
                    case R.id.managePartnerMode:
                        startActivity(new Intent(getApplicationContext(), PartnerManageActivity.class));
                        overridePendingTransition(0,0);
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
