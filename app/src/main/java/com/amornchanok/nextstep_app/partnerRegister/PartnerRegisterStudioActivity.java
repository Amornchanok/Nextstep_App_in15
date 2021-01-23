package com.amornchanok.nextstep_app.partnerRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.firebaseStudio.Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebaseStudio.Studios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PartnerRegisterStudioActivity extends AppCompatActivity  {

    EditText edtStudioName,edtTimeOpen,edtTimeClose;
    CheckBox cbAir,cbSpeaker,cbToilet,cbInternet,cbCarpark,cbOther;
    TextView tvChooseLoc;
    Spinner spLocation,spTimeOpen,spTimeClose;
    Button btSaveInfoStudio;
    FirebaseDatabase database;
    DatabaseReference reference;
    Studios studios;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register_studio);

        tvChooseLoc = findViewById(R.id.tvChooseLoc);
        edtTimeOpen = findViewById(R.id.edtTimeOpen);
        edtTimeClose = findViewById(R.id.edtTimeClose);
        spLocation = findViewById(R.id.spLocation);
        btSaveInfoStudio = findViewById(R.id.btSaveInfoStudio);
        studios = new Studios();
        cbAir = findViewById(R.id.cbAir);
        cbSpeaker = findViewById(R.id.cbSpeaker);
        cbToilet= findViewById(R.id.cbToilet);
        cbInternet = findViewById(R.id.cbInternet);
        cbCarpark = findViewById(R.id.cbCarpark);
        cbOther = findViewById(R.id.cbOther);
        reference = database.getInstance().getReference().child("Studios");

        String cb1 = "แอร์";
        String cb2 = "ลำโพง";
        String cb3 = "ห้องน้ำ";
        String cb4 = "อินเทอร์เน็ต";
        String cb5 = "ลานจอดรถ";
        String cb6 = "อื่นๆ";
        
//      Start  Spinner Locations

        List<String> Locations = new ArrayList<>();
        Locations.add(0,"เลือกที่ตั้ง");
        Locations.add("พญาไท");
        Locations.add("ลาดพร้าว");
        Locations.add("ราชเทวี");
        Locations.add("เอกมัย");
        Locations.add("รัชดา");
        Locations.add("ปทุมวัน");
        Locations.add("มีนบุรี");
        Locations.add("หลักสี่");
        Locations.add("จตุจักร");
        Locations.add("บางซื่อ");
        Locations.add("พระราม 2");
        Locations.add("บางแค");
        Locations.add("วัฒนา");
        Locations.add("บางเขน");
        Locations.add("บางพลัด");
        Locations.add("ห้วยขวาง");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Locations);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spLocation.setAdapter(dataAdapter);

        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (adapterView.getItemAtPosition(position).equals("เลือกที่ตั้ง")) {

                }else {
                    tvChooseLoc.setText(adapterView.getSelectedItem().toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//      End  Spinner Locations


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    id = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btSaveInfoStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtStudioName = (EditText) findViewById(R.id.edtStudioName);
                String numberAsString = Integer.toString(id);

                studios.setID(String.valueOf(id).toString().trim());
                studios.setLocation(spLocation.getSelectedItem().toString());
                studios.setName(edtStudioName.getText().toString().trim());
                studios.setTimeOpen(edtTimeOpen.getText().toString().trim());
                studios.setTimeClose(edtTimeClose.getText().toString().trim());

//                if (cb1.isChecked()) {
//
//                } else {
//                    studios.setCbAir(cb1);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                }
//                if (cb2.isChecked()) {
//                    studios.setCbSpeaker(cb2);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                } else {
//
//                }
//                if (cb3.isChecked()) {
//                    studios.setCbToilet(cb3);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                } else {
//
//                }
//
//                if (cb4.isChecked()) {
//                    studios.setCbInternet(cb4);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                } else {
//
//                }
//
//                if (cb5.isChecked()) {
//                    studios.setCbCarpark(cb5);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                } else {
//
//                }
//
//                if (cb6.isChecked()) {
//                    studios.setCbOther(cb6);
//                    reference.child(String.valueOf(id)).child("conveniences").setValue(studios);
//                }

                
                Toast.makeText(PartnerRegisterStudioActivity.this,"บันทึกข้อมูลสตูดิโอแล้ว!",Toast.LENGTH_LONG).show();
                reference.child(String.valueOf(id)).setValue(studios);

                Intent intentOtp = new Intent(getApplicationContext(), PartnerRegisterOtpActivity.class);
                startActivity(intentOtp);

            }
        });
    }
}
