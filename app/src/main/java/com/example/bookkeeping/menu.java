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
    String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_menu);
        // Get username from previous intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                currentUsername= null;
            } else {
                currentUsername= extras.getString("username");
            }
        } else {
            currentUsername= (String) savedInstanceState.getSerializable("username");
        }

        System.out.println("tässä on kayttaja: " + currentUsername);



    }

    // Function to switch screen to carbon footprint calculation screen
    public void switchtoCFP(View v){
        startActivity(new Intent(menu.this, calculatecarbonfootPrint.class));
    }

    public void switchToWeightScreen(View v) {
        System.out.println(currentUsername);
        startActivity(new Intent(menu.this, WeightScreen.class).putExtra("username", currentUsername));
    }
}


//Menu class