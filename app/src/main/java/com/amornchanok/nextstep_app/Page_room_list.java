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


import com.amornchanok.nextstep_app.modelStudioList.Products;
import com.amornchanok.nextstep_app.ViewHolder.ProductViewHolder;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Page_room_list extends AppCompatActivity {

    RecyclerView recyclerView;

    String Category_id;
    Query databaseReference;
    FirebaseRecyclerOptions<Products> optionsproducts;
    FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_room_list);

        if (getIntent() != null)

        Category_id = getIntent().getStringExtra("categoryId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Room").orderByChild("MenuId").equalTo(Category_id);
        optionsproducts = new FirebaseRecyclerOptions.Builder<Products>().setQuery(databaseReference, Products.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.room_list);
        recyclerView.setHasFixedSize(true);

        if (!Category_id.isEmpty() && Category_id != null) {


            adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(optionsproducts) {
                @Override
                protected void onBindViewHolder(ProductViewHolder holder, int position, final Products model) {



                    holder.productname.setText(model.getName());
                    Glide.with(holder.imageproduct.getContext()).load(model.getImage()).into(holder.imageproduct);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent i=new Intent(Page_room_list.this,Page_room_detail.class);
                            Intent i=new Intent(Page_room_list.this, Page_room_detail.class);

                            i.putExtra("Category_id",Category_id);
                            i.putExtra("roomId",model.getID());
                            i.putExtra("roomName",model.getName());
                            i.putExtra("roomDescribtion",model.getDescription());
                           // i.putExtra("RoomPrice",model.getPrice());
                           // i.putExtra("RoomQuantity",model.getQuantity());
                            i.putExtra("roodImage",model.getImage());
//                            Toast.makeText(getApplicationContext(),model.getName(),Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }
                    });
                }

                @NonNull
                @Override
                public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.room_item, viewGroup, false);
                    return new ProductViewHolder(view);


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
