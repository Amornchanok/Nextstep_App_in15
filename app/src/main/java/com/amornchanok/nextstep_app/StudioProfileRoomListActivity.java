package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.amornchanok.nextstep_app.firebaseConnect.Rooms;
import com.amornchanok.nextstep_app.ViewHolder.RoomViewHolder;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StudioProfileRoomListActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    Firebase firebase;

    RecyclerView recyclerView;
    String Studio_id;
    Query databaseReference;
    FirebaseRecyclerOptions<Rooms> optionsrooms;
    FirebaseRecyclerAdapter<Rooms, RoomViewHolder> adapter;

    String studioId;
    String studioName;
    String studioImg;
    String studioLoc;
    String studioLogo;
    TextView text_studioId;
    TextView text_studioName;
    TextView text_studioImg;
    TextView text_studioLogo;
    TextView text_studioLoc;

    ImageView imgPreview;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        text_studioId = (TextView) findViewById(R.id.text_studioId);
        text_studioName = (TextView) findViewById(R.id.text_studioName);
        text_studioImg = (TextView) findViewById(R.id.text_studioImg);
        text_studioLogo = (TextView) findViewById(R.id.text_studioLogo);
        text_studioLoc = (TextView) findViewById(R.id.text_studioLoc);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        imgLogo= (ImageView) findViewById(R.id.imgLogo);

        Intent intent = getIntent();
        studioId = intent.getStringExtra("studioId");
        studioName = intent.getStringExtra("stdName");
        studioLoc = intent.getStringExtra("stdLocation");
        studioImg = intent.getStringExtra("imagePreview");
        studioLogo = intent.getStringExtra("imageLogo");

        text_studioId.setText("" + studioId);
        text_studioName.setText("" + studioName);
        text_studioImg.setText("" + studioImg);
        text_studioLogo.setText("" + studioLogo);
        text_studioLoc.setText("" + studioLoc);

        Picasso.get().load("" + studioImg).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imgPreview);
        Picasso.get().load("" + studioLogo).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imgLogo);

 // RoomList

        if (getIntent() != null)

        Studio_id = getIntent().getStringExtra("studioId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Rooms").orderByChild("StudioId").equalTo(Studio_id);
        optionsrooms = new FirebaseRecyclerOptions.Builder<Rooms>().setQuery(databaseReference, Rooms.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.room_list);
        recyclerView.setHasFixedSize(true);

        if (!Studio_id.isEmpty() && Studio_id!= null) {

            adapter = new FirebaseRecyclerAdapter<Rooms, RoomViewHolder>(optionsrooms) {
                @Override
                protected void onBindViewHolder(RoomViewHolder holder, int position, final Rooms model) {
                    holder.roomName.setText(model.getName());
                    holder.roomPrice.setText(model.getPrice() + "บาท/ชั่วโมง");
                    holder.roomCapacity.setText(model.getCapacity());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent i=new Intent(Page_room_list.this,Page_room_detail.class);
                            Intent i=new Intent(StudioProfileRoomListActivity.this, RoomDetailActivity.class);

                            i.putExtra("Studio_id",Studio_id);
                            i.putExtra("roomId",model.getID());
                            i.putExtra("roomPrice",model.getPrice());
                            i.putExtra("roomName",model.getName());
                            i.putExtra("roomCapacity",model.getCapacity());
                            i.putExtra("roomImage",model.getImage());
//                            Toast.makeText(getApplicationContext(),model.getName(),Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }
                    });

                    Picasso.get().load(model.getImage()).into(holder.roomImage, new Callback() {
                        @Override
                        public void onSuccess() { }

                        @Override
                        public void onError(Exception e) {

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
