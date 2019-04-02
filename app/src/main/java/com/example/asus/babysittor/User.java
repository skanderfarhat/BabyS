package com.example.asus.babysittor;

import android.net.Uri;

public class User {

        public String username;
        public String email;
        public  String personId;
        public Uri personPhoto;

    public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email, String personId, Uri personPhoto ) {
            this.username = username;
            this.email = email;
            this.personId = personId;
            this.personPhoto=personPhoto;
        }



}
