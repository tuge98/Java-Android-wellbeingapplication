package com.example.bookkeeping;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1_login);
        username = (EditText)findViewById(R.id.username_);
        password = (EditText)findViewById(R.id.password_);


    }
}

