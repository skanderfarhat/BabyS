package com.example.asus.babysittor;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Posting extends AppCompatActivity {
    ImageView imageView;
    EditText Title;
    EditText Description;
    //private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        imageView = findViewById(R.id.imageView);
        Title = findViewById(R.id.titleEditText);
        Description = findViewById(R.id.descriptionEditText);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        mDatabase = FirebaseDatabase.getInstance().getReference();
  //      FirebaseApp.initializeApp(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Title.getText().toString().isEmpty())
                {
                    Snackbar.make(view, "Vous avez oublier de saisir un titre", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if (Description.getText().toString().isEmpty())
                {
                    Snackbar.make(view, "Vous avez oublier de saisir une description", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                  /*  DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("User").child("Hello");

                    ref.child("userId").setValue("1");
                    ref.child("Title").setValue(Title.getText().toString());
                    ref.child("Description").setValue(Description.getText().toString());*/
                    Snackbar.make(view," Votre annonce a été crée avec succés", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
