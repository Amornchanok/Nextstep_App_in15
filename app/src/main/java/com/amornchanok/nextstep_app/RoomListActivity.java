package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.amornchanok.nextstep_app.firebaseStudio.Rooms;
import com.amornchanok.nextstep_app.ViewHolder.RoomViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class RoomListActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    TextView tvStdName,tvStdLocation;

    RecyclerView recyclerView;

    String Studio_id;
    Query databaseReference;
    FirebaseRecyclerOptions<Rooms> optionsproducts;
    FirebaseRecyclerAdapter<Rooms, RoomViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        if (getIntent() != null)

        Studio_id = getIntent().getStringExtra("studioId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Rooms").orderByChild("StudioId").equalTo(Studio_id);
        optionsproducts = new FirebaseRecyclerOptions.Builder<Rooms>().setQuery(databaseReference, Rooms.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.room_list);
        recyclerView.setHasFixedSize(true);

        if (!Studio_id.isEmpty() && Studio_id!= null) {


            adapter = new FirebaseRecyclerAdapter<Rooms, RoomViewHolder>(optionsproducts) {
                @Override
                protected void onBindViewHolder(RoomViewHolder holder, int position, final Rooms model) {



                    holder.roomName.setText(model.getName());
                    Picasso.get().load(getIntent().getStringExtra("roomImage")).into(holder.roomImage);
//                    Glide.with(holder.roomImage.getContext()).load(model.getImage()).into(holder.roomImage);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent i=new Intent(Page_room_list.this,Page_room_detail.class);
                            Intent i=new Intent(RoomListActivity.this, RoomDetailActivity.class);

                            i.putExtra("Studio_id",Studio_id);
                            i.putExtra("roomId",model.getID());
                            i.putExtra("roomName",model.getName());
                            i.putExtra("roomCapacity",model.getCapacity());
                            i.putExtra("roodImage",model.getImage());
//                            Toast.makeText(getApplicationContext(),model.getName(),Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }
                    });
                }

                @NonNull
                @Override
                public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_room, viewGroup, false);
                    return new RoomViewHolder(view);


                }
            };

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);



        }
    }

//        databaseReference = firebaseDatabase.getReference();
//
//        databaseReference.child("Room/MenuId").addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange (DataSnapshot snapshot){
//            showData(snapshot);
//        }
//
//        @Override
//        public void onCancelled (DatabaseError error){
//
//        }
//    });
//
//    private  void showData(DataSnapshot snapshot) {
//
//        Categories uInfo = snapshot.getValue(Categories.class);
//
//        tvStdName = (TextView) findViewById(R.id.tvStdName);
//        tvStdName.setText(uInfo.getName());
//        tvStdLocation= (TextView) findViewById(R.id.tvStdLocation);
//        tvStdLocation.setText(uInfo.getLocation());
//
//    }

    @Override
    protected void onStart() {

        if(adapter!=null)
            adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

}
