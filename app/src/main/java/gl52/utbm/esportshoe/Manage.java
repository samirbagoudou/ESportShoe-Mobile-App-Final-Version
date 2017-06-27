package gl52.utbm.esportshoe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Manage extends AppCompatActivity {

    final Context context = this;
    private Button addShoeButton = null;
    private Button delShoeButton = null;
    private EditText addShoeEditText = null;
    private Spinner spinner = null;

    DBHelper esportshoeDB;

    private String shoeToAdd = "";
    private String spinnerChoice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        esportshoeDB = new DBHelper(this);

        //Assigning related views to graphic components
        spinner = (Spinner)findViewById(R.id.delShoespinner);
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

        addShoeButton = (Button)findViewById(R.id.addShoeButton);
        delShoeButton = (Button)findViewById(R.id.delShoeButton);
        addShoeEditText = (EditText)findViewById(R.id.addShoeEditText);

        addShoeButton.setOnClickListener(addShoeButtonListener);
        delShoeButton.setOnClickListener(delShoeButtonListener);

    }


    private View.OnClickListener addShoeButtonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //When the addShoeButton is pressed, get the shoe name typed and add it to DB
            shoeToAdd = addShoeEditText.getText().toString();

            if(esportshoeDB.insertShoe(shoeToAdd)){
                Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(context, "Add error", Toast.LENGTH_SHORT).show();

            esportshoeDB.close();
            finish();
            startActivity(getIntent());
        }
    };

    private View.OnClickListener delShoeButtonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //When the delShoeButton is pressed, get the shoe name selected and delete it from DB
            esportshoeDB.deleteShoe(spinnerChoice);
                Toast.makeText(context, spinnerChoice+" deleted", Toast.LENGTH_SHORT).show();

            esportshoeDB.close();
            finish();
            startActivity(getIntent());
        }
    };
}
