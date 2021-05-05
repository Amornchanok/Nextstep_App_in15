package com.amornchanok.nextstep_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.firebaseConnect.PartnerPhoto;
import com.amornchanok.nextstep_app.partnerRegister.PartnerRegisterStudioActivity;
import com.amornchanok.nextstep_app.partnerRegister.PartnerRegisterStudioActivity2;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import javax.annotation.Nullable;

public class PartnerVerifyPhotoIdActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView imageViewAdd;
    private Button btnUpload;

    private String type;
    PartnerPhoto partnerPhoto;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageRef;



    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_verify_photo_id);

            imageViewAdd = findViewById(R.id.ImageViewAdd);
            btnUpload = findViewById(R.id.btnUpload);

            dataRef = FirebaseDatabase.getInstance().getReference().child("Photo");
            storageRef = FirebaseStorage.getInstance().getReference().child("PartnerVerifyPhotoID");


            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isImageAdded!=false)
                    {
                        uploadImage();

                    }
                }
            });





            //อัปโหลดรูป
            imageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    selectImage();
                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,REQUEST_CODE_IMAGE);
                }
            });
        }

        private void uploadImage() {

            final String key=dataRef.push().getKey();
            storageRef.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap hashMap=new HashMap();
                            // hashMap.put("typeTattoo",type);
                            hashMap.put("imageUrl",uri.toString());

                            dataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent=new Intent(PartnerVerifyPhotoIdActivity.this, PartnerRegisterStudioActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(PartnerVerifyPhotoIdActivity.this,"บันทึกรูปบัตรประชาชนเรียบร้อย",Toast.LENGTH_LONG).show();
                                }


                            });
                        }
                    });
                }
            });
        }
//
//    private void selectImage() {
//
//        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//
//            @Override
//
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Take Photo")) {
//                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivityForResult(takePictureIntent, 1);
//                    }
//
//                } else if (options[item].equals("Choose from Gallery")) {
//                    Intent intent = new Intent();
//                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intent, 2);
//
//
//                } else if (options[item].equals("Cancel")) {
//
//                    dialog.dismiss();
//                }
//            }
//        });
//
//        builder.show();
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Uri imageUri;
//        if(requestCode==1 && resultCode==RESULT_OK)
//        {
//            //Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageViewAdd.setImageBitmap(bitmap);
//            isImageAdded = true;
//        }
//
//        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
//            imageUri = data.getData();
//            isImageAdded = true;
//            imageViewAdd.setImageURI(imageUri);
//        }
//    }


    ///////////////////////////////


        private void ShowAlertSuccess() {
            LayoutInflater myInflater = LayoutInflater.from(this);
//            View view = myInflater.inflate(R.layout.dialog_noti_success, null);
//            Toast mytoast = new Toast(this);
//            mytoast.setView(view);
//            mytoast.setGravity(Gravity.CENTER_VERTICAL,0,0);
//            mytoast.setDuration(Toast.LENGTH_LONG);
//            mytoast.show();

        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==REQUEST_CODE_IMAGE && data!=null)
            {
                imageUri=data.getData();
                isImageAdded = true;
                imageViewAdd.setImageURI(imageUri);
            }
        }

}