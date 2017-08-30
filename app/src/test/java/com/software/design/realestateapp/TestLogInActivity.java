package com.software.design.realestateapp;

import org.junit.Test;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * Created by kyle on 2017/08/30.
 */

public class TestLogInActivity  {

    private static final String TEST_USERNAME = "test123";
    private static final String TEST_PASSWORD = "password1";
    private static final String JSON_0 = "0";
    private static final String JSON_1 = "1";


    @Test
    public void processJSON_correct() throws Exception {
        assertThat(LogInActivity.processJSON(JSON_0), is(0));
        assertThat(LogInActivity.processJSON(JSON_1), is(1));
    }



}
