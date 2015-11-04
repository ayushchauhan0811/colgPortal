package com.example.android.colgportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.Places;

public class PlaceDetails extends AppCompatActivity {
    private Button button;
    private EditText place;
    private EditText location;
    private EditText distance;
    private EditText review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_details);

        button=(Button) findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place=(EditText) findViewById(R.id.editText);
                location=(EditText) findViewById(R.id.editText2);
                distance= (EditText) findViewById(R.id.editText3);
                review= (EditText) findViewById(R.id.editText4);
                Places placeDetails = new Places();
                placeDetails.setName(place.getText().toString());
                Log.d("name", String.valueOf(place.getText()));
                placeDetails.setLocation(location.getText().toString());
                placeDetails.setDistance(distance.getText().toString());
                placeDetails.setReview(review.getText().toString());
                placeDetails.saveInBackground();
                startActivity(new Intent(getApplicationContext(),Hangout.class));
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
