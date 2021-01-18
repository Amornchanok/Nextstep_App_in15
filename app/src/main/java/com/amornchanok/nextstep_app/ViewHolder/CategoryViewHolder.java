package com.amornchanok.nextstep_app.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.Interface.ItemclickListner;
import com.amornchanok.nextstep_app.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView stdName;
    public TextView stdLocation;
    public ImageView imageView;
    private ItemclickListner itemclickListner;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        stdLocation = (TextView) itemView.findViewById(R.id.stdLocation);
        stdName = (TextView) itemView.findViewById(R.id.category_name);
        imageView = (ImageView) itemView.findViewById(R.id.category_image);

itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        itemclickListner.onClick(v,getAdapterPosition(),false);
    }
}