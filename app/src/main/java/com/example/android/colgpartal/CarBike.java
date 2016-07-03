package com.example.android.colgpartal;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.colgportal.R;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import model.CarModel;

public class CarBike extends AppCompatActivity {
    Button button;
    EditText name;
    EditText year;
    EditText desc;
    EditText quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_car_bike);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name= (EditText) findViewById(R.id.editText);
        year = (EditText) findViewById(R.id.editText2);
        desc= (EditText) findViewById(R.id.editText3);
        quantity= (EditText) findViewById(R.id.editText4);
        button= (Button) findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarModel model= new CarModel();
                model.setName(name.getText().toString());
                model.setColgId(MyApplication.global.getSomeVariable());
                model.setModel(year.getText().toString());
                model.setDesc(desc.getText().toString());
                model.setPrice(Integer.parseInt(quantity.getText().toString()));
                model.setSellerId(ParseUser.getCurrentUser().getObjectId());
                model.setHasSold("NO");
                model.saveInBackground();

                Toast.makeText(getApplicationContext(), "Details saved successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CarBike.this,user_profile.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_books, menu);
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

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        startActivity(new Intent(CarBike.this, MainActivity.class));

                    } else {

                    }
                }
            });



            return true;
        }

        if (item.getItemId() == R.id.action_home) {

            startActivity(new Intent(CarBike.this, user_profile.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}