package com.example.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class register extends AppCompatActivity {

    EditText username1;
    EditText password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s2_register);

        username1 = (EditText)findViewById(R.id.username1);
        password1 = (EditText)findViewById(R.id.password1);

        String name = username1.getText().toString();
        String password_string = password1.getText().toString();
    }


}