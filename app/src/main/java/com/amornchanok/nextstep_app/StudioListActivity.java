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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.firebaseConnect.Studios;
import com.amornchanok.nextstep_app.ViewHolder.StudioViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioListActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Studios>options;
    FirebaseRecyclerAdapter<Studios, StudioViewHolder>adapter;
    RecyclerView recycleView;
    ArrayList<Studios> arrayList;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_studio_list);

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Studios");
        options = new FirebaseRecyclerOptions.Builder<Studios>().setQuery(databaseReference, Studios.class).build();

        recycleView = (RecyclerView) findViewById(R.id.studiolist);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        adapter = new FirebaseRecyclerAdapter<Studios, StudioViewHolder>(options) {
            @Override
            protected void onBindViewHolder(StudioViewHolder holder, final int position, final Studios model) {
                holder.stdName.setText(model.getName());
                holder.stdLocation.setText(model.getLocation());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(StudioListActivity.this, StudioProfileRoomListActivity.class);
                        i.putExtra("studioId", model.getID());
                        i.putExtra("stdName", model.getName());
                        i.putExtra("stdLocation", model.getLocation());
                        i.putExtra("imageLogo", model.getLogo());
                        i.putExtra("imagePreview", model.getImage());
                        Toast.makeText(getApplicationContext(), model.getName(), Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });

                Picasso.get().load(model.getImage()).into(holder.stdImage, new Callback() {
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
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.setAdapter(adapter);

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

