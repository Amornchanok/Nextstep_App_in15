package com.amornchanok.nextstep_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Page_firebase_show_img_100 extends AppCompatActivity {

    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    List<Upload>uploadList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_firebase_show_img_100);

        recyclerView=findViewById(R.id.recyclerviewId);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.imageProgressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploadList=new ArrayList<>();

        firebaseStorage=FirebaseStorage.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("Studios");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //duplicate value remove
                uploadList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    assert upload != null;
                    upload.setKey(dataSnapshot.getKey());
                    uploadList.add(upload);
                }
                myAdapter=new MyAdapter(Page_firebase_show_img_100.this,uploadList);
                recyclerView.setAdapter(myAdapter);

                myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String s_key = uploadList.get(position).getKey();
                        String s_name = uploadList.get(position).getI_name();
                        String s_url = uploadList.get(position).getI_url();

                        Toast.makeText(Page_firebase_show_img_100.this, s_key, Toast.LENGTH_SHORT).show();

                        Intent newActivity = new Intent(Page_firebase_show_img_100.this, Page_detail.class);
                        newActivity.putExtra("s_key",s_key);
                        newActivity.putExtra("s_name",s_name);
                        newActivity.putExtra("s_url",s_url);
                        startActivity(newActivity);

                    }

                    @Override
                    public void doAnuTask(int position) {
                        Toast.makeText(Page_firebase_show_img_100.this, "do any task selected"+position, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void delete(int position) {
                        //get for selected item position
                        Upload selectItem=uploadList.get(position);

                        //get for selected item key
                        final String key=selectItem.getKey();

                        StorageReference storageReference=firebaseStorage.getReferenceFromUrl(selectItem.getI_url());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                databaseReference.child(key).removeValue();
                            }
                        });
                    }
                });





                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Page_firebase_show_img_100.this, "Error: "+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
