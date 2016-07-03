package com.example.android.colgpartal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.android.colgportal.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import Util.ProgressGenerator;

public class CreateAccount extends ActionBarActivity implements ProgressGenerator.OnCompleteListener{
    private EditText emailAddress;
    private EditText mobile;
    private EditText password;
    private EditText username;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailAddress = (EditText) findViewById(R.   id.userEmailId);
        password = (EditText) findViewById(R.id.usernamePasswordId);
        username = (EditText) findViewById(R.id.usernameAccountId);
        mobile=(EditText) findViewById(R.id.mobile);

        progressGenerator = new ProgressGenerator(this);

        createAccountButton = (ActionProcessButton) findViewById(R.id.userNameCreateAccountId);

        createAccountButton.setMode(ActionProcessButton.Mode.PROGRESS);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });



    }


    private void createAccount(){
        final String uEmail = emailAddress.getText().toString();
        final String uName = username.getText().toString();
        final String pWord = password.getText().toString();


        if (uEmail.equals("") || uName.equals("") || pWord.equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(CreateAccount.this);
            dialog.setTitle("Empty Fields");
            dialog.setMessage("Please complete the form");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else {

            ParseUser user = new ParseUser();

            //set core properties
            user.setUsername(uName);
            user.setPassword(pWord);
            user.setEmail(uEmail);

            //set custom property
            Log.d("error",MyApplication.global.getSomeVariable());
            user.put("colgId", MyApplication.global.getSomeVariable());
            user.put("mobile",mobile.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        progressGenerator.start(createAccountButton);
                        createAccountButton.setEnabled(false);

                        emailAddress.setEnabled(false);
                        username.setEnabled(false);
                        password.setEnabled(false);
                        mobile.setEnabled(false);

                        //log them in right away!
                        logUserIn(uName, pWord);
                    }

                }
            });

        }

    }

    private void logUserIn(String uName, String pWord) {
        if (!uName.equals("") || !pWord.equals("")) {

            ParseUser.logInInBackground(uName, pWord, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (e == null) {
                        Log.v("USER LOGGED IN IS:", parseUser.getUsername());

                    }else {

                    }
                }
            });
        }else {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    @Override
    public void onComplete() {

        startActivity(new Intent(CreateAccount.this, user_profile.class));

    }
}
