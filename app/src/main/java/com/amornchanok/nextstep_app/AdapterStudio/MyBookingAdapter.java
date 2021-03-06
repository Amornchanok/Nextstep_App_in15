package com.amornchanok.nextstep_app.AdapterStudio;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amornchanok.nextstep_app.PaymentActivity;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebaseConnect.MyBooking;
import com.amornchanok.nextstep_app.userBottomNavigation.BookingActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyViewHolder> {
    private Context context;
    private List<MyBooking> uploadList;
    private onItemClickListener listener;
    Dialog myDialog;

    public MyBookingAdapter(Context context, List<MyBooking> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_booking,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nameTextView.setText(uploadList.get(position).getRoom_name());
        holder.text_date.setText(uploadList.get(position).getDate());
        holder.text_time.setText(uploadList.get(position).getTime());

        Picasso.get().load(uploadList.get(position).getRoom_img()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView nameTextView;
        TextView text_date;
        TextView text_time;
        Button btnDelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cardImageViewId);
            nameTextView = itemView.findViewById(R.id.cardTextViewId);
            text_date = itemView.findViewById(R.id.text_date);
            text_time = itemView.findViewById(R.id.text_time);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            myDialog = new Dialog(context);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ShowPopup();
                    myDialog.setContentView(R.layout.activity_popup_delete_booking);
                    btnDelete = (Button) myDialog.findViewById(R.id.btnDelete);
                    //Button = (Button) myDialog.findViewById(R.id)
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent partnerMode = new Intent(context, BookingActivity.class);
//                            context.startActivity(partnerMode);

//                            Intent myIntent1 = new Intent(this, B.class);
//                            myIntent1.putExtra("layout", R.layout.myLayout);
//                            startActivity(myIntent1);
                        }
                    });
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });

            itemView.setOnClickListener(this);
//            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }
        }

//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            menu.setHeaderTitle("Choose an action");
//            MenuItem doAnyTask=menu.add(Menu.NONE,1,1,"do any task");
//            MenuItem delete=menu.add(Menu.NONE,2,2,"delete");
//            doAnyTask.setOnMenuItemClickListener(this);
//            delete.setOnMenuItemClickListener(this);
//        }

//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            if (listener!=null){
//                int position=getAdapterPosition();
//                if (position!=RecyclerView.NO_POSITION){
//                    switch(item.getItemId()){
//                        case 1:
//                            listener.doAnuTask(position);
//                            return true;
//                        case 2:
//                            listener.delete(position);
//                            return true;
//                    }
//                }
//            }
//            return false;
//        }
    }

//    private void ShowPopup() {
//        myDialog.setContentView(R.layout.activity_popup_delete_booking);
//        btnDelete = (Button) myDialog.findViewById(R.id.btnDelete);
//        //Button = (Button) myDialog.findViewById(R.id)
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent partnerMode = new Intent(context, BookingActivity.class);
//                startActivity(partnerMode);
//            }
//        });
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }

    public  interface onItemClickListener{
        void onItemClick(int position);
        void doAnuTask(int position);
        void delete(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;
    }

}
