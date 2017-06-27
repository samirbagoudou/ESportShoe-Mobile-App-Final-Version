package gl52.utbm.esportshoe;
//       /GL52/ESPORTSHOE/SHOE1/SENSOR1
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    final Context context = this;
    public MqttAndroidClient mqttAndroidClient ;
    private boolean hasStarted = false;
    private String shoe = "";
    DBHelper esportshoeDB;

    ArrayList<String> allSensor1Values = new ArrayList<>();
    ArrayList<String> allSensor2Values = new ArrayList<>();
    ArrayList<String> allSensor3Values = new ArrayList<>();
    ArrayList<String> allSensor4Values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        esportshoeDB = new DBHelper(this);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String fn = intent.getStringExtra("fn");
        String ln = intent.getStringExtra("ln");
        shoe = intent.getStringExtra("sc");

        TextView welcome = (TextView)findViewById(R.id.welcomeTView);
        welcome.setText("Welcome "+fn+" "+ln+" with "+shoe);

        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), "tcp://iot.eclipse.org:1883", "esportshoe");
        ///////////////////////////////////////////////////////////MQTT HANDLING/////////////////////////////////////////////////////////////////////////////////////////////////
        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.i(TAG, "Connection was lost!");
                Toast.makeText(MainActivity.this, "Connection was lost!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                    if(topic.equals("/GL52/ESPORTSHOE/"+shoe+"/SENSOR1")) {
                        Log.i(TAG, "Message Arrived!: " + topic + ": " + new String(message.getPayload()));
                        Toast.makeText(MainActivity.this, "Message Arrived!: " + topic + ": " + new String(message.getPayload()), Toast.LENGTH_SHORT).show();
                        TextView stat1 = (TextView) findViewById(R.id.stat1);
                        stat1.setText(new String(message.getPayload()));
                        esportshoeDB.insertSensor1Value(new String(message.getPayload()));
                    }
                    else if(topic.equals("/GL52/ESPORTSHOE/"+shoe+"/SENSOR2")) {
                        Log.i(TAG, "Message Arrived!: " + topic + ": " + new String(message.getPayload()));
                        Toast.makeText(MainActivity.this, "Message Arrived!: " + topic + ": " + new String(message.getPayload()), Toast.LENGTH_SHORT).show();
                        TextView stat2 = (TextView) findViewById(R.id.stat2);
                        stat2.setText(new String(message.getPayload()));
                        esportshoeDB.insertSensor2Value(new String(message.getPayload()));
                    }
                    else if(topic.equals("/GL52/ESPORTSHOE/"+shoe+"/SENSOR3")) {
                        Log.i(TAG, "Message Arrived!: " + topic + ": " + new String(message.getPayload()));
                        Toast.makeText(MainActivity.this, "Message Arrived!: " + topic + ": " + new String(message.getPayload()), Toast.LENGTH_SHORT).show();
                        TextView stat3 = (TextView) findViewById(R.id.stat3);
                        stat3.setText(new String(message.getPayload()));
                        esportshoeDB.insertSensor3Value(new String(message.getPayload()));
                    }
                    else if(topic.equals("/GL52/ESPORTSHOE/"+shoe+"/SENSOR4")) {
                        Log.i(TAG, "Message Arrived!: " + topic + ": " + new String(message.getPayload()));
                        Toast.makeText(MainActivity.this, "Message Arrived!: " + topic + ": " + new String(message.getPayload()), Toast.LENGTH_SHORT).show();
                        //TextView field4 = (TextView) findViewById(R.id.field4);
                        //field4.setText(new String(message.getPayload()));
                        esportshoeDB.insertSensor4Value(new String(message.getPayload()));
                    }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.i(TAG, "Delivery Complete!");
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView field1 = (TextView)findViewById(R.id.field1);
        field1.setText("1-Speed");

        field1.setBackgroundResource(R.drawable.graph);
        field1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Field 1 Clicked. Values : "+allSensor1Values, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(context, Graph.class);
                intent.putExtra("key", "1");
                startActivity(intent);
            }
        });

        TextView field2 = (TextView)findViewById(R.id.field2);
        field2.setText("2-Hot Zones");
        field2.setBackgroundResource(R.drawable.gmapicon);
        field2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Field 2 Clicked", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                //Snackbar.make(view, "Field 2 Clicked. Values : "+allSensor2Values, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(context, Maps.class);
                intent.putExtra("key", "2");
                startActivity(intent);
            }
        });

        TextView field3 = (TextView)findViewById(R.id.field3);
        field3.setText("3-Pressure");
        field3.setBackgroundResource(R.drawable.bargraph);
        field3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Field 3 Clicked. Values : "+allSensor3Values, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(context, Graph.class);
                intent.putExtra("key", "3");
                startActivity(intent);
            }
        });

        TextView field4 = (TextView)findViewById(R.id.field4);
        field4.setText("Stats");
        /*field4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Field 4 Clicked. Values : "+allSensor4Values, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(context, Graph.class);
                intent.putExtra("key", "4");
                startActivity(intent);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle("Exit App")
                    .setMessage("Are you sure you want to exit our beautiful App?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            Log.i(TAG, "Activating front vibration");
            publish(MainActivity.this, mqttAndroidClient, "/GL52/ESPORTSHOE/"+shoe+"/VIB", new MqttMessage("FRONT".getBytes()));
            return true;
        }
        else if (id == R.id.action_settings2) {
            Log.i(TAG, "Activating back vibration");
            publish(MainActivity.this, mqttAndroidClient, "/GL52/ESPORTSHOE/"+shoe+"/VIB", new MqttMessage("BACK".getBytes()));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_startT) {
            Log.i(TAG, "Start training option selected");
            //Empty sensors tables to receive new values
            esportshoeDB.truncateSensorTables();
            hasStarted = true;
            Snackbar.make(findViewById(R.id.field4), "Training Starting", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            try {
                mqttAndroidClient.connect(null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i(TAG, "Connection Success!");
                        Toast.makeText(MainActivity.this, "Connection successful", Toast.LENGTH_SHORT).show();
                        try {
                            mqttAndroidClient.subscribe("/GL52/ESPORTSHOE/"+shoe+"/SENSOR1", 0);
                            Toast.makeText(MainActivity.this, "Subscribed to "+"/GL52/ESPORTSHOE/"+shoe+"/SENSOR1", Toast.LENGTH_SHORT).show();
                            mqttAndroidClient.subscribe("/GL52/ESPORTSHOE/"+shoe+"/SENSOR2", 0);
                            Toast.makeText(MainActivity.this, "Subscribed to "+"/GL52/ESPORTSHOE/"+shoe+"/SENSOR2", Toast.LENGTH_SHORT).show();
                            mqttAndroidClient.subscribe("/GL52/ESPORTSHOE/"+shoe+"/SENSOR3", 0);
                            Toast.makeText(MainActivity.this, "Subscribed to "+"/GL52/ESPORTSHOE/"+shoe+"/SENSOR3", Toast.LENGTH_SHORT).show();
                            mqttAndroidClient.subscribe("/GL52/ESPORTSHOE/"+shoe+"/SENSOR4", 0);
                            Toast.makeText(MainActivity.this, "Subscribed to "+"/GL52/ESPORTSHOE/"+shoe+"/SENSOR4", Toast.LENGTH_SHORT).show();
                        } catch (MqttException ex) {
                        }
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i(TAG, "Connection Failure!");
                        Toast.makeText(MainActivity.this, "Connection Failure!", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MqttException ex) {
            }
        }

        else if (id == R.id.nav_stopT) {
            //hasStarted = false;
            if(hasStarted == true){
                Snackbar.make(findViewById(R.id.field4), "Training Stopped", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                allSensor1Values = esportshoeDB.getAllSensor1Values();
                allSensor2Values = esportshoeDB.getAllSensor2Values();
                allSensor3Values = esportshoeDB.getAllSensor3Values();
                allSensor4Values = esportshoeDB.getAllSensor4Values();
                try {
                    mqttAndroidClient.disconnect(null, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i(TAG, "Disconnection Success!");
                            Toast.makeText(MainActivity.this, "Disconnection success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i(TAG, "Disconnection Failure!");
                            Toast.makeText(MainActivity.this, "Disconnection Failure!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MqttException ex) {
                }
                hasStarted = false;
            }
        }

        else if (id == R.id.nav_manage) {
            //Snackbar.make(findViewById(R.id.field4), "Handle Settings", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Intent intent = new Intent(context, Manage.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_contactUs) {
            Snackbar.make(findViewById(R.id.field4), "eSportShoe Team : gl52@utbm.fr'", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void publish(final Context main, final MqttAndroidClient client, final String topic, final MqttMessage message){
        try {
            client.connect(null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "Connection Success!");
                    Toast.makeText(main, "Connected", Toast.LENGTH_SHORT).show();
                    try {
                        Log.i(TAG, "Publishing message..");
                        message.setQos(0);
                        message.setRetained(false);
                        client.publish(topic, message);
                        Toast.makeText(main, "Published Message : "+topic+"/"+message, Toast.LENGTH_SHORT).show();
                        //client.disconnect();
                    } catch (MqttException ex) {
                    }
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "Connection Failure!");
                    Toast.makeText(main, "Connection Failure!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException ex) {

        }
    }

}