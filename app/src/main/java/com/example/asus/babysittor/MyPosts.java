package com.example.asus.babysittor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.UUID;

public class MyPosts extends AppCompatActivity {
    String title;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseApp.initializeApp(this);
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        Query query = db.child("Posts");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    CardView mm = findViewById(R.id.card_view1);
                    LinearLayout LL = findViewById(R.id.LL);
                    RelativeLayout rl = findViewById(R.id.rl);
                    TextView textView = findViewById(R.id.titleTextView1);

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (account.getId().equals(ds.child("userId").getValue(String.class)))
                        {
                            String key = ds.getKey();
                            String description = ds.child("Description").getValue(String.class);

                            CardView cardView = new CardView(getApplicationContext());
                            getCardviewParam(cardView,mm);

                        RelativeLayout rm = new RelativeLayout(getApplicationContext());
                        rm.setLayoutParams(rl.getLayoutParams());

                         title = ds.child("Title").getValue(String.class);
                        TextView textView1 = new TextView(getApplicationContext());
                        getTextviewParam(textView1,textView,title);
                        textView1.setText(title);
                        cardView.addView(textView1);
                        LL.addView(cardView);}

                    }
                  /*  CardView mm = findViewById(R.id.card_view1);
                    CardView cardView = new CardView(getApplicationContext());
                    getCardviewParam(cardView,mm);

                    RelativeLayout rl = findViewById(R.id.rl);
                    RelativeLayout rm = new RelativeLayout(getApplicationContext());
                    rm.setLayoutParams(rl.getLayoutParams());

                    ImageView img = findViewById(R.id.postImageView1);

                    ImageView img1 = new ImageView(getApplicationContext());
                    img1.setLayoutParams(img.getLayoutParams());
                    img1.setScaleType(img.getScaleType());
                    Random number = new Random();
                    img.setId(number.nextInt(2000));
                    rm.addView(img1);

                    cardView.addView(rm);

                    TextView textView = findViewById(R.id.titleTextView1);
                    TextView textView1 = new TextView(getApplicationContext());
                    textView1.setId(number.nextInt(2000));
                    getTextviewParam(textView1,textView,title);
                    rm.addView(textView1 ,textView.getLayoutParams());
                    LinearLayout LL = findViewById(R.id.LL);
                    LL.addView(cardView);*/

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getCardviewParam (CardView cardView,CardView mm){
        cardView.setLayoutParams(mm.getLayoutParams());
        cardView.setCardElevation(mm.getCardElevation());
        cardView.setCardBackgroundColor(mm.getCardBackgroundColor());
        cardView.setUseCompatPadding(mm.getUseCompatPadding());
    }

    public void getTextviewParam (TextView textView1,TextView textView,String string){
        textView1.setLayoutParams(textView.getLayoutParams());
        textView1.setPadding(textView.getPaddingLeft(),textView.getPaddingTop(),textView.getPaddingRight(),textView.getCompoundPaddingBottom());
        textView1.setTextAppearance(R.style.TextAppearance_AppCompat_Title);
        textView1.setText(string);
        textView1.setPadding(8,0,8,0);
        textView1.setEllipsize(textView.getEllipsize());
       // textView1.setMaxLines(2);
    }
}
