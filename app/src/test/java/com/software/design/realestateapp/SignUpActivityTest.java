package com.software.design.realestateapp;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by kyle on 2017/09/21.
 */
public class SignUpActivityTest {

    static final String TEST_STRING = "Test String";
    static final String EMPTY_STRING = " ";

    @Test
    public void checkCompletedFields() throws Exception {
        assertThat(SignUpActivity.checkCompletedFields(TEST_STRING, TEST_STRING), is(0));
        assertThat(SignUpActivity.checkCompletedFields(TEST_STRING, EMPTY_STRING), is(1));
    }

    /*@Test
    public void signUpUser() throws Exception {
        assertThat(SignUpActivity.signUpUserTestable(true), is(0));
    }*/

}