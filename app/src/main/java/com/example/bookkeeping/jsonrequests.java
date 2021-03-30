package com.example.bookkeeping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class jsonrequests{

    //method to readJSON objects
    public void readJSON(){
        String json = getJSON();
        System.out.println("JSON: "+ json);

        if(json != null){
            try{
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i < jsonArray.length();i++){
                    JSONObject jobject = jsonArray.getJSONObject(i);
                    System.out.println("#######"+(i+1)+"####¤#");
                    System.out.println(jobject.getBoolean("Total"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //performs a get request to ilmastodieetti json api
    public String getJSON() {
        String response = null;
        try {
            URL url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore&query.beefLevel=24&query.fishLevel=24&query.porkPoultryLevel=24&query.dairyLevel=24&query.cheeseLevel=24&query.riceLevel=24&query.eggLevel=24&query.winterSaladLevel=24&query.restaurantSpending=24");
            HttpURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();
            br.close();

        } catch (
                ProtocolException e) {
            e.printStackTrace();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            System.out.println("Getjson toimii");
        }

        return response;

    }
}

