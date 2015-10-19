package com.example.android.colgpartal;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import model.BooksModel;
import model.CarModel;
import model.Colg;
import model.GlobalVariables;
import model.Profile;

public class MyApplication extends Application {

    public static final String APP_KEY_ID = "DLaP289m95tXF5UOQd4dKBHN0yMHtCLlKkCYCjTg";
    public static final String APP_CLIENT_ID = "h5tgpDGsFpAE3DTfvrE2pwQ3h1gCcaIFrWNGHh68";
    public static GlobalVariables global= new GlobalVariables();

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Colg.class);
        ParseObject.registerSubclass(BooksModel.class);
        ParseObject.registerSubclass(CarModel.class);
        ParseObject.registerSubclass(Profile.class);

        Parse.initialize(this, "metwWjc2cc4YjPBb4dtc9H41zZUYO0EmwbDYTOqf", "w0KNMSiP4DseOw4BCw9WkXIUQb0eDhwkJj58NHc8");

    }
}
