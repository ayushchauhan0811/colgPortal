package com.example.android.colgpartal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.colgportal.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import data.BooksAdapter;
import model.BooksModel;

public class Books_buy_list extends AppCompatActivity {
    private ListView listView;
    private ArrayList<BooksModel> mBooks;
    private BooksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_buy_list);
        listView = (ListView) findViewById(R.id.list);
        mBooks = new ArrayList<BooksModel>();
        mAdapter = new BooksAdapter(Books_buy_list.this,mBooks);
        listView.setAdapter(mAdapter);
        receiveBooks();

    }

    private void receiveBooks() {
        ParseQuery<BooksModel> query = ParseQuery.getQuery(BooksModel.class);
        query.whereNotEqualTo("sellerId", ParseUser.getCurrentUser().getObjectId());
        query.whereNotEqualTo("hasSold", "YES");
        query.setLimit(20);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<BooksModel>() {
            @Override
            public void done(List<BooksModel> messages, ParseException e) {
                if (e == null) {
                    mBooks.clear();
                    mBooks.addAll(messages);
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
        getMenuInflater().inflate(R.menu.menu_books_buy_list, menu);
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

        if (item.getItemId() == R.id.action_home) {

            startActivity(new Intent(Books_buy_list.this, user_profile.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
