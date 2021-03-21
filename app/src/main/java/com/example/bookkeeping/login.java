package com.example.bookkeeping;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    Button loginButton;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1_login);
        username = (EditText)findViewById(R.id.username_);
        password = (EditText)findViewById(R.id.password_);
        loginButton = findViewById(R.id.loginButton);

    }

    // login credentials check
    public void checkLogin(View v) {
        System.out.println("Login pressed");

        String tempUsername = "qwerty";
        String tempPassword = "asdf";


        System.out.println(password.getText().toString());
         if ((username.getText().toString().equals(tempUsername)) & (password.getText().toString().equals(tempPassword))) {
            System.out.println("Sisään");
        }

    }
}

