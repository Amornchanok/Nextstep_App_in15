package com.amornchanok.nextstep_app.searchStudio;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.amornchanok.nextstep_app.studioProfile.StudioProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.amornchanok.nextstep_app.studioProfile.StudioProfileActivity.INTENT_STUDIO_ID;

public class SearchRoomAdapter extends RecyclerView.Adapter<SearchRoomAdapter.MyViewHolder> {

    private ArrayList<Studios> studios;
    private Activity activity;

    public SearchRoomAdapter(Activity activity , ArrayList<Studios> studios) {
        this.studios = studios;
        this.activity =activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new MyViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studio_home,parent,false));
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        final Studios studio = studios.get(position);

        holder.tvName.setText(studio.getStudioname());
        holder.tvPrice.setText(studio.getPrice() + " บาท / ชั่วโมง" );
        Picasso.get().load(studio.getPic().getPreview()).into(holder.ivPic);

        holder.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, StudioProfileActivity.class);
                intent.putExtra(INTENT_STUDIO_ID,studio.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studios.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvPrice;
        ImageView ivPic;
        public MyViewHolder(View v){
            super(v);
            this.tvName = v.findViewById(R.id.studioname);
            this.tvPrice = v.findViewById(R.id.studioprice);
            this.ivPic = v.findViewById(R.id.studiopic);

        }
    }
}
