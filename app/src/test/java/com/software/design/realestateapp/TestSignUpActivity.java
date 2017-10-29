package com.software.design.realestateapp;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by kyle on 2017/10/09.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestSignUpActivity {

    private String TEST_VALID_USERNAME = "TEST";
    private String TEST_VALID_NUMBER = "1234";
    private String TEST_VALID_EMAIL = "TEST@TEST.TEST";
    private String TEST_VALID_NAME = "TEST";
    private String TEST_VALID_PASSWORD = "TEST";
    private String TEST_VALID_CONFIRM_PASSWORD = "TEST";

    private String TEST_INVALID_USERNAME = "TEST*";
    private String TEST_INVALID_NUMBER = "1234AB";
    private String TEST_INVALID_EMAIL = "TEST.TEST.TEST";
    private String TEST_INVALID_NAME = "TEST2";
    private String TEST_INVALID_PASSWORD = "TES";
    private String TEST_INVALID_CONFIRM_PASSWORD = "TEST2";
    private String TEST_EMPTY_STRING = "";

    private boolean TEST_AGENT = false;
    private String TEST_AGENT_DATA = "A";



    @Test
    public void checkCompletedFields() throws Exception {

        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        String usernameData = TEST_VALID_USERNAME;
        String passwordData = TEST_VALID_PASSWORD;
        String nameData = TEST_VALID_NAME;
        String surnameData = TEST_VALID_NAME;
        String confirmPasswordData = TEST_VALID_CONFIRM_PASSWORD;
        String phonenumberData = TEST_VALID_NUMBER;
        String emailData = TEST_VALID_EMAIL;
        String agentData = TEST_AGENT_DATA;


        // All valid
        assertThat(t.checkCompletedFields(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData), is(0));
        //Missing Entries
        assertThat(t.checkCompletedFields(usernameData, passwordData, nameData, surnameData, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING, TEST_EMPTY_STRING), is(1));
        //Nothing entered
        assertThat(t.checkCompletedFields(TEST_EMPTY_STRING), is(1));
    }

    @Test
    public void signUpUserTestable() throws Exception {

        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        EditText name = (EditText) t.findViewById(R.id.editText_Name_signup);
        EditText surname = (EditText) t.findViewById(R.id.editText_Surname_signup);
        EditText password = (EditText) t.findViewById(R.id.editText_Password_signup);
        EditText confirmPassword = (EditText) t.findViewById(R.id.editText_ConfirmPassword_signup);
        EditText username = (EditText) t.findViewById(R.id.editText_Username_signup);
        EditText email = (EditText) t.findViewById(R.id.editText_EmailAddress_signup);
        EditText phonenumber = (EditText) t.findViewById(R.id.editText_PhoneNumber_signup);
        CheckBox agent = (CheckBox) t.findViewById(R.id.checkBox_Agent_signup);
        Button signUp = (Button) t.findViewById(R.id.button_SignUpSend);

        //Everything Correct
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "0");


        //Passwords do not match
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_INVALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "3");

        //Missing field
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_EMPTY_STRING);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid Email
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_INVALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid name
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_INVALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid surname
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_INVALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid Username
        username.setText(TEST_INVALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid phonenumber
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_INVALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid Password
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_INVALID_PASSWORD);
        name.setText(TEST_VALID_NAME);
        surname.setText(TEST_VALID_NAME);
        confirmPassword.setText(TEST_VALID_CONFIRM_PASSWORD);
        phonenumber.setText(TEST_VALID_NUMBER);
        email.setText(TEST_VALID_EMAIL);
        agent.setChecked(TEST_AGENT);

        t.signUpUserTestable(true);

        assertEquals(t.testReciever, "1");

    }

    @Test
    public void handleResponce() throws Exception {
        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", TEST_VALID_USERNAME);
        params.put("PASSWORD", TEST_VALID_PASSWORD);
        params.put("NAME", TEST_VALID_NAME);
        params.put("SURNAME", TEST_VALID_NAME);
        params.put("EMAIL", TEST_VALID_EMAIL);
        params.put("PHONENUMBER", TEST_VALID_NUMBER);
        params.put("USER_TYPE", "A");

        String response1 = "0";
        String response2 = "1";

        t.handleResponce(response1, params, 1);

        assertEquals(t.testReciever, "0");

        t.handleResponce(response2, params, 1);

        assertEquals(t.testReciever, "1");
    }

    @Test
    public void handleError() throws Exception {
        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        String error = "ERROR";

        t.handleError(error, 1);

        assertEquals(t.testReciever, "2");


    }

    @Test
    public void getContext() throws Exception {
        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        assertEquals(t.getContext(), t);

    }

    @Test
    public void createUser() throws Exception {
        SignUpActivity t = Robolectric.setupActivity(SignUpActivity.class);

        String usernameData = TEST_VALID_USERNAME;
        String passwordData = TEST_VALID_PASSWORD;
        String nameData = TEST_VALID_NAME;
        String surnameData = TEST_VALID_NAME;
        String confirmPasswordData = TEST_VALID_CONFIRM_PASSWORD;
        String phonenumberData = TEST_VALID_NUMBER;
        String emailData = TEST_VALID_EMAIL;
        String agentData = TEST_AGENT_DATA;

        t.createUser(usernameData, passwordData, nameData, surnameData, confirmPasswordData, phonenumberData, emailData, agentData, true);

        assertEquals(t.testReciever, "CreateUser");
    }

}