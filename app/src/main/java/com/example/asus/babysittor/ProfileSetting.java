package com.example.asus.babysittor;

import android.Manifest;
import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.theartofdev.edmodo.cropper.CropImage;

import static android.support.constraint.Constraints.TAG;

public class ProfileSetting extends Activity {

    private EditText nameEditText;
    protected ImageView imageView;
    private ProgressBar avatarProgressBar;
    private DatabaseReference mDatabase;

    private static final int SELECT_PICTURE1 = 100;
    ImageView ImgToUpload;
    LinearLayout ThisLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        avatarProgressBar = findViewById(R.id.avatarProgressBar);
        imageView = findViewById(R.id.imageView);
        nameEditText = findViewById(R.id.nameEditText);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseApp.initializeApp(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);
            }
        });
        Intent intent = getIntent();
        String personName = intent.getStringExtra("personName");
        String personId = intent.getStringExtra("personId");
        String ProfileSetted = intent.getStringExtra("ProfileSetted");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("User").child(personId);
        nameEditText.setText(personId);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        Query query = db.child("User").child(personId).child("ProfileSetted");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    if(dataSnapshot.getValue().toString()=="true"){
                        Intent intent = new Intent(ProfileSetting.this, Posting.class);
                        startActivityForResult(intent, 1);
                    }
                }
            }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = selectedImageUri.getPath();
                    ImgToUpload.setImageURI(selectedImageUri);
                    ImgToUpload.setBackground(null);
                    Log.e("image path", path + "");
                }
        }
    }

    public void onSelectImageClick(View view) {
        if (CropImage.isExplicitCameraPermissionRequired(this)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            CropImage.startPickImageActivity(this);
        }
    }


}

