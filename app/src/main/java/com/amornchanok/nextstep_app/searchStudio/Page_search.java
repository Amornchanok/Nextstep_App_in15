package com.amornchanok.nextstep_app.searchStudio;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.MapsActivity;
import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Page_search extends AppCompatActivity {

    private EditText mSearchField;
    Button search_btn;
    private RecyclerView mRecyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    ArrayList<Model_Studios> arrayList = new ArrayList<>();

    TextView heading;
    //SearchAdapter searchAdapter;
    AdapterRent searchAdapter;
    Button btnLoction;
    String s_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_search_2);

        btnLoction = findViewById(R.id.btnLoction);
        btnLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map = new Intent(Page_search.this, MapsActivity.class);
                startActivity(map);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSearchField = (EditText) findViewById(R.id.search_field);
        search_btn = (Button) findViewById(R.id.search_btn);

        mRecyclerView = (RecyclerView) findViewById(R.id.result_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               // heading.setVisibility(View.INVISIBLE);
               // heading.setHeight(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }
            }
        });


        // for search button click
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mSearchField.getText().toString();
                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }

            }
        });

    }

    private void setAdapter(final String searchString) {
         databaseReference.child("Studios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //for every new search
                arrayList.clear();
                mRecyclerView.removeAllViews();

                int counter = 0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    String location = snapshot.child("location").getValue(String.class);
                    String logo = snapshot.child("logo").getValue(String.class);
                    String image = snapshot.child("image").getValue(String.class);
                    String id = snapshot.child("id").getValue(String.class);
                    String tag = snapshot.child("tag").getValue(String.class);

                    if (location.startsWith(searchString)){
                        arrayList.add(new Model_Studios(name, location, logo, image, id, tag));
                        counter++;
                    }

                    //for the top 15 results
                    if (counter  == 20){
                        break;
                    }
                }

                searchAdapter = new AdapterRent(Page_search.this, arrayList, mSearchField);
                mRecyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
