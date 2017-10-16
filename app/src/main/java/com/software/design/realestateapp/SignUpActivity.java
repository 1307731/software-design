package com.software.design.realestateapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        setContentView(R.layout.activity_sign_up);

        //Change action bar - move this to styles next sprint
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
        //assign screen element variables to fields
        name = (EditText) findViewById(R.id.editText_Name_signup);
        surname = (EditText) findViewById(R.id.editText_Surname_signup);
        password = (EditText) findViewById(R.id.editText_Password_signup);
        confirmPassword = (EditText) findViewById(R.id.editText_ConfirmPassword_signup);
        username = (EditText) findViewById(R.id.editText_Username_signup);
        email = (EditText) findViewById(R.id.editText_EmailAddress_signup);
        phonenumber = (EditText) findViewById(R.id.editText_PhoneNumber_signup);
        agent = (CheckBox) findViewById(R.id.checkBox_Agent_signup);
        signUp = (Button) findViewById(R.id.button_SignUpSend);
        //php url for insert
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertUser.php";

        resultTextView = (TextView) findViewById(R.id.textView_signUp_result);
    }

    //on click method for signup button
    public void signUpUser(View v) {
        signUpUserTestable(false);
    }

    public void signUpUserTestable(boolean isTest) {

        final String TEST_STRING = "TEST";
        final String TEST_NUMBER = "1234";
        final String TEST_AGENT_DATA = "A";

        final String usernameData;
        final String passwordData;
        final String nameData;
        final String surnameData;
        final String confirmPasswordData;
        final String phonenumberData;
        final String emailData;
        final boolean agentBool;
        final String agentData;

        //get current values from fields
        usernameData = username.getText().toString().trim();
        passwordData = password.getText().toString().trim();
        nameData = name.getText().toString().trim();
        surnameData = surname.getText().toString().trim();
        confirmPasswordData = confirmPassword.getText().toString().trim();
        phonenumberData = phonenumber.getText().toString().trim();
        emailData = email.getText().toString().trim();
        agentBool = agent.isChecked();


        if (agentBool) {
            agentData = "A";
        } else {
            agentData = "R";
        }

        //Error validation

        boolean valid = true;
        //check if all fields are complete
        if (checkCompletedFields(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData) != 0) {
            valid = false;
            resultTextView.setText("4");
            System.err.println("completed fields");
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_FieldsIncomplete_4), Toast.LENGTH_LONG).show();
        }

        //checks if passwords match

        if (!passwordData.equals(confirmPasswordData)) {
            //Passwords do not match
            valid = false;
            resultTextView.setText("3");
            System.err.println("pass - confirm");
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_PasswordNoMatch_3), Toast.LENGTH_LONG).show();
        }

        //checks if email is valid
        if (!emailData.contains("@")) {
            //checks if email has a @ symbol
            valid = false;
            resultTextView.setText("1");
            System.err.println("contains @");
            Toast.makeText(getApplicationContext(), "Email is invalid", Toast.LENGTH_LONG).show();
        }

        if (usernameData.contains("*")) {
            //checks if username has a * symbol
            valid = false;
            resultTextView.setText("1");
            System.err.println("contains *");
            Toast.makeText(getApplicationContext(), "Username is invalid", Toast.LENGTH_LONG).show();
        }

        //checks if number contains only digits, android should cause this by default
        if (!checkNumber(phonenumberData)) {
            //checks if phone has only digits
            valid = false;
            resultTextView.setText("1");
            System.err.println("phonenumber");
            Toast.makeText(getApplicationContext(), "Phone number is invalid", Toast.LENGTH_LONG).show();
        }

        if (passwordData.length() <= 3) {
            //checks if phone has only digits
            valid = false;
            resultTextView.setText("1");
            System.err.println("passLength");
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
        }

        if (!checkName(nameData)) {
            valid = false;
            resultTextView.setText("1");
            System.err.println("name");
            Toast.makeText(getApplicationContext(), "Please make sure there are no numbers in the name", Toast.LENGTH_LONG).show();
        }

        if (!checkName(surnameData)) {
            valid = false;
            resultTextView.setText("1");
            System.err.println("surname");
            Toast.makeText(getApplicationContext(), "Please make sure there are no numbers in the name", Toast.LENGTH_LONG).show();
        }

        //creates user if valid
        if (valid) {

            resultTextView.setText("0");
            Toast.makeText(getApplicationContext(), "Adding user now", Toast.LENGTH_LONG).show();
            if (!isTest) {
                createUser(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData, agentData);
            } else {
                mockCreateUser(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData, agentData);
            }
        }


    }

    public boolean checkName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean checkNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void createUser(String usernameData, String passwordData, String nameData, String surnameData, String confirmPasswordData, String phonenumberData, String emailData, String agentData) {

        final String c_usernameData = usernameData, c_passwordData = passwordData, c_nameData = nameData, c_surnameData = surnameData, c_confirmPasswordData = confirmPasswordData, c_phonenumberData = phonenumberData, c_emailData = emailData, c_agentData = agentData;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resultTextView.setText(response);


                        //Success
                        if (response.contains("0")) {
                            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_CreatedUser_0), Toast.LENGTH_LONG).show();

                        } else if (response.contains("1")) {
                            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_Failed_1), Toast.LENGTH_LONG).show();
                        }

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
                params.put("USERNAME", c_usernameData);
                params.put("PASSWORD", c_passwordData);
                params.put("NAME", c_nameData);
                params.put("SURNAME", c_surnameData);
                params.put("EMAIL", c_emailData);
                params.put("PHONENUMBER", c_phonenumberData);
                params.put("USER_TYPE", c_agentData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void mockCreateUser(String usernameData, String passwordData, String nameData, String surnameData, String confirmPasswordData, String phonenumberData, String emailData, String agentData) {
        System.out.println("Create User Test");
    }

}
