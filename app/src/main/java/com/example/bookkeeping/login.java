package com.example.bookkeeping;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button loginButton;
    EditText username;
    EditText password;
    Button switchscreenbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1_login);
        username = (EditText) findViewById(R.id.username_);
        password = (EditText) findViewById(R.id.password_);
        loginButton = findViewById(R.id.loginButton);
        switchscreenbtn = findViewById(R.id.switchscreenbtn);


    }

    // login credentials check
    public void checkLogin(View v) {
        System.out.println("Login pressed");

        String tempUsername = "qwerty";
        String tempPassword = "asdf";

        final String usernametocheck = username.getText().toString();
        final String passwordtocheck = password.getText().toString();


        System.out.println(password.getText().toString());
        if (usernametocheck.isEmpty() || passwordtocheck.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username and password", Toast.LENGTH_SHORT).show();

        } else {
            userdatabase userdatabase = com.example.bookkeeping.userdatabase.getuserDatabase(getApplicationContext());
            final dbinterface dbinterface = userdatabase.dbinterface();
            new Thread(new Runnable() {
                @Override
                public void run() {


                    userdetails userdetails = dbinterface.login(usernametocheck, passwordtocheck);
                    if (userdetails == null) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Invalid credentials.", Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {

                        //Intent intent = new Intent(this, menu.class);
                        startActivity(new Intent(login.this, menu.class));

                    }
                }

            }).start();

        }

    }
    public void register(View v) {
        startActivity(new Intent(login.this, register.class));


    }

}

