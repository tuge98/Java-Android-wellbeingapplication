package com.example.wellbeing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

// Here the carbon footprint information is received and plotted
public class carbonfootprintSummary extends AppCompatActivity {
    GraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s5_carbonfootprintsummary);

        Bundle b = getIntent().getExtras();
        double dairy = b.getDouble("Dairy");
        double meat = b.getDouble("Meat");
        double plant = b.getDouble("Plant");
        double restaurant = b.getDouble("Restaurant");
        double total = b.getDouble("Total");
        //System.out.println(dairy);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, dairy),
                new DataPoint(1, meat),
                new DataPoint(2, plant),
                new DataPoint(3, restaurant),
                new DataPoint(4, total)

        });
        graph.addSeries(series);
        series.setSpacing(50);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"dairy", "meat", "plant", "restaurant","total"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

    }
}