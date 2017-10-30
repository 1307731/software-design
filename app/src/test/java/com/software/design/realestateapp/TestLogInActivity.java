package com.software.design.realestateapp;

import android.content.Intent;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
/**
 * Created by kyle on 2017/08/30.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestLogInActivity  {

    private String TEST_VALID_USERNAME = "TEST";
    private String TEST_VALID_PASSWORD = "TEST";

    private String TEST_INVALID_USERNAME = "TEST*";
    private String TEST_INVALID_PASSWORD = "TES";
    private String TEST_EMPTY_STRING = "";

    @Test
    public void checkCompletedFields() throws Exception {

        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        String usernameData = TEST_VALID_USERNAME;
        String passwordData = TEST_VALID_PASSWORD;


        // All valid
        assertThat(t.checkCompletedFields(usernameData, passwordData), is(0));
        //Missing Entries
        assertThat(t.checkCompletedFields(usernameData, TEST_EMPTY_STRING), is(1));
        //Nothing entered
        assertThat(t.checkCompletedFields(TEST_EMPTY_STRING), is(1));
    }

    @Test
    public void loginUserTestable() throws Exception{
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);


        EditText username = (EditText) t.findViewById(R.id.editText_Username_login);
        EditText password = (EditText) t.findViewById(R.id.editText_Password_login);

        //Everything Correct
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(t.testReciever, "0");

        //Missing field
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_EMPTY_STRING);

        t.loginUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid Username
        username.setText(TEST_INVALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(t.testReciever, "1");

        //Invalid Password
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_INVALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(t.testReciever, "1");
    }

    @Test
    public void onSignUpClick(){
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        t.onSignUpClick(t.findViewById(R.id.button_signup));

        Intent expectedIntent = new Intent(t, SignUpActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void handleResponce() throws Exception {
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", TEST_VALID_USERNAME);
        params.put("PASSWORD", TEST_VALID_PASSWORD);

        String response1 = "0 10 moo moo";
        String response2 = "1 10 moo moo";


        t.handleResponce(response2, params, 1);

        assertEquals(t.testReciever, "Incorrect");

        t.handleResponce(response1, params, 1);

        assertEquals(t.testReciever, "0");

        Intent expectedIntent = new Intent(t, DrawerActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());

    }

    @Test
    public void handleError() throws Exception {
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        String error = "ERROR";

        t.handleError(error, 1);

        assertEquals(t.testReciever, "2");


    }

    @Test
    public void getContext() throws Exception {
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        assertEquals(t.getContext(), t);

    }

}
