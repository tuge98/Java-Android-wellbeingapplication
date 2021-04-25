package com.example.bookkeeping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class InsertInfoScreen extends AppCompatActivity {
    String currentUsername;
    EditText heightIn;
    EditText ageIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s7_insertinfo);

        heightIn = (EditText) findViewById(R.id.HeightIn);
        ageIn = (EditText) findViewById(R.id.AgeIn);

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

    }

    public void insertInfo(View v) {
        if ((heightIn.getText().toString() != null) || (ageIn.getText().toString() != null)) {
            UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();
            UserInfo userInfo = new UserInfo(currentUsername, Integer.parseInt(heightIn.getText().toString()), Integer.parseInt(ageIn.getText().toString()));
            Runnable task = () -> {
                System.out.println("Inserting to DB:" + currentUsername + ", Height: " + heightIn + ", Age: " + ageIn);
                dao.insertUserInfo(userInfo);
            };


            Thread thread = new Thread(task);
            thread.start();
            thread.interrupt();
            Snackbar validInfo = Snackbar.make(v, "Height and age added successfully", BaseTransientBottomBar.LENGTH_SHORT);
            validInfo.show();
        } else {
            Snackbar invalidInfo = Snackbar.make(v, "Please fill your age and height", BaseTransientBottomBar.LENGTH_SHORT);
            invalidInfo.show();
        }
    }

    public void backToMenu(View v) {
        startActivity(new Intent(InsertInfoScreen.this, menu.class).putExtra("username", currentUsername));
    }
}