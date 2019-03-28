package com.example.asus.babysittor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.example.asus.babysittor.Constants;

import static android.support.constraint.Constraints.TAG;

public class ProfileSetting extends Activity {

    private EditText nameEditText;
    protected ImageView imageView;
    private ProgressBar avatarProgressBar;

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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);
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

