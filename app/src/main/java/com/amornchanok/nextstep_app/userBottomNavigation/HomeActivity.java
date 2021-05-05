package com.amornchanok.nextstep_app.userBottomNavigation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amornchanok.nextstep_app.Adapter_Studios;
import com.amornchanok.nextstep_app.ContestListActivity;
import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.searchStudio.Page_search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private Button bt_service1,bt_service2;
    private RecyclerView recyclerView_3;
    private RecyclerView.Adapter adapter_3;
    private RecyclerView.LayoutManager layoutManager_3;
    ArrayList<Model_Studios> bloglist_3;
    private RecyclerView recyclerView_2;
    private RecyclerView.Adapter adapter_2;
    private RecyclerView.LayoutManager layoutManager_2;
   ArrayList<Model_Studios> bloglist_2;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        myDialog = new Dialog(this);
        ShowPopup();

        bt_service1 = (Button) findViewById(R.id.bt_service1);
        bt_service1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchroom();
            }
        });


        bt_service2 = (Button) findViewById(R.id.bt_service2);
        bt_service2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchcontest();
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

        bloglist_3 = new ArrayList<>();
        Query ref_3 = FirebaseDatabase.getInstance().getReference().child("Studios").orderByChild("tag").equalTo("promotion");
        ref_3.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectDetails_3((Map<String, Object>) dataSnapshot.getValue());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bloglist_2 = new ArrayList<>();
        Query ref_2 = FirebaseDatabase.getInstance().getReference().child("Studios").orderByChild("tag").equalTo("suggest");

        //////////////////////////////////////////////////////////////////////////                                                                          }
        ref_2.addValueEventListener(new ValueEventListener() {
            // ref.orderByChild("tag").equalTo("promotion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Log.d("HomePgae.this","startttttttttttt");

                collectDetails_2((Map<String, Object>) dataSnapshot.getValue());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        initializeRecyclerView_3();
        initializeRecyclerView_2();

    }

    private void ShowPopup() {
        TextView tvClose;
        myDialog.setContentView(R.layout.activity_popup_hotdeal);
        tvClose = (TextView) myDialog.findViewById(R.id.tvClose);
        //Button = (Button) myDialog.findViewById(R.id)
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    private void initializeRecyclerView_3() {
        recyclerView_3 =findViewById(R.id.recyclerView_3);
        recyclerView_3.setNestedScrollingEnabled(false);
        recyclerView_3.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_3 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_3.setLayoutManager(layoutManager_3);
        adapter_3 = new Adapter_Studios(bloglist_3);
        recyclerView_3.setAdapter(adapter_3);
    }
    private void initializeRecyclerView_2() {
        recyclerView_2 =findViewById(R.id.recyclerView_2);
        recyclerView_2.setNestedScrollingEnabled(false);
        recyclerView_2.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_2.setLayoutManager(layoutManager_2);
        adapter_2 = new Adapter_Studios(bloglist_2);
        recyclerView_2.setAdapter(adapter_2);
    }
    private void collectDetails_3(Map<String, Object> posts) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            //Get user map
            Map singlePost = (Map) entry.getValue();
            Model_Studios blog = new Model_Studios();
            blog.setId(singlePost.get("id").toString());
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
            blog.setLocation(singlePost.get("location").toString());
            blog.setLogo(singlePost.get("logo").toString());
            blog.setTag(singlePost.get("tag").toString());
            //   blog.setTimestamp(singlePost.get("timestamp").toString());
            //   blog.setUserid(singlePost.get("userid").toString());

            bloglist_3.add(blog);
            //Log.d("HomePgae.this","valllllllllllllll"+singlePost.get("title").toString());
            adapter_3.notifyDataSetChanged();
        }

    }
    private void collectDetails_2(Map<String, Object> posts) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            //Get user map
            Map singlePost = (Map) entry.getValue();
            Model_Studios blog = new Model_Studios();
            blog.setId(singlePost.get("id").toString());
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
            blog.setLocation(singlePost.get("location").toString());
            blog.setLogo(singlePost.get("logo").toString());
            blog.setTag(singlePost.get("tag").toString());
            //   blog.setTimestamp(singlePost.get("timestamp").toString());
            //   blog.setUserid(singlePost.get("userid").toString());

            bloglist_2.add(blog);
            //Log.d("HomePgae.this","valllllllllllllll"+singlePost.get("title").toString());
            adapter_2.notifyDataSetChanged();
        }

    }

    public void openSearchroom() {
        Intent intent = new Intent(HomeActivity.this, Page_search.class);
        startActivity(intent);
    }

    public void openSearchcontest() {
        Intent i = new Intent(HomeActivity.this, ContestListActivity.class);
        startActivity(i);
    }

    public void ShowPopup (View v) {
        TextView tvClose;
        myDialog.setContentView(R.layout.activity_popup_hotdeal);
        tvClose = (TextView) myDialog.findViewById(R.id.tvClose);
        //Button = (Button) myDialog.findViewById(R.id)
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}

