package com.example.wellbeing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class calculatecarbonfootPrint extends AppCompatActivity {
    jsonrequests jsoni = new jsonrequests();
    List<Double> jsonlist2;
    String currentUsername;

    EditText editbeefLevel, editfishLevel, editporkpoultryLevel, editdairyLevel, editcheeseLevel,editriceLevel, editeggLevel, editwintersaladLevel, editrestaurantLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s4_carbonfootprint);

        editbeefLevel = findViewById(R.id.editbeefLevel);
        editfishLevel = findViewById(R.id.editfishLevel);
        editporkpoultryLevel = findViewById(R.id.editporkpoultryLevel);
        editdairyLevel = findViewById(R.id.editdairyLevel);
        editcheeseLevel = findViewById(R.id.editcheeseLevel);
        editriceLevel = findViewById(R.id.editriceLevel);
        editwintersaladLevel = findViewById(R.id.editwintersaladLevel);
        editrestaurantLevel = findViewById(R.id.editrestaurantLevel);
        editeggLevel = findViewById(R.id.editeggLevel);
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


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //jsoni.readJSON();

    }
    // Function to take user inputs and send API request
    public void calculateCFBLEVEL(View w) {
        String beef_val = editbeefLevel.getText().toString();
        String fish_val = editfishLevel.getText().toString();
        String pork_val = editporkpoultryLevel.getText().toString();
        String dairy_val = editdairyLevel.getText().toString();
        String cheese_val = editcheeseLevel.getText().toString();
        String rice_val = editriceLevel.getText().toString();
        String wintersalad_val = editwintersaladLevel.getText().toString();
        String restaurant_val = editrestaurantLevel.getText().toString();
        String egg_val = editeggLevel.getText().toString();

        String new_url = String.format("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore&query.beefLevel=%1$s&query.fishLevel=%2$s&query.porkPoultryLevel=%3$s&query.dairyLevel=%4$s&query.cheeseLevel=%5$s&query.riceLevel=%6$s&query.eggLevel=%7$s&query.winterSaladLevel=%8$s&query.restaurantSpending=%9$s",beef_val,fish_val,pork_val,dairy_val,cheese_val,rice_val,egg_val,wintersalad_val,restaurant_val);
        System.out.println(new_url);
        jsonlist2 = jsoni.readJSON(new_url);

/*
        for(int i=0; i < jsonlist2.size(); i++){
            System.out.println(jsonlist2.get(i));
            System.out.println("moi");

        }*/

    }
    // Switch to Summary of carbon footprint information
    public void checkcarbonfootprintSummary(View w) {
       // Intent intent = new Intent(calculatecarbonfootPrint.this, carbonfootprintSummary.class);
        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();
        try {

            CarbonFootPrint carbonFootPrint = new CarbonFootPrint(currentUsername, jsonlist2.get(0),  jsonlist2.get(1), jsonlist2.get(2), jsonlist2.get(3), jsonlist2.get(4));
            Runnable task = () -> {
                dao.insertCFP(carbonFootPrint);
            };


            Thread thread = new Thread(task);
            thread.start();
            thread.interrupt();
            Intent intent = new Intent(calculatecarbonfootPrint.this, carbonfootprintSummary.class);
            //Bundle b = new Bundle();
            intent.putExtra("Dairy", jsonlist2.get(0));
            intent.putExtra("Meat", jsonlist2.get(1));
            intent.putExtra("Plant", jsonlist2.get(2));
            intent.putExtra("Restaurant", jsonlist2.get(3));
            intent.putExtra("Total", jsonlist2.get(4));
            startActivity(intent);

        } catch (Exception e) {
            System.err.println("Failed to add information to database");
        }
    }
}