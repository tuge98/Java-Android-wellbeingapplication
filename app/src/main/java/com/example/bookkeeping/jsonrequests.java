package com.example.bookkeeping;

import android.util.JsonReader;



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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class jsonrequests{



    //method to readJSON objects
    public List<Double> readJSON(String urli){
        String json = getJSON(urli);
        //System.out.println("JSON: "+ json);

        ArrayList<Double> jsonlist = new ArrayList<Double>();

        if(json != null){
            try{
                //JSONArray jsonArray = new JSONArray(json);
                JSONObject jsonObject = new JSONObject(json);
                //JSONArray jsonArray = jsonObject.getJSONArray("Total");
                for(int i = 0; i <jsonObject.names().length() ; i++){
                    //JSONObject jobject = jsonArray.getJSONObject(i);
                    //JSONObject jobject = jsonArray.getJSONObject(i);
                    //System.out.println("#######"+(i+1)+"####¤#");
                    //System.out.println(jobject.getBoolean("Total"));
                    //jsonlist.add(jobject.toString());
                    //System.out.println(jsonObject.get(jsonObject.names().getString(i)));
                    jsonlist.add((Double) jsonObject.get(jsonObject.names().getString(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonlist;
    }
    //performs a get request to json api
    public String getJSON(String urli) {
        String response = null;
        try {
            URL url = new URL(urli);
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
            System.out.println("API working properly!");
        }

        return response;

    }



    public List<String> read2JSON(String url, String fruit){
        String json = getJSON(url);
        //System.out.println("JSON: "+ json);

        List<String> jsonlist = new ArrayList<String>();

        if(json != null){
            try{
                JSONArray jsonArray = new JSONArray(json);


                for(int i = 0; i <jsonArray.length() ; i++){
                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                    System.out.println(jsonobject.getString("name"));
                    String Fruit = fruit.substring(0,1).toUpperCase() + fruit.substring(1).toLowerCase();
                    if (jsonobject.getString("name").equals(Fruit)) {


                        JSONObject jsonobject2 = jsonobject.getJSONObject("nutritions");

                        System.out.println(jsonobject.getString("name"));
                        System.out.println(jsonobject2.getString("calories"));
                        jsonlist.add(Fruit);
                        jsonlist.add(jsonobject2.getString("calories"));
                        jsonlist.add(jsonobject2.getString("carbohydrates"));
                        jsonlist.add(jsonobject2.getString("protein"));
                        jsonlist.add(jsonobject2.getString("fat"));
                        jsonlist.add(jsonobject2.getString("sugar"));
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonlist;
    }
}

