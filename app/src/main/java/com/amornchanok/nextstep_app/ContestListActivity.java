package com.amornchanok.nextstep_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ContestListActivity extends AppCompatActivity {
    private RecyclerView recyclerView_3;
    private RecyclerView.Adapter adapter_3;
    private RecyclerView.LayoutManager layoutManager_3;
    ArrayList<Model_Contest> bloglist_3;
    ////////////////////////////////////////////////
    private RecyclerView recyclerView_2;
    private RecyclerView.Adapter adapter_2;
    private RecyclerView.LayoutManager layoutManager_2;
    ArrayList<Model_Contest> bloglist_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_list);

        bloglist_3 = new ArrayList<Model_Contest>();
        Query ref_3 = FirebaseDatabase.getInstance().getReference().child("Contests").orderByChild("tag").equalTo("now");
        ref_3.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                collectDetails_3((Map<String, Object>) dataSnapshot.getValue());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

///////
        bloglist_2 = new ArrayList<>();

        Query ref_2 = FirebaseDatabase.getInstance().getReference().child("Contests").orderByChild("tag").equalTo("coming");

        ref_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectDetails_2((Map<String, Object>) dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

////////////////////////////////////////////////////////////////////////////////////
        initializeRecyclerView_3();
        initializeRecyclerView_2();
    }

    private void initializeRecyclerView_3() {
        recyclerView_3 =findViewById(R.id.recyclerView_3);
        recyclerView_3.setNestedScrollingEnabled(false);
        recyclerView_3.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_3 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_3.setLayoutManager(layoutManager_3);
        adapter_3 = new Adapter_Contest(bloglist_3);
        recyclerView_3.setAdapter(adapter_3);
    }
    private void initializeRecyclerView_2() {
        recyclerView_2 =findViewById(R.id.recyclerView_2);
        recyclerView_2.setNestedScrollingEnabled(false);
        recyclerView_2.setHasFixedSize(false);
        // layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager_2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_2.setLayoutManager(layoutManager_2);
        adapter_2 = new Adapter_Contest(bloglist_2);
        recyclerView_2.setAdapter(adapter_2);
}
    private void collectDetails_3(Map<String, Object> posts) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            //Get user map
            Map singlePost = (Map) entry.getValue();
            Model_Contest blog = new Model_Contest();
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
            blog.setTag(singlePost.get("tag").toString());

            bloglist_3.add(blog);
            adapter_3.notifyDataSetChanged();
        }
    }
    private void collectDetails_2(Map<String, Object> posts) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            //Get user map
            Map singlePost = (Map) entry.getValue();
            Model_Contest blog = new Model_Contest();
            blog.setImage(singlePost.get("image").toString());
            blog.setName(singlePost.get("name").toString());
            blog.setTag(singlePost.get("tag").toString());

            bloglist_2.add(blog);
            adapter_2.notifyDataSetChanged();
        }
    }
}

