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
        roomName=(TextView)itemView.findViewById(R.id.room_name);
        roomPrice=(TextView)itemView.findViewById(R.id.room_price);
        roomCapacity=(TextView)itemView.findViewById(R.id.room_capacity);
        roomImage=(ImageView)itemView.findViewById(R.id.room_image);

    }

}
