package com.example.bookkeeping;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class WeightScreen extends AppCompatActivity {
    String currentUsername;
    EditText weightIn;
    GraphView graph;
    Button button;
    Button button2;
    int s;
    List<Weight> w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s6_weight);
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
        weightIn = (EditText) findViewById(R.id.weightinputfield);
        graph = (GraphView) findViewById(R.id.graph);

        button2 = (Button) findViewById(R.id.button2);

    }

    // This function takes the user input and inserts it to database
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addWeightToDB(View v) {
        LocalDate today = LocalDate.now();
        String dateAsStr = today.toString();
        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();
        Weight weightInfo = new Weight(currentUsername, dateAsStr, Double.parseDouble(weightIn.getText().toString()));
        Runnable task = () -> {
            System.out.println("Syotetaan:" + currentUsername + ", " + dateAsStr);
            dao.insertWeight(weightInfo);
        };


        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();

    }


    // Function querys user's weights from database that user has inserted, then uses these weights as datapoints for a the graph
    public void graphView(View v) {


        UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();
        Runnable task = () -> {

            List<UserWithWeights> lista = dao.getUserWithWeights(currentUsername);
            w = lista.get(0).allWeights;
            int index = 0;
            s = w.size();
            for (Weight y : w) {

                System.out.println("Painotiedot: " + y.getWeight_id() + ": " + y.getWeight() + ", " + y.getDate());
                index++;
                if (index == s) {
                    break;
                }
            }
        };


        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();

        DataPoint[] dataPoints = new DataPoint[s];
        for (int i = 0; i < s; i++) {
            Double y;
            if ((y = w.get(i).getWeight()) == null) {
                System.out.println("numbers are out");
                break;

            }
            System.out.println("luvut:" + i + ", " + y);
            dataPoints[i] = new DataPoint(i, y);

        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        graph.addSeries(series);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(150.0);
    }
    }

