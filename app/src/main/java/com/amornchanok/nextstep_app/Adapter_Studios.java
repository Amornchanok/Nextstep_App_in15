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

import java.util.List;

//import com.bumptech.glide.Glide;


public class Adapter_Studios extends RecyclerView.Adapter<Adapter_Studios.ViewHolder> {

    private Context context;
    private List<Model_Studios> blogList;
    Button b_click;


    public Adapter_Studios(List<Model_Studios> blogList) {
        //this.context = context;
        this.blogList = blogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_1, parent, false);
        context = parent.getContext();

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Model_Studios blog = blogList.get(position);
        String imageUrl = null;
        holder.id.setText(blog.getId());
        holder.name.setText(blog.getName());
        holder.location.setText(blog.getLocation());
        holder.logo.setText(blog.getLogo());
        holder.text_image.setText(blog.getImage());


      //  java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
       // String formattedDate = dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())).getTime());


       // holder.timestamp.setText(formattedDate);

        //Glide.with(context)
        //        .load(Uri.parse(blog.getImage()))
        //        .into(holder.image);


        // holder.image.setImageURI(Uri.parse(blog.getImage()));
        //holder.mItem = mValues.get(position);

        // imageUrl = blog.getImage();


        /*
        //Picasso.with(context)
          //      .load(imageUrl)
            //    .into(holder.image);*/

        Picasso.get().load(blog.getImage()).into(holder.image);

        // holder.b_click = (Button) findViewById(R.id.b_click);
        //holder.rentstatus.setText(blogList.get(position).getRentstatus());
//        holder.b_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  Log.e("test", "" + position + " : " + contacts.get(position).isBlocked());
//
//                Intent intent = new Intent(context, StudioProfileRoomListActivity.class);
//                //intent.putExtra("pid", s_pid);
//                // intent.putExtra("rentname", s_rentname);
//                //intent.putExtra("renttime", s_renttime);
//                intent.putExtra("title", holder.title.getText().toString().trim());
//
//                context.startActivity(intent);
//            }
//        });
//        b_click = (Button) view.findViewById(R.id.b_click);
//        b_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                Intent intent = new Intent(Place_list_rent_time.this, Page_user_rent.class);
////                intent.putExtra("pid", model.getPid());
////                intent.putExtra("rentname", model.getRentname());
////                intent.putExtra("renttime", model.getRenttime());
//////                                intent.putExtra("description", model.getDescription());
//////                                //intent.putExtra("image_url", model.getImage_url());
//////                                intent.putExtra("image", model.getImage());
/////////////////////////////////////////////////////////////////////////////////////////////////
////
////                intent.putExtra("Place_id", Place_id);
////                intent.putExtra("Name", Name);
////                intent.putExtra("Description", Description);
////                intent.putExtra("Image", Image);
////                startActivity(intent);
//
//
////                // Intent intent =  new Intent(context, Page_user_rent.class);
////                Intent intent =  new Intent(context, DialogActivity_1.class);
////
////                context.startActivity(intent);
//
//
////               // Intent intent =  new Intent(context, Page_user_rent.class);
//                //Intent intent =  new Intent(context, Page_user_rent_3.class);
//                Intent intent = new Intent(context, Detail_1.class);
//                //intent.putExtra("pid", s_pid);
//                // intent.putExtra("rentname", s_rentname);
//                //intent.putExtra("renttime", s_renttime);
//                //  intent.putExtra("pid", blogList.get(view).
//
//                context.startActivity(intent);
//
//
//            }
//        });
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
           // timestamp = (TextView) view.findViewById(R.id.posttimelist);

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
