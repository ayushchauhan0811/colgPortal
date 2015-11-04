<<<<<<< HEAD
package com.example.android.colgportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import data.Placesadapter;
import model.Places;

public class Hangout extends AppCompatActivity {
    ImageView AddButton;

    private ListView listView;
    private ArrayList<Places> mPlacess;
    private Placesadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        listView = (ListView) findViewById(R.id.list);
        mPlacess = new ArrayList<Places>();
        mAdapter = new Placesadapter(Hangout.this,   mPlacess);
        listView.setAdapter(mAdapter);
        receivePlaces();
        AddButton=(ImageView) findViewById(R.id.add);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hangout.this,PlaceDetails.class));
            }
        });
    }

    private void receivePlaces() {
        ParseQuery<Places> query = ParseQuery.getQuery(Places.class);
        query.setLimit(20);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<Places>() {
            @Override
            public void done(List<Places> messages, ParseException e) {
                if (e == null) {
                    mPlacess.clear();
                    mPlacess.addAll(messages);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
||||||| merged common ancestors
=======
package com.example.android.colgportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import data.Placesadapter;
import model.Places;

public class Hangout extends AppCompatActivity {
    Button AddButton;

    private ListView listView;
    private ArrayList<Places> mPlacess;
    private Placesadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        listView = (ListView) findViewById(R.id.list);
        mPlacess = new ArrayList<Places>();
        mAdapter = new Placesadapter(Hangout.this,   mPlacess);
        listView.setAdapter(mAdapter);
        receivePlaces();
        AddButton=(Button) findViewById(R.id.add);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hangout.this,PlaceDetails.class));
            }
        });
    }

    private void receivePlaces() {
        ParseQuery<Places> query = ParseQuery.getQuery(Places.class);
        query.setLimit(20);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<Places>() {
            @Override
            public void done(List<Places> messages, ParseException e) {
                if (e == null) {
                    mPlacess.clear();
                    mPlacess.addAll(messages);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
>>>>>>> master
