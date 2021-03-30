package com.example.bookkeeping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import org.json.JSONException;

import java.io.IOException;


public class menu extends AppCompatActivity {
    jsonrequests jsoni = new jsonrequests();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_menu);

        //modifying strick policies to permit all
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        jsoni.readJSON();



    }
}


//Menu class