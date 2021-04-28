package com.example.wellbeing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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



        final String usernametocheck = username.getText().toString();
        final String passwordtocheck = password.getText().toString();


       
        if (usernametocheck.isEmpty() || passwordtocheck.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username and password", Toast.LENGTH_SHORT).show();

        } else {
            UserDao dao = UserDB.getInstance(this).userDao();

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {


                    User user = dao.login(usernametocheck);


                    try {
                        String passwordconverter = user.getHash();
                        String test_final_username = user.getUsername();
                        byte[] byteconverter = user.getSalt();
                        String test_final_password = hashingClass.getSecurePassword(passwordtocheck, byteconverter);
                        if(usernametocheck.equals(test_final_username) && passwordconverter.equals(test_final_password)){

                            startActivity(new Intent(login.this, menu.class).putExtra("username", usernametocheck));
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Invalid credentials.", Toast.LENGTH_SHORT).show();
                                }
                            });}
                    } catch (NullPointerException e) {
                            Snackbar failLogin = Snackbar.make(v, "Wrong password, try again.", BaseTransientBottomBar.LENGTH_SHORT);
                            failLogin.show();
                    }

                }
            }).start();
        }
    }

    //Function to move from login activity to register activity
    public void register(View v) {
        startActivity(new Intent(login.this, register.class));


    }
}

