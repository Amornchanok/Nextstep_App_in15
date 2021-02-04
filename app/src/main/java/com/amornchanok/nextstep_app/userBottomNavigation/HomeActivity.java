package com.amornchanok.nextstep_app.userBottomNavigation;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.amornchanok.nextstep_app.StudioProfileRoomListActivity;
import com.amornchanok.nextstep_app.ViewHolder.StudioViewHolder;

import com.amornchanok.nextstep_app.firebaseStudio.Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.searchStudio.SearchStudioActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private Button bt_service1;

    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Studios> options;
    FirebaseRecyclerAdapter<Studios, StudioViewHolder> adapter;
    RecyclerView recyclerView;
    ArrayList<Studios> arrayList;

    AlertDialog dialog;
    ArrayList<Studios> studios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        arrayList=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Studios");
        options = new FirebaseRecyclerOptions.Builder<Studios>().setQuery(databaseReference, Studios.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.studiolist);
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<Studios, StudioViewHolder>(options) {
            @Override
            protected void onBindViewHolder(StudioViewHolder holder, final int position, final Studios model) {
                holder.stdName.setText(model.getName());
                holder.stdLocation.setText(model.getLocation());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(HomeActivity.this, StudioProfileRoomListActivity.class);
                        i.putExtra("studioId", model.getID());
                        Toast.makeText(getApplicationContext(), model.getName(), Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });

                Picasso.get().load(model.getImage()).into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError(Exception e) {

                    }
                });

            }

            @NonNull
            @Override
            public StudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_studio, viewGroup, false);
                return new StudioViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

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

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }


}


