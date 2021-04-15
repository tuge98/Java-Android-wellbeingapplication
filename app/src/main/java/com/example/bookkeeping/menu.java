package com.example.bookkeeping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;


public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_menu);

        //modifying strick policies to permit all




    }



    public void switchtoCFP(View v){
        startActivity(new Intent(menu.this, calculatecarbonfootPrint.class));
    }
}


//Menu class