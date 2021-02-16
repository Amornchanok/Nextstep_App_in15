package com.amornchanok.nextstep_app.searchStudio;


import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.StudioProfileRoomListActivity;
import com.amornchanok.nextstep_app.firebaseConnect.Studios;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRent extends RecyclerView.Adapter<AdapterRent.ViewHolder> {
    private EditText mSearchFeild;
    Context context;
    ArrayList<Model_Studios> arrayList = new ArrayList<>();
    public String s_image;
    public AdapterRent(Context context, ArrayList<Model_Studios> arrayList, EditText mSearchFeild) {
        this.context = context;
        this.arrayList = arrayList;
        this.mSearchFeild = mSearchFeild;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        TextView pid;
        TextView rentname;
        TextView renttime;
        TextView rentdate;
        TextView rentstatus;
        TextView rentprice;
        TextView rentuser;
        TextView imageURL;

        public TextView text_status;
        public ImageView imageStudio;
        public ImageView image1;
        public Button b_click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pid = (TextView) itemView.findViewById(R.id.pid);
            rentname = (TextView) itemView.findViewById(R.id.rentname);
            renttime = (TextView) itemView.findViewById(R.id.renttime);
            rentdate = (TextView) itemView.findViewById(R.id.rentdate);
            rentstatus = (TextView) itemView.findViewById(R.id.rentstatus);
            rentprice = (TextView) itemView.findViewById(R.id.rentprice);
            rentuser = (TextView) itemView.findViewById(R.id.rentuser);
            imageURL = (TextView) itemView.findViewById(R.id.imageurl);

            imageStudio = (ImageView) itemView.findViewById(R.id.imageStudio);
            text_status = (TextView) itemView.findViewById(R.id.text_status);
            b_click = (Button) itemView.findViewById(R.id.b_click);

            Picasso.get().load(""+imageURL).into(imageStudio);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
        }

        public void onClick(final int position, final String s) {
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mSearchFeild.setText(s);
//                    Intent intent = new Intent(context, StudioProfileRoomListActivity.class);
//                    intent.putExtra("pid", s);
//                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.pid.setText(arrayList.get(position).getId());
        holder.rentname.setText(arrayList.get(position).getName());
        holder.renttime.setText(arrayList.get(position).getLocation());
        holder.rentdate.setText(arrayList.get(position).getLogo());
        holder.rentstatus.setText(arrayList.get(position).getTag());
        holder.imageURL.setText(arrayList.get(position).getImage());
        holder.b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, StudioProfileRoomListActivity.class);
//                intent.putExtra("pid", model.getID());
//                intent.putExtra("rentname", model.getRentname());
//                intent.putExtra("renttime", model.getRenttime());
//                intent.putExtra("image", model.getImage());



//                intent.putExtra("Place_id", Place_id);
//                intent.putExtra("Name", Name);
//                intent.putExtra("Description", Description);
//                intent.putExtra("Image", Image);
//                startActivity(intent);


//                Intent intent =  new Intent(context, Page_user_rent.class);
//                Intent intent =  new Intent(context, DialogActivity_1.class);
//                context.startActivity(intent);


//                Intent intent =  new Intent(context, Page_user_rent.class);
//                Intent intent =  new Intent(context, StudioProfileRoomListActivity.class);
//                Intent intent = new Intent(context, DialogActivity_1.class);
//                intent.putExtra("pid", getID);
//                 intent.putExtra("rentname", s_rentname);
//                intent.putExtra("renttime", s_renttime);
//                intent.putExtra("pid", arrayList.get(position).getId());
//                intent.putExtra("rentname", arrayList.get(position).getName());
//                intent.putExtra("renttime", arrayList.get(position).getLocation());
//                context.startActivity(intent);

            }
        });


        holder.onClick(position, arrayList.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
