package com.example.android.colgpartal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.Colg;

public class ColgDetails extends AppCompatActivity {
    private Button button;
    private EditText colg;
    private EditText location;
    private EditText speciality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colg_details);

        button=(Button) findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colg=(EditText) findViewById(R.id.editText);
                location=(EditText) findViewById(R.id.editText2);
                speciality= (EditText) findViewById(R.id.editText3);
                Colg ColgDetails = new Colg();
                ColgDetails.setName(colg.getText().toString());
                Log.d("name", String.valueOf(colg.getText()));
                ColgDetails.setLocation(location.getText().toString());
                ColgDetails.setSpeciality(speciality.getText().toString());
                ColgDetails.saveInBackground();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_colg_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_example) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
