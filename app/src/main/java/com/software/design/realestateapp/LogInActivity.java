package com.software.design.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

//we now implement the VolleyResponce interface
public class LogInActivity extends AppCompatActivity implements VolleyResponce {
    //initialize variables
    Button signUp;
    Button login;
    EditText username, password;
    String url;

    String testReciever;


    /*
    Result Codes:
        0 - Success
        1 - Fail
        2 - Text too long
        3 - Passwords do not match
        4 - Not all fields complete
        5 - Existing user

        */

    //method that returns values based on the servers response

    public int checkCompletedFields(String... input) {

        for (String anInput : input) {
            if (anInput.trim().length() == 0) {
                return 1;
            }
        }
        return 0;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //assign the signup button to the variable and initialize a listener for a click
        signUp = (Button) findViewById(R.id.button_signup);
        //Change action bar - move this to styles next sprint
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));

        findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(R.color.grey));


        //assign text fields to variables
        username = (EditText) findViewById(R.id.editText_Username_login);
        password = (EditText) findViewById(R.id.editText_Password_login);
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/existsUser.php";

        login = (Button) findViewById(R.id.button_login);

        testReciever = new String();

    }

    @Override
    public void handleResponce(Object response, Map<String, String> map, int key) {

        //This is the same code that you would have in the response part of the volley request
        if (key == 1) {
            String c_response = (String) response;
            String[] _response = c_response.split(" ");
            System.out.println(_response[0]);
            String code = _response[0];
            String s_user_id = _response[1];
            String user_email = _response[2];
            String user_name = _response[3];
            String usernameData = map.get("USERNAME");
            testReciever = _response[0];

            if (code.contains("0")) {
                Intent intent = new Intent(getBaseContext(), DrawerActivity.class);
                intent.putExtra("Username", usernameData);
                intent.putExtra("USER_ID",s_user_id);
                startActivity(intent);

                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Login Details Incorrect", Toast.LENGTH_LONG).show();
                testReciever = "Incorrect";
            }

        }
    }

    @Override
    public void handleError(Object error, int key) {
        //This is the same block of code that you would have in the error part of the request
        if (key == 1) {
            testReciever = "2";
            Toast.makeText(LogInActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public Context getContext() {
        //This is needed for the request queue
        return this;
    }

    public void onSignUpClick(View v){
        Intent changeToSignUp = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(changeToSignUp);
    }


    public void loginUserTestable(boolean isTest) {
        final String usernameData = username.getText().toString().trim();
        final String passwordData = password.getText().toString().trim();

        boolean valid = true;

        if (checkCompletedFields(usernameData, passwordData) != 0) {
            valid = false;
            testReciever = "4";
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_FieldsIncomplete_4), Toast.LENGTH_LONG).show();
        }

        if (usernameData.contains("*")) {
            //checks if username has a * symbol
            valid = false;
            testReciever = "1";
            Toast.makeText(getApplicationContext(), "Username is invalid", Toast.LENGTH_LONG).show();
        }

        if (passwordData.length() <= 3) {
            //checks if phone has only digits
            valid = false;
            testReciever = "1";
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
        }

        if(valid){
            sendLoginData(usernameData, passwordData, isTest);
        }
    }

    public void sendLoginData(String usernameData, String passwordData, boolean isTest) {

        //Same map as the one you would put in the volley request
        final Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", usernameData);
        params.put("PASSWORD", passwordData);

        int key = 1; //This will be different for each request made in the same activity

        //Make request and start activity
        VolleyRequest volleyrequest = new VolleyRequest(url, params, this, key);
        testReciever = "0";

        if (!isTest) {
            volleyrequest.makeRequest();
        }
    }


    public void onLoginClick(View v) {
        loginUserTestable(false);

    }

}