package com.software.design.realestateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SignUpActivity extends AppCompatActivity {

    //declare on screen element variables
    Button signUp;
    EditText name, surname, password, confirmPassword, username, email, phonenumber;
    CheckBox agent;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //assign screen element variables to fields
        name = (EditText)findViewById(R.id.editText_Name);
        surname = (EditText)findViewById(R.id.editText_Surname);
        password = (EditText)findViewById(R.id.editText_Password);
        confirmPassword = (EditText)findViewById(R.id.editText_ConfirmPassword);
        username = (EditText)findViewById(R.id.editText_Username);
        email = (EditText)findViewById(R.id.editText_EmailAddress);
        phonenumber = (EditText)findViewById(R.id.editText_PhoneNumber);
        agent = (CheckBox)findViewById(R.id.checkBox_Agent);
        signUp = (Button)findViewById(R.id.button_SignUpSend);
        //php url for insert
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertUser.php";
    }

    //on click method for signup button
    public void signUpUser(View v) {
        //get current values from fields
        final String usernameData = username.getText().toString().trim();
        final String passwordData = password.getText().toString().trim();
        final String nameData = name.getText().toString().trim();
        final String surnameData = surname.getText().toString().trim();
        final String confirmPasswordData = confirmPassword.getText().toString().trim();
        final String phonenumberData = phonenumber.getText().toString().trim();
        final String emailData = email.getText().toString().trim();
        final boolean agentBool = agent.isChecked();
        final String agentData;
        if(agentBool){
            agentData = "A";
        }else{
            agentData = "R";
        }
        //if passwords match send data for query
        if(passwordData.equals(confirmPasswordData)){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("USERNAME", usernameData);
                params.put("PASSWORD", passwordData);
                params.put("NAME", nameData);
                params.put("SURNAME", surnameData);
                params.put("EMAIL", emailData);
                params.put("PHONENUMBER", phonenumberData);
                params.put("USER_TYPE", agentData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }else{
            System.out.println("The passwords do not match");
            Toast.makeText(getApplicationContext(),"The passwords do not match!",Toast.LENGTH_LONG).show();
        }
    }
}
