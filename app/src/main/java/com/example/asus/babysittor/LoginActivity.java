package com.example.asus.babysittor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

public class LoginActivity extends AppCompatActivity {

    Switch  switch1;
    Switch switch2;
    Button Login;
    Intent StartProfileSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        switch1 =findViewById(R.id.family_switch);
        switch2=  findViewById(R.id.bbs_switch);
        Login = findViewById(R.id.login_button);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked())
                    switch2.setChecked(false);
                else
                    switch2.setChecked(true);
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch2.isChecked())
                    switch1.setChecked(false);
                else
                    switch1.setChecked(true);
            }
        });
        StartProfileSetting = new Intent(this,ProfileSetting.class);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(StartProfileSetting);
            }
        });
    }


}