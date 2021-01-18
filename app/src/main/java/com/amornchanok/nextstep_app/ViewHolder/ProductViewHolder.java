package com.amornchanok.nextstep_app.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.R;


public class ProductViewHolder extends RecyclerView.ViewHolder  {

    public TextView productname;
    public ImageView imageproduct;



    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productname=(TextView)itemView.findViewById(R.id.Product_name);
        imageproduct=(ImageView)itemView.findViewById(R.id.product_image);

    }





}
