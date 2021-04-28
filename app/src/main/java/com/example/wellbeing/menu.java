package com.example.wellbeing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static java.lang.Math.round;


public class menu extends AppCompatActivity {
    String currentUsername;
    List<Weight> w;
    //List<UserInfo> uinfo;
    TextView alertMSG;

    TextView showbmi;
    int height = 0;
    double weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_menu);
        alertMSG = (TextView) findViewById(R.id.alertMSG);

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


        showBMI();


    }
    // takes weight and height from DB and uses them for BMI
    public void showBMI() {
        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();

        Runnable task = () -> {

            UserAndInfo uinfo = dao.getUserAndInfo(currentUsername);

            UserInfo userinfo = uinfo.userinfo;
            try {

                height = userinfo.getHeight();
                List<UserWithWeights> list = dao.getUserWithWeights(currentUsername);
                w = list.get(list.size() - 1).allWeights;
                weight = w.get(0).getWeight();
            } catch (Exception e) {
                System.out.println("No db infomation");
            }

            if ((height == 0)) {
                alertMSG.setText("Insert weight and height to view BMI");
            } else {
                double bmi = bmiCalculator(height, weight);
                showbmi.setText(String.valueOf(round(bmi)));
            }

        };

        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();

    }
    //Calculates BMI
    public double bmiCalculator(int height, double weight) {
        System.out.println(weight + ":::" + height);
        return weight / ((height/100.0) * (height/100.0)) ;
    }

    // Functions to switch screen

    public void switchtoCFP(View v){
        startActivity(new Intent(menu.this, calculatecarbonfootPrint.class).putExtra("username", currentUsername));
    }

    public void switchToWeightScreen(View v) {
        startActivity(new Intent(menu.this, WeightScreen.class).putExtra("username", currentUsername));
    }

    public void switchToInsertInfoScreen(View v) {
        startActivity(new Intent(menu.this, InsertInfoScreen.class).putExtra("username", currentUsername));
    }

    public void switchToInsertNutritionInfo(View v) {
        startActivity(new Intent(menu.this, NutritionInfo.class).putExtra("username", currentUsername));
    }
    public void switchToAddUserInfo(View v) {
        startActivity(new Intent(menu.this, AddUserInfo.class).putExtra("username", currentUsername));
    }

}


