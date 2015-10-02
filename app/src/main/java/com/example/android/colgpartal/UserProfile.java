package com.example.android.colgpartal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class  UserProfile extends AppCompatActivity implements View.OnClickListener {
    Button sell;
    Button onSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);


        sell= (Button) findViewById(R.id.sell);
        onSale= (Button) findViewById((R.id.onSale));


        sell.setOnClickListener(this);
        onSale.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logoout) {

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        startActivity(new Intent(UserProfile.this, MainActivity.class));

                    } else {

                    }
                }
            });



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sell:
                startActivity(new Intent(UserProfile.this,Sell.class));
                //we do something
                break;
            case R.id.onSale:
                startActivity(new Intent(UserProfile.this, OnSale.class));
        }
    }
}
