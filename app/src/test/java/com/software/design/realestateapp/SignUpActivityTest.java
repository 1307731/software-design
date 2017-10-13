package com.software.design.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by kyle on 2017/10/09.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SignUpActivityTest {

    final String TEST_VALID_USERNAME = "TEST";
    final String TEST_VALID_NUMBER = "1234";
    final String TEST_VALID_EMAIL = "TEST@TEST.TEST";
    final String TEST_VALID_NAME = "TEST";
    final String TEST_VALID_PASSWORD = "TEST";
    final String TEST_VALID_CONFIRM_PASSWORD = "TEST";

    final String TEST_INVALID_USERNAME = "TEST*";
    final String TEST_INVALID_NUMBER = "1234AB";
    final String TEST_INVALID_EMAIL = "TEST.TEST.TEST";
    final String TEST_INVALID_NAME = "TEST2";
    final String TEST_INVALID_PASSWORD = "TES";
    final String TEST_INVALID_CONFIRM_PASSWORD = "TEST2";
    final String TEST_EMPTY_STRING = "";

    final boolean TEST_AGENT = false;
    final String TEST_AGENT_DATA = "A";

    private String usernameData;
    private String passwordData;
    private String nameData;
    private String surnameData;
    private String confirmPasswordData;
    private String phonenumberData;
    private String emailData;
    private boolean agentBool;
    private String agentData;



    @Test
    public void checkCompletedFields() throws Exception {

        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        usernameData = TEST_VALID_USERNAME;
        passwordData = TEST_VALID_PASSWORD;
        nameData = TEST_VALID_NAME;
        surnameData = TEST_VALID_NAME;
        confirmPasswordData = TEST_VALID_CONFIRM_PASSWORD;
        phonenumberData = TEST_VALID_NUMBER;
        emailData = TEST_VALID_EMAIL;
        agentData = TEST_AGENT_DATA;



        // All valid
        assertThat(t.checkCompletedFields(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData),is(0));
        //Missing Entries
        assertThat(t.checkCompletedFields(usernameData, passwordData, nameData, surnameData, "", "", "", ""), is(1));
        //String too short, but none missing
        assertThat(t.checkCompletedFields(usernameData, passwordData, nameData, surnameData, ""), is(1));
        //Nothing entered
        assertThat(t.checkCompletedFields(""),is(1));
    }

    @Test
    public void signUpUserTestable() throws Exception{

        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);



        TextView resultTextView = (TextView) t.findViewById(R.id.textView_signUp_result);

        EditText name = (EditText) t.findViewById(R.id.editText_Name);
        EditText surname = (EditText) t.findViewById(R.id.editText_Surname);
        EditText password = (EditText) t.findViewById(R.id.editText_Password);
        EditText confirmPassword = (EditText) t.findViewById(R.id.editText_ConfirmPassword);
        EditText username = (EditText) t.findViewById(R.id.editText_Username);
        EditText email = (EditText) t.findViewById(R.id.editText_EmailAddress);
        EditText phonenumber = (EditText) t.findViewById(R.id.editText_PhoneNumber);
        CheckBox agent = (CheckBox) t.findViewById(R.id.checkBox_Agent);
        Button signUp = (Button) t.findViewById(R.id.button_SignUpSend);

        //Everything Correct
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"0");



        //Passwords do not match
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_INVALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"3");

        //Missing field
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_EMPTY_STRING);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

        //Invalid Email
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_INVALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

        //Invalid name
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_INVALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

        //Invalid Username
        username.setText(TEST_INVALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

        //Invalid phonenumber
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_INVALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

        //Invalid Passwords
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_INVALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked (TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(resultTextView.getText().toString(),"1");

    }

}