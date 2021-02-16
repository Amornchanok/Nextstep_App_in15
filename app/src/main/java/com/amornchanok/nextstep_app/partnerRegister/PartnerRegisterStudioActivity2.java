package com.amornchanok.nextstep_app.partnerRegister;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.firebaseConnect.Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebaseConnect.Upload;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;


public class PartnerRegisterStudioActivity2 extends AppCompatActivity  {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText edtStudioName,edtTimeOpen,edtTimeClose;
    CheckBox cbAir,cbSpeaker,cbToilet,cbInternet,cbCarpark,cbOther;
    TextView tvChooseLoc;
    Spinner spLocation,spTimeOpen,spTimeClose;
    Button btSaveInfoStudio,btChooseImage;

    ProgressBar progressBar;
    ImageView ivUploadView;
    Uri imageUri;
    private StorageReference storageRef;

    FirebaseDatabase database;
    DatabaseReference reference;
    Studios studios;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register_studio_2);

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
        storageRef = FirebaseStorage.getInstance().getReference().child("Studios");

        String cb1 = "แอร์";
        String cb2 = "ลำโพง";
        String cb3 = "ห้องน้ำ";
        String cb4 = "อินเทอร์เน็ต";
        String cb5 = "ลานจอดรถ";
        String cb6 = "อื่นๆ";

        btChooseImage = findViewById(R.id.btChooseImage);
        ivUploadView = findViewById(R.id.ivUploadView);
        progressBar = findViewById(R.id.progressBar);

        btChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChoose();
            }
        });



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

                uploadeFile();

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

                
                Toast.makeText(PartnerRegisterStudioActivity2.this,"บันทึกข้อมูลสตูดิโอแล้ว!",Toast.LENGTH_LONG).show();
                reference.child(String.valueOf(id)).setValue(studios);

                Intent intentOtp = new Intent(getApplicationContext(), PartnerRegisterOtpActivity.class);
                startActivity(intentOtp);

            }
        });
    }
    private void openFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

//            Picasso.with(this).load(imageUri).into(ivUploadView);
            Glide.with(this).load(imageUri).into(ivUploadView);
            ivUploadView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentRes = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentRes.getType(uri));
    }

    private void uploadeFile() {
        if (imageUri != null) {
            StorageReference fileReference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressBar.setProgress(0);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            },5000);

                            Toast.makeText(PartnerRegisterStudioActivity2.this, "อัปโหลดแล้ว!",Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload();
                            String uploadId = reference.push().getKey();
                            reference.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PartnerRegisterStudioActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this,"ยังไม่ได้เลือกไฟล์",Toast.LENGTH_SHORT).show();
        }
    }
}
