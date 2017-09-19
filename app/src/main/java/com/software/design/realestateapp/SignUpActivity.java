package com.software.design.realestateapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SignUpActivity extends AppCompatActivity {

    //declare on screen element variables
    Button signUp;
    EditText name, surname, password, confirmPassword, username, email, phonenumber;
    CheckBox agent;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Change action bar - move this to styles next sprint
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
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

        resultTextView = (TextView)findViewById(R.id.textView_signUp_result);
    }

    public int checkCompletedFields(){
        if(
                name.getText().toString().trim().length() == 0 ||
                surname.getText().toString().trim().length() == 0 ||
                password.getText().toString().trim().length() == 0 ||
                confirmPassword.getText().toString().trim().length() == 0 ||
                username.getText().toString().trim().length() == 0 ||
                email.getText().toString().trim().length() == 0 ||
                phonenumber.getText().toString().trim().length() == 0

                ){

            return 1; //There is an empty field
        }else {
            return 0; //All fields are complete
        }
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

        //Error validation



        //if passwords match send data for query
        if (checkCompletedFields() == 0) {
            if (passwordData.equals(confirmPasswordData)) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                resultTextView.setText(response);

                                //Success
                                if (response.equals("0")) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.SignUp_CreatedUser_0), Toast.LENGTH_LONG).show();
                                } else if (response.equals("1")) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.SignUp_Failed_1), Toast.LENGTH_LONG).show();
                                }

                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                resultTextView.setText("2");
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
            } else {
                //Passwords do not match
                resultTextView.setText("3");
                Toast.makeText(getApplicationContext(), getString(R.string.SignUp_PasswordNoMatch_3), Toast.LENGTH_LONG).show();
            }
        }else {
            resultTextView.setText("4");
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_FieldsIncomplete_4), Toast.LENGTH_LONG).show();
        }
    }

    public static int processJSON(String json) {
        System.out.println(json);
        return Integer.parseInt(json);
    }
}
