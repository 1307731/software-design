package com.software.design.realestateapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by kyle on 2017/10/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestDrawerActivity {

    @Test
    public void onCreate() throws Exception {

        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        assertNotNull(t);
    }

    /*
    @Test
    public void onNavigationItemSelected() throws Exception{

        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        //News feed
        MenuItem menuItem = new RoboMenuItem(R.id.nav_newsFeed);
        t.onNavigationItemSelected(menuItem);
        FragmentManager fragmentManager = t.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.drawerContentFrame);

        NewsFeed newsFeed = new NewsFeed();
        assertEquals(t.getFragmentManager().findFragmentById(R.id.drawerContentFrame), );
    }
    */
}