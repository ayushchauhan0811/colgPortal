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

public class OnSale extends AppCompatActivity {
    private ListView listView;
    private ArrayList<BooksModel> mbooks;
    private OnSaleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_sale);

        listView = (ListView) findViewById(R.id.list);
        mbooks = new ArrayList<BooksModel>();
        mAdapter = new OnSaleAdapter(OnSale.this, mbooks);
        listView.setAdapter(mAdapter);
        receiveBooks();
    }
    private void receiveBooks() {
        ParseQuery<BooksModel> query = ParseQuery.getQuery(BooksModel.class);
        query.whereEqualTo("hasSold","NO");
        query.whereEqualTo("sellerId", ParseUser.getCurrentUser().getObjectId());

        query.findInBackground(new FindCallback<BooksModel>() {
            @Override
            public void done(List<BooksModel> messages, ParseException e) {
                if (e == null) {
                    mbooks.clear();
                    mbooks.addAll(messages);
                    mAdapter.notifyDataSetChanged();
                    listView.invalidate();//allows for the listview to be redrawn
                } else {
                    Log.v("Error:", "Error:" + e.getMessage());
                }
            }
        });

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
        if (id == R.id.logoout) {

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

        return super.onOptionsItemSelected(item);
    }
}
