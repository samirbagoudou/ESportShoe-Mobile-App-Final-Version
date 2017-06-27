package gl52.utbm.esportshoe;

/**
 * Created by root on 08/06/17.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Connection extends AppCompatActivity {

    public static final String TAG = "Connection";

    //Defining graphic components
    private Button connect = null;
    private EditText loginTField = null;
    private EditText passwordTField = null;
    private TextView incorrectMsg = null;
    private Spinner spinner = null;
    final Context context = this;

    DBHelper esportshoeDB;

    private String login = "";
    private String pass = "";
    private String fName = "";
    private String lName = "";
    private String spinnerChoice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        Log.i(TAG, "Instanciation DB");
        esportshoeDB = new DBHelper(this);

        ////////////////////////////////   BASE VALUES FOR DB ////////////////////////////////
        //Uncomment this only for first installation of application
        Log.i(TAG, "Adding test value");
        if(esportshoeDB.insertAdmin("Jhon", "DOE", "a", ""))
            Log.i(TAG, "Add admin successful");
        else
            Log.i(TAG, "Add error");
        if(esportshoeDB.insertShoe("SHOE1"))
            Log.i(TAG, "Add shoe successful");
        else
            Log.i(TAG, "Add error");
        if(esportshoeDB.insertSensor2Value("45.576166,5.965252"))
            Log.i(TAG, "Add shoe successful");
        else
            Log.i(TAG, "Add error");
        if(esportshoeDB.insertSensor2Value("45.576105,5.965315"))
            Log.i(TAG, "Add shoe successful");
        else
            Log.i(TAG, "Add error");
        /////////////////////////////////////////////////////////////////////////////////////

        //Assigning related views to graphic components
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> shoes = new ArrayList<>();
        shoes = esportshoeDB.getAllShoes();
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,shoes);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapterR);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                spinnerChoice = String.valueOf(spinner.getSelectedItem());}
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }

            });

        connect = (Button)findViewById(R.id.connect);
        loginTField = (EditText)findViewById(R.id.loginTField);
        passwordTField = (EditText)findViewById(R.id.passwordTField);
        incorrectMsg = (TextView)findViewById(R.id.incorrectMsg);
        incorrectMsg.setText("");

        //Attribute listeners to all views
        connect.setOnClickListener(connectListener);
        loginTField.addTextChangedListener(textWatcher);
        passwordTField.addTextChangedListener(textWatcher);

    }

    //Connect button listener
    private OnClickListener connectListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Log.i(TAG, "Connect button clicked");
            //When the connection button is pressed, create an intent to the main page and pass as argument the text typed in login field
            Intent intent = new Intent(context, MainActivity.class);//Intent intent = new Intent(context, PahoExampleActivity.class);
            login = loginTField.getText().toString();
            pass = passwordTField.getText().toString();

            Log.i(TAG, "Database ACCESS");
            Log.i(TAG, "Filling cursor");
            Cursor  adminCursor = esportshoeDB.getAdminFLNames(login, pass);
            Log.i(TAG, "Cursor filled");
            if(adminCursor.moveToFirst())
            {
                //User found
                fName = adminCursor.getString(adminCursor.getColumnIndex(Constants.ADMIN_FIRSTNAME));
                lName = adminCursor.getString(adminCursor.getColumnIndex(Constants.ADMIN_LASTNAME));
                Log.i(TAG, "Data affected");
                adminCursor.close();

                intent.putExtra("ln", lName);
                intent.putExtra("fn", fName);
                intent.putExtra("sc", spinnerChoice);
                startActivity(intent);
            }
            else {
                Log.i(TAG, "Cursor empty");
                incorrectMsg.setText("Wrong user or password");
            }

            esportshoeDB.close();
            Log.i(TAG, "End DB Access");
        }
    };

    private TextWatcher textWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            connect.setTextColor(Color.RED);
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        @Override
        public void afterTextChanged(Editable s) {}
    };


}

