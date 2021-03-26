package com.example.bookkeeping;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class login extends AppCompatActivity {
    Button loginButton;
    EditText username;
    EditText password;
    Button switchscreenbtn;
    javaHashing hashingClass = new javaHashing();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1_login);
        username = (EditText) findViewById(R.id.username_);
        password = (EditText) findViewById(R.id.password_);
        loginButton = findViewById(R.id.loginButton);
        switchscreenbtn = findViewById(R.id.switchscreenbtn);



    }

    // login credentials check from database
    public void checkLogin(View v) {
        //System.out.println("Login pressed");

        //String tempUsername = "qwerty";
        //String tempPassword = "asdf";


        final String usernametocheck = username.getText().toString();
        final String passwordtocheck = password.getText().toString();


        //System.out.println(password.getText().toString());
        if (usernametocheck.isEmpty() || passwordtocheck.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username and password", Toast.LENGTH_SHORT).show();

        } else {
            userdatabase userdatabase = com.example.bookkeeping.userdatabase.getuserDatabase(getApplicationContext());
            final dbinterface dbinterface = userdatabase.dbinterface();
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {

                    //String s = "23fab0548f9418bc129e333a64620dbf476f2e1dc2956a2ba4877fbebdadd162764c5004e78760add0fd7d209e2c9b843f15ded1be1d3f2c90a2a5bf377cff1c";
                    userdetails userdetails = dbinterface.login(usernametocheck);//passwordtocheck
                    //dbinterface.deleteAll();
                    System.out.println("get "+userdetails.getPassword());
                    System.out.println("get "+userdetails.getUsername());
                    //System.out.println(userdetails.getuserIDRIVI());
                    String passwordconverter = userdetails.getPassword();
                    byte[] byteconverter = userdetails.getuserIDRIVI();
                    //byte[] convert_byte = byteconverter.getBytes(StandardCharsets.UTF_8);
                    System.out.println(byteconverter.toString());

                    String test_final_password = hashingClass.getSecurePassword(passwordtocheck, byteconverter);
                    System.out.println("final "+test_final_password);
                    System.out.println("suola db"+userdetails.getuserIDRIVI().toString());
                    System.out.println(userdetails.getUsername());
                    String test_final_username = userdetails.getUsername();



                    System.out.println("käyttäjän syöttämä nimi "+usernametocheck);
                    System.out.println("käyttäjän syöttämä salasana "+test_final_password);
                    System.out.println("database "+ passwordconverter);
                    System.out.println("database "+ test_final_username);


                    if(usernametocheck.equals(test_final_username) && passwordconverter.equals(test_final_password)){

                            startActivity(new Intent(login.this, menu.class));
                    }else{
                    runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Invalid credentials.", Toast.LENGTH_SHORT).show();

                            }
                        });}
                   /* if (userdetails == null) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Invalid credentials.", Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {

                        //Intent intent = new Intent(this, menu.class);
                        startActivity(new Intent(login.this, menu.class));

                    }*/
                }

            }).start();

        }

    }

    //Function to move from login activity to register activity
    public void register(View v) {
        startActivity(new Intent(login.this, register.class));


    }

}

