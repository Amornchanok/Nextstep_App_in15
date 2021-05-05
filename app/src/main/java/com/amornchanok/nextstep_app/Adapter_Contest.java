package com.amornchanok.nextstep_app;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import com.bumptech.glide.Glide;


public class Adapter_Contest extends RecyclerView.Adapter<Adapter_Contest.ViewHolder> {

    private Context context;
    private List<Model_Contest> blogList;
    Button b_click;


    public Adapter_Contest(ArrayList<Model_Contest> blogList) {
        //this.context = context;
        this.blogList = blogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_2, parent, false);
        context = parent.getContext();

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Model_Contest blog = blogList.get(position);
        String imageUrl = null;
        holder.id.setText(blog.getId());
        holder.name.setText(blog.getName());
        holder.text_image.setText(blog.getImage());


        Picasso.get().load(blog.getImage()).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView name;
        public TextView location;
        public TextView logo;
        public TextView text_image;
        public ImageView image;
        public Button b_click;
        String userid;

        public ViewHolder(View view) {
            super(view);

            b_click = (Button) view.findViewById(R.id.b_click);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            location = (TextView) view.findViewById(R.id.text_location);
            logo = (TextView) view.findViewById(R.id.logo);
            text_image = (TextView) view.findViewById(R.id.text_image);
            image = (ImageView) view.findViewById(R.id.image);

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to the next activity...


                    Intent intent = new Intent(context, StudioProfileRoomListActivity.class);

                    intent.putExtra("studioId", id.getText().toString().trim());
                    intent.putExtra("stdName", name.getText().toString().trim());
                    intent.putExtra("stdLocation",location.getText().toString().trim());
                    intent.putExtra("imageLogo", logo.getText().toString().trim());
                    intent.putExtra("imagePreview", text_image.getText().toString().trim());
                    //intent.putExtra("title", title.getText().toString().trim());

                    context.startActivity(intent);


                }
            });


        }
    }
}
