package com.software.design.realestateapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    //initialize variables
    Button signUp;
    Button login;
    EditText username, password;
    String url;
    TextView resultTextView;


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


        //assign text fields to variables
        username = (EditText) findViewById(R.id.editText_Username_login);
        password = (EditText) findViewById(R.id.editText_Password_login);
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/existsUser.php";

        login = (Button) findViewById(R.id.button_login);
        resultTextView = (TextView) findViewById(R.id.textView_logIn_result);

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
            resultTextView.setText("4");
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_FieldsIncomplete_4), Toast.LENGTH_LONG).show();
        }

        if (usernameData.contains("*")) {
            //checks if username has a * symbol
            valid = false;
            resultTextView.setText("1");
            Toast.makeText(getApplicationContext(), "Username is invalid", Toast.LENGTH_LONG).show();
        }

        if (passwordData.length() <= 3) {
            //checks if phone has only digits
            valid = false;
            resultTextView.setText("1");
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
        }

        if(valid){
            //resultTextView.setText("0");
            if (!isTest) {
                sendLoginData(usernameData, passwordData);
            } else {
                mockSendLoginData(usernameData,passwordData);
            }
        }
    }

    public void sendLoginData(String usernameData, String passwordData){
        final String c_usernameData = usernameData, c_passwordData = passwordData;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LogInActivity.this,response,Toast.LENGTH_LONG).show();
                        resultTextView.setText(response);

                        if (response.contains("0")) {
                            Intent intent = new Intent(getBaseContext(), DrawerActivity.class);
                            intent.putExtra("Username", c_usernameData);
                            startActivity(intent);

                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Login Details Incorrect", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultTextView.setText("2");
                        Toast.makeText(LogInActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("USERNAME",c_usernameData);
                params.put("PASSWORD", c_passwordData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void mockSendLoginData(String usernameData, String passwordData) {
        System.out.println("Logging in");
    }


    public void onLoginClick(View v) {
        loginUserTestable(false);

    }

}