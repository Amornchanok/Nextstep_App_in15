package com.amornchanok.nextstep_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.modelStudioList.Categories;
import com.amornchanok.nextstep_app.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioListActivity extends AppCompatActivity {
    //////
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Categories>options;
    FirebaseRecyclerAdapter<Categories, CategoryViewHolder>adapter;
    RecyclerView recyclerView;
    ArrayList<Categories> arrayList;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_studio_list);

        arrayList=new ArrayList<>();

        //firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Studios2");

        options = new FirebaseRecyclerOptions.Builder<Categories>().setQuery(databaseReference, Categories.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.catlist);
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<Categories, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CategoryViewHolder holder, final int position, final Categories model) {
                holder.stdName.setText(model.getName());
                holder.stdLocation.setText(model.getLocation());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(StudioListActivity.this, Page_room_list.class);
                        // Intent i=new Intent(Categorylist.this,Show_list.class);
                        i.putExtra("categoryId", model.getID());
                        Toast.makeText(getApplicationContext(), model.getName(), Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });

                Picasso.get().load(model.getImage()).into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {


                    }
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.page_studio_item, viewGroup, false);
                return new CategoryViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


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

