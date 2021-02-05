package com.amornchanok.nextstep_app.AdapterStudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.firebaseConnect.Studios;
import com.amornchanok.nextstep_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.StudioAdapterViewHolder> {

    public  Context context;
    public  ArrayList<Studios> arrayList;
    public StudioAdapter(Context context, ArrayList<Studios> arrayList)
    {
            this.context = context;
            this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public StudioAdapterViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_studio,viewGroup,false);
                return new StudioAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StudioAdapterViewHolder holder, int i) {
        Studios studioitem = arrayList.get(i);
        holder.studio_name.setText(studioitem.getName());
        Picasso.get().load(studioitem.getImage()).into(holder.studio_image);
    }

    public class StudioAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView studio_name;
        public ImageView studio_image;
        public StudioAdapterViewHolder(View itemView) {
            super(itemView);
            studio_name =(TextView)itemView.findViewById(R.id.studio_name);
            studio_image =(ImageView)itemView.findViewById(R.id.studio_image);

        }
    }
}
