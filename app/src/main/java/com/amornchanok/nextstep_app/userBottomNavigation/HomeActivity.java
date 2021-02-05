package com.amornchanok.nextstep_app.userBottomNavigation;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.amornchanok.nextstep_app.SuggestListAdapter;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.searchStudio.SearchStudioActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private Button bt_service1;

    RecyclerView recyclerView;
    ArrayList<String> studiolist;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    SuggestListAdapter suggestListAdapter;
    LinearLayoutManager HorizontalLayout;

    View ChildView;
    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        AddItemsToRecyclerViewArrayList();

        suggestListAdapter = new SuggestListAdapter(studiolist);

        HorizontalLayout = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(suggestListAdapter);

        // Button Service

        bt_service1 = (Button) findViewById(R.id.bt_service1);
        bt_service1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchroom();
            }
        });

        BottomNavigationView bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.home);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.myBooking:
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.noti:
                        startActivity(new Intent(getApplicationContext(), NotiActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }


    public void openSearchroom() {
        Intent intent = new Intent(HomeActivity.this, SearchStudioActivity.class);
        startActivity(intent);
    }

    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        studiolist = new ArrayList<>();
        studiolist.add("D Dance Studio");
        studiolist.add("Independanze");
        studiolist.add("Fitness 7");
        studiolist.add("Danctination");
        studiolist.add("Pandora Box");
        studiolist.add("The Artist");

    }


}


