package com.software.design.realestateapp;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Created by kyle on 2017/09/18.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SignUpActivityTestInstrumentation {

    public static final String TEST_STRING = "Hello";
    public static final int RESET_VALUE = -1;






    @Rule
    public ActivityTestRule<SignUpActivity> mActivityRule = new ActivityTestRule<>(
            SignUpActivity.class);

    @Test
    public void signUpUserCompleteFields() throws Exception {
        TextView result;
        result = (TextView)mActivityRule.getActivity().findViewById(R.id.textView_signUp_result);
        //result.setText(RESET_VALUE);

        onView(withId(R.id.editText_Name))              .perform(typeText(TEST_STRING));
        onView(withId(R.id.editText_Surname))           .perform(typeText(TEST_STRING));
        onView(withId(R.id.editText_Username))          .perform(typeText(TEST_STRING));
        onView(withId(R.id.editText_EmailAddress))      .perform(typeText(TEST_STRING));
        onView(withId(R.id.editText_PhoneNumber))       .perform(typeText("12345"));
        //onView(withId(R.id.editText_Password))          .perform(typeText(TEST_STRING));
        onView(withId(R.id.editText_ConfirmPassword))   .perform(typeText(TEST_STRING),closeSoftKeyboard());

        onView(withId(R.id.button_SignUpSend)).perform(click());


        onView(withId(R.id.textView_signUp_result)).check(matches(withText("4")));


    }

}