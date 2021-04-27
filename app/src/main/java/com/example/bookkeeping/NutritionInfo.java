package com.example.bookkeeping;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NutritionInfo extends AppCompatActivity {
    jsonrequests jsoni = new jsonrequests();

    EditText fruit;

    TextView name;
    TextView cal;
    TextView carb;
    TextView prot;
    TextView fat;
    TextView sugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s8_nutritioninfo);


        fruit = findViewById(R.id.fruitsearch);
        name = findViewById(R.id.textView18);
        cal = findViewById(R.id.textView19);
        carb = findViewById(R.id.textView20);
        prot = findViewById(R.id.textView21);
        fat = findViewById(R.id.textView22);
        sugar = findViewById(R.id.textView23);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }

    public void getInfo(View v) {

        Snackbar fruiNotFound = Snackbar.make(v, "Cannot find fruit, try something else.", BaseTransientBottomBar.LENGTH_SHORT);
        String url = "https://www.fruityvice.com/api/fruit/all";
        try {
            List<String> list = jsoni.read2JSON(url, fruit.getText().toString());
            System.out.println("fruit: " + list);
            name.setText(list.get(0));
            cal.setText(list.get(1));
            carb.setText(list.get(2));
            prot.setText(list.get(3));
            fat.setText(list.get(4));
            sugar.setText(list.get(5));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Fruit not found, keyword was not matched.");
            fruiNotFound.show();
        }


    }
}
