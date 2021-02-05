package com.amornchanok.nextstep_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.firebaseConnect.Rooms;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PartnerAddRoomActivity extends AppCompatActivity {

    EditText edtRoomName,edtRoomCapacity,edtPrice,edtStudioId;
    Button btSaveRoomNext;

    FirebaseDatabase database;
    DatabaseReference reference;
    Rooms rooms;
    int idObj = 0;
    int idRoom = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add_room);

        edtStudioId = findViewById(R.id.edtStudioId);
        edtRoomName = findViewById(R.id.edtRoomName);
        edtRoomCapacity = findViewById(R.id.edtRoomCapacity);
        edtPrice = findViewById(R.id.edtPrice);
        btSaveRoomNext = findViewById(R.id.btSaveRoomNext);

        rooms = new Rooms();
        reference = database.getInstance().getReference().child("Rooms");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    idObj = (int) snapshot.getChildrenCount();
                    idRoom = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btSaveRoomNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtStudioId = (EditText) findViewById(R.id.edtStudioId); // ไอดีของสตูดิโอที่โยงห้องไปอยู่ในสตูดิโอนั้น
                edtRoomName = (EditText) findViewById(R.id.edtRoomName);
                edtRoomCapacity = (EditText) findViewById(R.id.edtRoomCapacity);
                edtPrice = (EditText) findViewById(R.id.edtPrice);
//                String numberAsString = Integer.toString(idRoom);


                rooms.setStudioId(edtStudioId.getText().toString().trim());

                if (edtStudioId == edtStudioId) {
                    rooms.setID(String.valueOf(idRoom).toString().trim()); //ซ้อนอยู่ใน idObj เป็นไอดีห้อง
                }
//                rooms.setID(String.valueOf((idRoom-idRoom)+1).toString().trim()); //ซ้อนอยู่ใน idObj เป็นไอดีห้อง
                rooms.setName(edtRoomName.getText().toString().trim());
                rooms.setCapacity(edtRoomCapacity.getText().toString().trim());
                rooms.setPrice(edtPrice.getText().toString().trim());


                Toast.makeText(PartnerAddRoomActivity.this,"บันทึกข้อมูลสตูดิโอแล้ว!",Toast.LENGTH_LONG).show();
//                reference.child(String.valueOf(idObj)).child(String.valueOf(idRoom)).setValue(rooms);
                reference.child(String.valueOf(idObj)).setValue(rooms);

//                Intent intentManage = new Intent(getApplicationContext(), PartnerManageActivity.class);
//                startActivity(intentManage);

            }
        });
    }
}
