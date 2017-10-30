package com.software.design.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements VolleyResponce {

    //declare on screen element variables
    Button signUp;
    EditText name, surname, password, confirmPassword, username, email, phonenumber;
    CheckBox agent;
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
        //php urlCheck for insert
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertUser.php";


        testReciever = new String();
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
            testReciever = "4";
            
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_FieldsIncomplete_4), Toast.LENGTH_LONG).show();
        }

        //checks if passwords match

        if (!passwordData.equals(confirmPasswordData)) {
            //Passwords do not match
            valid = false;
            testReciever = "3";
            
            Toast.makeText(getApplicationContext(), getString(R.string.SignUp_PasswordNoMatch_3), Toast.LENGTH_LONG).show();
        }

        //checks if email is valid
        if (!emailData.contains("@")) {
            //checks if email has a @ symbol
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Email is invalid", Toast.LENGTH_LONG).show();
        }

        if (usernameData.contains("*")) {
            //checks if username has a * symbol
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Username is invalid", Toast.LENGTH_LONG).show();
        }

        //checks if number contains only digits, android should cause this by default
        if (!checkNumber(phonenumberData)) {
            //checks if phone has only digits
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Phone number is invalid", Toast.LENGTH_LONG).show();
        }

        if (passwordData.length() <= 3) {
            //checks if phone has only digits
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
        }

        if (!checkName(nameData)) {
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Please make sure there are no numbers in the name", Toast.LENGTH_LONG).show();
        }

        if (!checkName(surnameData)) {
            valid = false;
            testReciever = "1";
            
            Toast.makeText(getApplicationContext(), "Please make sure there are no numbers in the name", Toast.LENGTH_LONG).show();
        }

        //creates user if valid
        if (valid) {

            testReciever = "0";
            Toast.makeText(getApplicationContext(), "Adding user now", Toast.LENGTH_LONG).show();
            if (!isTest) {
                createUser(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData, agentData, isTest);
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

    public void createUser(String usernameData, String passwordData, String nameData, String surnameData, String confirmPasswordData, String phonenumberData, String emailData, String agentData, boolean isTest) {


        Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", usernameData);
        params.put("PASSWORD", passwordData);
        params.put("NAME", nameData);
        params.put("SURNAME", surnameData);
        params.put("EMAIL", emailData);
        params.put("PHONENUMBER", phonenumberData);
        params.put("USER_TYPE", agentData);

        int key = 1;
        VolleyRequest volleyRequest = new VolleyRequest(url, params, this, key);
        if (!isTest) {
            volleyRequest.makeRequest();
        } else {
            testReciever = "CreateUser";

        }
    }

    public void mockCreateUser(String usernameData, String passwordData, String nameData, String surnameData, String confirmPasswordData, String phonenumberData, String emailData, String agentData) {
        System.out.println("Create User Test");
    }

    @Override
    public void handleResponce(Object response, Map<String, String> map, int key) {
        if (key == 1) {
            String c_response = (String) response;
            testReciever = c_response;


            //Success
            if (c_response.contains("0")) {
                Toast.makeText(getApplicationContext(), getString(R.string.SignUp_CreatedUser_0), Toast.LENGTH_LONG).show();
                testReciever = "0";
                Intent nextIntent = new Intent(this, LogInActivity.class);
                startActivity(nextIntent);

            } else if (c_response.contains("1")) {
                Toast.makeText(getApplicationContext(), getString(R.string.SignUp_Failed_1), Toast.LENGTH_LONG).show();
                testReciever = "1";
            } else if (c_response.contains("5")) {
                Toast.makeText(getApplicationContext(), "Please enter a unique email and username", Toast.LENGTH_LONG).show();
                testReciever = "5";
            }
        }
    }

    @Override
    public void handleError(Object error, int key) {
        if (key == 1) {
            testReciever = "2";
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            testReciever = "2";
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
