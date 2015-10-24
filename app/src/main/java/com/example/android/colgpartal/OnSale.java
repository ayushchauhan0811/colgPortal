package com.example.android.colgpartal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import data.OnSaleAdapter;
import model.BooksModel;
import model.CarModel;

public class OnSale extends AppCompatActivity {
    private ListView listView;
    public static ArrayList<BooksModel> mbooks;
    public static ArrayList<CarModel> mcars;
    private ArrayList<String> mcombine;
    private OnSaleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_sale);

        listView = (ListView) findViewById(R.id.list);
        mbooks = new ArrayList<BooksModel>();
        mcars= new ArrayList<CarModel>();
        mcombine= new ArrayList<>();
        mAdapter = new OnSaleAdapter(OnSale.this, mcombine);
        listView.setAdapter(mAdapter);
        receiveBooks();
        receiveCars();
        combine();

        Log.v("error", "combine called");
    }
    private void receiveBooks() {
        ParseQuery<BooksModel> query = ParseQuery.getQuery(BooksModel.class);
        query.whereEqualTo("hasSold", "NO");
        query.whereEqualTo("sellerId", ParseUser.getCurrentUser().getObjectId());

        query.findInBackground(new FindCallback<BooksModel>() {
            @Override
            public void done(List<BooksModel> messages, ParseException e) {
                if (e == null) {

                     //mbooks.clear();
                    if(messages!=null) {
                        mbooks.addAll(messages);
                    }
                    else {
                        mbooks=null;
                    }
                    return;
                } else {
                    Log.v("Error:", "Error:" + e.getMessage());
                }
            }
        });

    }
    private void receiveCars() {
        ParseQuery<CarModel> query = ParseQuery.getQuery(CarModel.class);
        query.whereEqualTo("hasSold","NO");
        query.whereEqualTo("sellerId", ParseUser.getCurrentUser().getObjectId());

        query.findInBackground(new FindCallback<CarModel>() {
            @Override
            public void done(List<CarModel> messages2, ParseException e) {
                if (e == null) {
                    // mcars.clear();
                    if(messages2!=null) {
                        mcars.addAll(messages2);
                    }
                    else{
                        mcars=null;
                    }
                    combine();
                } else {
                    Log.v("Error:", "Error:" + e.getMessage());
                }
            }
        });

    }
    private void combine(){

        int i;
        Log.e("book size", String.valueOf(mbooks.size()));

        if(mbooks!=null) {
            for (i = 0; i < mbooks.size(); i++) {
                mcombine.add(mbooks.get(i).getName());
            }
        }
        Log.e("car size", String.valueOf(mcars.size()));

        if(mcars!=null) {
            for (i = 0; i < mcars.size(); i++) {
                mcombine.add(mcars.get(i).getName());
            }
        }

        mAdapter.notifyDataSetChanged();
        listView.invalidate();//allows for the listview to be redrawn

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_on_sale, menu);
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

                        startActivity(new Intent(OnSale.this, MainActivity.class));

                    } else {

                    }
                }
            });



            return true;
        }

        if (item.getItemId() == R.id.action_home) {

            startActivity(new Intent(OnSale.this, user_profile.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
