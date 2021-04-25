package com.example.bookkeeping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import static android.icu.text.Normalizer.NO;
import static java.lang.Math.round;


public class menu extends AppCompatActivity {
    String currentUsername;
    List<Weight> w;
    List<UserInfo> uinfo;
    TextView alertMSG;
    TextView showAge;
    TextView showHeight;
    TextView showbmi;
    int age = 0;
    int height = 0;
    double weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_menu);
        alertMSG = (TextView) findViewById(R.id.alertMSG);
        showAge = (TextView) findViewById(R.id.ShowAge);
        showHeight = (TextView) findViewById(R.id.heightShow);
        showbmi = (TextView) findViewById(R.id.showBMI);
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
        showUserInfo();


    }

    public void showUserInfo() {
        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();

        Runnable task = () -> {

            UserAndInfo uinfo = dao.getUserAndInfo(currentUsername);

            UserInfo userinfo = uinfo.userinfo;
            try {
                age = userinfo.getAge();
                height = userinfo.getHeight();
                List<UserWithWeights> lista = dao.getUserWithWeights(currentUsername);
                w = lista.get(0).allWeights;
                weight = w.get(0).getWeight();
            } catch (Exception e) {
                System.out.println("No db infomation");
            }

            if ((age == 0) || (height == 0)) {
                alertMSG.setText("You have not inserted your age and height");
            } else {
                System.out.println("Ika" + age + "::" + height);
                showHeight.setText(String.valueOf(height));
                showAge.setText(String.valueOf(age));
                double bmi = bmiCalculator(height, weight);
                showbmi.setText(String.valueOf(round(bmi)));
            }

        };

        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();

    }

    public double bmiCalculator(int height, double weight) {
        System.out.println(weight + ":::" + height);
        return weight / ((height/100.0) * (height/100.0)) ;
    }

    // Function to switch screen to carbon footprint calculation screen
    public void switchtoCFP(View v){
        startActivity(new Intent(menu.this, calculatecarbonfootPrint.class));
    }

    public void switchToWeightScreen(View v) {
        startActivity(new Intent(menu.this, WeightScreen.class).putExtra("username", currentUsername));
    }

    public void switchToInsertInfoScreen(View v) {
        startActivity(new Intent(menu.this, InsertInfoScreen.class).putExtra("username", currentUsername));
    }
}


//Menu class