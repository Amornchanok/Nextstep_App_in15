package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.UserProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_noti);

        BottomNavigationView bottomBar = findViewById(R.id.bottomBar);

        bottomBar.setSelectedItemId(R.id.noti);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myBooking:
                        startActivity(new Intent(getApplicationContext(),BookingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.noti:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
}
