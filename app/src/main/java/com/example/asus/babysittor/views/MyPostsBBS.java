package com.example.asus.babysittor.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.babysittor.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyPostsBBS extends AppCompatActivity {
    String title;
    boolean isFirstPost=true;
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
                    CardView mm = findViewById(R.id.card_view);
                    LinearLayout LL = findViewById(R.id.LL);
                    RelativeLayout rl = findViewById(R.id.RL);
                    TextView titleTextView = findViewById(R.id.titleTextView);
                    TextView detailsTextView = findViewById(R.id.detailsTextView);

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {

                            String key = ds.getKey();
                            String description = ds.child("Description").getValue(String.class);
                            title = ds.child("Title").getValue(String.class);

                            if (isFirstPost==true)
                            {
                                titleTextView.setText(title);
                                detailsTextView.setText(description);
                                isFirstPost=false;
                            }
                            else
                            {

                                CardView cardView = new CardView(getApplicationContext());
                                getCardviewParam(cardView,mm);

                                RelativeLayout rm = new RelativeLayout(getApplicationContext());
                                rm.setLayoutParams(rl.getLayoutParams());


                                TextView textView1 = new TextView(getApplicationContext());
                                getTextviewParam(textView1,titleTextView,title);
                                TextView textView2 = new TextView(getApplicationContext());
                                getTextviewParam(textView1,detailsTextView,description);
                                textView1.setText(title);
                                textView2.setText(description);
                                cardView.addView(textView1);
                                cardView.addView(textView2);
                                LL.addView(cardView);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar.make(v, "Félicitation vous avez postulé a cette offre", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                });}


                    }


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
