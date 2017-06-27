package gl52.utbm.esportshoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    public static final String TAG = "Graph";
    ArrayList<String> allSensorValues = new ArrayList<>();
    DBHelper esportshoeDB;

    private LineGraphSeries<DataPoint> series;
    private BarGraphSeries<DataPoint> seriesG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        esportshoeDB = new DBHelper(this);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0); //min data
        graph.getViewport().setMaxY(15); //max data

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10); //sizeallvalue


        Intent intent = getIntent();
        String field = intent.getStringExtra("key");
        if(field.equals("1")){
            //Intent came from first sensor field
            Log.i(TAG, "Came from first field");
            allSensorValues = esportshoeDB.getAllSensor1Values();

            graph.getGridLabelRenderer().setVerticalAxisTitle("Speed (km/h)");
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Number");
            //graph.getGridLabelRenderer().setNumHorizontalLabels(10);

            series = new LineGraphSeries<>(generateData(allSensorValues));

            graph.addSeries(series);
        }

        else if(field.equals("3")){
            //Intent came from third sensor field
            Log.i(TAG, "Came from first field");
            allSensorValues = esportshoeDB.getAllSensor3Values();

            graph.getGridLabelRenderer().setVerticalAxisTitle("Pressure");
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Number");
            //graph.getGridLabelRenderer().setNumHorizontalLabels(10);

            seriesG = new BarGraphSeries<>(generateData(allSensorValues));

            graph.addSeries(seriesG);
        }
        else if(field.equals("4")){
            //Intent came from forth sensor field
            allSensorValues = esportshoeDB.getAllSensor4Values();
        }


    }

    private DataPoint[] generateData(ArrayList<String> list) {
        int count = list.size();
        DataPoint[] values = new DataPoint[count];

        for (int i=0; i<count; i++) {
            int x = i;
            Double y = Double.parseDouble(list.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

}
