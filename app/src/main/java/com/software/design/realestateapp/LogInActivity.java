package com.software.design.realestateapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //assign the signup button to the variable and initialize a listener for a click
        signUp = (Button)findViewById(R.id.button_signup);
        //Change action bar - move this to styles next sprint
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create intent to switch to signup activity
                Intent changeToSignUp = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(changeToSignUp);
            }
        });

        //assign text fields to variables
        username = (EditText)findViewById(R.id.editText_Username_Login);
        password = (EditText)findViewById(R.id.editText_Password_Login);
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/existsUser.php";

        login = (Button)findViewById(R.id.button_login);
    }

    //method that returns values based on the servers response
    public int processJSON(String json) {
        System.out.println(json);
        if (json.contains("1")) {
            return 1;
        } else{
            return 0;
        }
    }

    public void loginUser(){
        final String usernameData = username.getText().toString().trim();
        final String passwordData = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LogInActivity.this,response,Toast.LENGTH_LONG).show();
                        int result = processJSON(response);

                        if(result==1){
                            System.out.println("result is: " + result);
                            Toast.makeText(getApplicationContext(),"Existing found", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"No user found", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogInActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("USERNAME",usernameData);
                params.put("PASSWORD",passwordData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onLoginClick(View v){
        loginUser();

    }



//    public void loginClick(View v) {
//        ArrayList<String[]> list = new ArrayList<>();
//        String user = username.getText().toString();
//        String pass = password.getText().toString();
//        if (!user.isEmpty() && !pass.isEmpty()) {
//            list.add(new String[]{"username", user});
//            list.add(new String[]{"password", pass});
//
//            AsyncHttpPost asyncHttpPost = new AsyncHttpPost(new AsyncHandler() {
//
//                @Override
//                public void handleResponse(String response) {
//
//                    int result = processJSON(response);
//
//                    if (result == 1) {
//                        Toast toast = Toast.makeText(getApplicationContext(), "Existing user", Toast.LENGTH_LONG);
//                        toast.show();
//                    } else {
//                        Toast toast = Toast.makeText(getApplicationContext(), "Unknown username", Toast.LENGTH_LONG);
//                        toast.show();
//                    }
//
//
//                }
//
//            });
//            asyncHttpPost.execute("http://lamp.ms.wits.ac.za/~s1037363/realestate_app/test.php", list);
//        }
//    }
}
