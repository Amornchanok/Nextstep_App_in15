package com.amornchanok.nextstep_app.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.Interface.ItemclickListner;
import com.amornchanok.nextstep_app.R;


public class StudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView stdName;
    public TextView stdLocation;
    public ImageView stdImage;
    private ItemclickListner itemclickListner;

    public StudioViewHolder(@NonNull View itemView) {
        super(itemView);
        stdLocation = (TextView) itemView.findViewById(R.id.studio_location);
        stdName = (TextView) itemView.findViewById(R.id.studio_name);
        stdImage = (ImageView) itemView.findViewById(R.id.studio_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemclickListner.onClick(v,getAdapterPosition(),false);
    }
}