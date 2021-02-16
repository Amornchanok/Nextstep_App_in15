package com.amornchanok.nextstep_app;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // private DatabaseReference mDatabaseReference;
    // private List<Model_Studios> blogList;
    // private FirebaseDatabase mDatabase;
    // private FirebaseUser mUser;
    // private FirebaseAuth mAuth;
    private RecyclerView recyclerView_3;
    private RecyclerView.Adapter adapter_3;
    private RecyclerView.LayoutManager layoutManager_3;
    ArrayList<Model_Studios> bloglist_3;
    ////////////////////////////////////////////////
    //private DatabaseReference mDatabaseReference;
    // private List<Model_Studios> blogList;
    // private FirebaseDatabase mDatabase;
    // private FirebaseUser mUser;
    // private FirebaseAuth mAuth;
    private RecyclerView recyclerView_2;
    private RecyclerView.Adapter adapter_2;
    private RecyclerView.LayoutManager layoutManager_2;
    ArrayList<Model_Studios> bloglist_2;



    // String s_tag = "promotion";
    // String s_tag = "promotion";
    // String s_setValue = "promotion";
    // DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //   ActionBar actionBar = getSupportActionBar();
        //     actionBar.hide();


        bloglist_3 = new ArrayList<>();
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Studios");
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Studios").child("tag");
        //database.getReference("Messages").child(roomID).child(msgID).child("status").setValue("delivered");
        //  mDatabase.getReference().child("Studios").child("tag").setValue("promotion");
        // mUserCompounds.orderByChild("status").equalTo("NOT OK").addChildEventListener(new ChildEventListener() {


        Query ref_3 = FirebaseDatabase.getInstance().getReference().child("Studios2").orderByChild("tag").equalTo("promotion");

        //////////////////////////////////////////////////////////////////////////                                                                          }
        ref_3.addValueEventListener(new ValueEventListener() {
            // ref.orderByChild("tag").equalTo("promotion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Log.d("HomePgae.this","startttttttttttt");

                collectDetails_3((Map<String, Object>) dataSnapshot.getValue());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bloglist_2 = new ArrayList<>();
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Studios");
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Studios").child("tag");
        //database.getReference("Messages").child(roomID).child(msgID).child("status").setValue("delivered");
        //  mDatabase.getReference().child("Studios").child("tag").setValue("promotion");
        // mUserCompounds.orderByChild("status").equalTo("NOT OK").addChildEventListener(new ChildEventListener() {


        Query ref_2 = FirebaseDatabase.getInstance().getReference().child("Studios2").orderByChild("tag").equalTo("suggest");

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



    private void initializeRecyclerView_3() {
        recyclerView_3 =findViewById(R.id.recyclerView_3);
        recyclerView_3.setNestedScrollingEnabled(false);
        recyclerView_3.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_3.setLayoutManager(layoutManager_3);
        adapter_3 = new Adapter_Studios(bloglist_3);
        recyclerView_3.setAdapter(adapter_3);
    }
    private void initializeRecyclerView_2() {
        recyclerView_2 =findViewById(R.id.recyclerView_2);
        recyclerView_2.setNestedScrollingEnabled(false);
        recyclerView_2.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_2.setLayoutManager(layoutManager_2);
        adapter_2 = new Adapter_Studios(bloglist_2);
        recyclerView_2.setAdapter(adapter_2);
    }
    private void collectDetails_3(Map<String, Object> posts) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            //Get user map
            Map singlePost = (Map) entry.getValue();
            //Get phone field and append to list
            // String title = singlePost.get("title");
            //  phoneNumbers.add((Long) singleUser.get("phone"));
            Model_Studios blog = new Model_Studios();
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
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
            //Get phone field and append to list
            // String title = singlePost.get("title");
            //  phoneNumbers.add((Long) singleUser.get("phone"));
            Model_Studios blog = new Model_Studios();
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
            blog.setTag(singlePost.get("tag").toString());
            //   blog.setTimestamp(singlePost.get("timestamp").toString());
            //   blog.setUserid(singlePost.get("userid").toString());

            bloglist_2.add(blog);
            //Log.d("HomePgae.this","valllllllllllllll"+singlePost.get("title").toString());
            adapter_2.notifyDataSetChanged();
        }

    }
}

