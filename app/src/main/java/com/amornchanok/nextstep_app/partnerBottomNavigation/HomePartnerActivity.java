package com.amornchanok.nextstep_app.partnerBottomNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amornchanok.nextstep_app.Adapter_Studios;
import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.partnerBottomNavigation.NotiPartnerModeActivity;
import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerManageActivity;
import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerProfileActivity;
import com.amornchanok.nextstep_app.searchStudio.Page_search;
import com.amornchanok.nextstep_app.userBottomNavigation.BookingActivity;
import com.amornchanok.nextstep_app.userBottomNavigation.NotiActivity;
import com.amornchanok.nextstep_app.userBottomNavigation.UserProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class HomePartnerActivity extends AppCompatActivity {
    private Button bt_service1;
    private RecyclerView recyclerView_3;
    private RecyclerView.Adapter adapter_3;
    private RecyclerView.LayoutManager layoutManager_3;
    ArrayList<Model_Studios> bloglist_3;
    private RecyclerView recyclerView_2;
    private RecyclerView.Adapter adapter_2;
    private RecyclerView.LayoutManager layoutManager_2;
    ArrayList<Model_Studios> bloglist_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_partner);

        bt_service1 = (Button) findViewById(R.id.bt_service1);
        bt_service1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchroom();
            }
        });

        BottomNavigationView bottomBarPartner = findViewById(R.id.bottomBarPartner);
        bottomBarPartner.setSelectedItemId(R.id.bookingPartnerMode);
        bottomBarPartner.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homePartnerMode:
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

    public void openSearchroom() {
//        Intent intent = new Intent(com.amornchanok.nextstep_app.partnerBottomNavigation.HomePartnerActivity.this, Page_search.class);
//        startActivity(intent);
    }
}

