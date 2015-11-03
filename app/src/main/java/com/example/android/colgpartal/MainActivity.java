package com.example.android.colgpartal;

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

import data.Colgadapter;
import model.Colg;

public class MainActivity extends AppCompatActivity {
    ImageView AddButton;

    private ListView listView;
    private ArrayList<Colg> mColgs;
    private Colgadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colg_list);
        listView = (ListView) findViewById(R.id.list);
        mColgs = new ArrayList<Colg>();
        mAdapter = new Colgadapter(MainActivity.this,   mColgs);
        listView.setAdapter(mAdapter);
        receiveColg();
        AddButton=(ImageView) findViewById(R.id.add);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ColgDetails.class));
            }
        });
    }

    private void receiveColg() {
        ParseQuery<Colg> query = ParseQuery.getQuery(Colg.class);
        query.setLimit(20);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<Colg>() {
            @Override
            public void done(List<Colg> messages, ParseException e) {
                if (e == null) {
                    mColgs.clear();
                    mColgs.addAll(messages);
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
