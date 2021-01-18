package com.amornchanok.nextstep_app.studioHome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.amornchanok.nextstep_app.studioProfile.StudioProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import java.util.ArrayList;

public class StudioHomeActivity extends AppCompatActivity implements StudioHomeAdapter.OnItemClickListener {
    public static final String EXTRA_NAME = "studioname";
    public static final String EXTRA_PIC = "pic";

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ArrayList <Studios> studios = new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studiolist);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.listviewtxt);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        databaseReference.child("Studios").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot datachild :dataSnapshot.getChildren()){
                            Studios value=datachild.getValue(Studios.class);
                            studios.add(value);
                        }
                        adapter = new StudioHomeAdapter(studios);
                        recyclerView.setAdapter(adapter);
                        Log.d("test", new Gson().toJson(studios));
//                        adapter.setOnItemClickListener(StudioHomeActivity,this)
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );


//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String value=dataSnapshot.getValue(Studios.class).toString();
//                arrayList.add(value);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(StudioHomeActivity.this, StudioProfileActivity.class);
        Studios clickedItem = studios.get(position);

        detailIntent.putExtra(EXTRA_PIC, clickedItem.getPic().getPreview());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getStudioname());

        startActivity(detailIntent);
    }
}
