package com.example.asus.babysittor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

public class LoginActivity extends AppCompatActivity {

    Switch switch1 = findViewById(R.id.rectangle_switch);
    Switch switch2=  findViewById(R.id.round_switch);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}