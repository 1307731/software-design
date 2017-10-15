package com.software.design.realestateapp;

import android.content.Intent;
import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;

/**
 * Created by kyle on 2017/10/15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWelcomeScreen {
    @Test
    public void onCreate() throws Exception {

        WelcomeScreen t = Robolectric.setupActivity(WelcomeScreen.class);

        t.onCreate(Bundle.EMPTY);

        Intent expectedIntent = new Intent(t, LogInActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}