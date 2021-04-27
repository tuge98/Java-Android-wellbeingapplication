package com.example.bookkeeping;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.round;

public class AddUserInfo extends AppCompatActivity {

    EditText editage;
    EditText editheight;
    EditText editweight;
    String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s9_userinfo);

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
        editage = findViewById(R.id.editAge);
        editheight = findViewById(R.id.editHeight);
        editweight = findViewById(R.id.editWeight);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToDB(View v) {
        LocalDate today = LocalDate.now();
        String dateAsStr = today.toString();
        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();

        Runnable task = () -> {

            try {
                Double insertedWeight = Double.parseDouble(editweight.getText().toString());
                Weight weightInfo = new Weight(currentUsername, dateAsStr, insertedWeight);
                int insertedheight = Integer.parseInt(editheight.getText().toString());
                int insertedAge = Integer.parseInt(editage.getText().toString());
                UserInfo userinfo = new UserInfo(currentUsername, insertedheight, insertedAge);
                dao.insertWeight(weightInfo);
                dao.insertUserInfo(userinfo);
                Snackbar dbSuccess = Snackbar.make(v, "Information added", BaseTransientBottomBar.LENGTH_SHORT);
                dbSuccess.show();
            } catch (Exception e) {
                System.out.println("No db infomation");
                Snackbar dbFail = Snackbar.make(v, "Adding information failed, do not leave input fields empty", BaseTransientBottomBar.LENGTH_SHORT);
                dbFail.show();
            }

        };

        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();

    }

    public void backToMenu(View v) {
        startActivity(new Intent(AddUserInfo.this, menu.class).putExtra("username", currentUsername));
    }




}