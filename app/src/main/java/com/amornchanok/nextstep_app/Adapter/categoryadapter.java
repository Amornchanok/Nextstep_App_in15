package com.amornchanok.nextstep_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.modelStudioList.Categories;
import com.amornchanok.nextstep_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categoryadapter extends RecyclerView.Adapter<categoryadapter.categoryadapterViewHolder> {


    public  Context c;
    public  ArrayList<Categories> arrayList;
    public categoryadapter(Context c, ArrayList<Categories> arrayList)
    {
            this.c=c;
            this.arrayList=arrayList;
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
    public categoryadapterViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.page_studio_item,viewGroup,false);
        return new categoryadapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(categoryadapterViewHolder holder, int i) {

        Categories categoryitem=arrayList.get(i);
        holder.t1.setText(categoryitem.getName());
        Picasso.get().load(categoryitem.getImage()).into(holder.i1);

    }

    public class categoryadapterViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;
        public ImageView i1;
        public categoryadapterViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.category_name);
           i1=(ImageView)itemView.findViewById(R.id.category_image);

        }
    }




}
