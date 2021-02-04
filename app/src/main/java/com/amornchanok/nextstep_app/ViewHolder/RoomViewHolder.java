package com.amornchanok.nextstep_app.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.R;


public class RoomViewHolder extends RecyclerView.ViewHolder  {

    public TextView roomName;
    public ImageView roomImage;
    public TextView roomPrice;
    public TextView roomCapacity;



    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        roomName=(TextView)itemView.findViewById(R.id.roomName);
        roomPrice=(TextView)itemView.findViewById(R.id.roomPrice);
        roomCapacity=(TextView)itemView.findViewById(R.id.roomCapacity);
        roomImage=(ImageView)itemView.findViewById(R.id.roomImage);

    }





}
