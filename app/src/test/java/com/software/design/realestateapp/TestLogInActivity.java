package com.software.design.realestateapp;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void  loginUserTestable() throws Exception{
        LogInActivity t = Robolectric.setupActivity(LogInActivity.class);

        TextView resultTextView = (TextView) t.findViewById(R.id.textView_logIn_result);

        EditText username = (EditText) t.findViewById(R.id.editText_Username_login);
        EditText password = (EditText) t.findViewById(R.id.editText_Password_login);

        //Everything Correct
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(resultTextView.getText().toString(), "0");

        //Missing field
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_EMPTY_STRING);

        t.loginUserTestable(true);

        assertEquals(resultTextView.getText().toString(), "1");

        //Invalid Username
        username.setText(TEST_INVALID_USERNAME);
        password.setText(TEST_VALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(resultTextView.getText().toString(), "1");

        //Invalid Password
        username.setText(TEST_VALID_USERNAME);
        password.setText(TEST_INVALID_PASSWORD);

        t.loginUserTestable(true);

        assertEquals(resultTextView.getText().toString(), "1");
    }


}
