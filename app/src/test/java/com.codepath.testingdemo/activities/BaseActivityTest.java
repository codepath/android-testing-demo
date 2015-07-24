package com.codepath.testingdemo.activities;

import android.content.Intent;
import android.os.Build;

import com.codepath.testingdemo.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BaseActivityTest {

    private ActivityController<MainActivity> controller;
    private MainActivity activity;

    @Before
    public void setUp() {
        // Return ActivityController that can be used to create Activity
        controller = Robolectric.buildActivity(MainActivity.class);
    }

    @After
    public void tearDown() {
        // Destory activity in every test
        controller.destroy();
    }

    // Activity creation that allows intent extras to be pass in
    private void createWithIntent(String extra) {
        Intent intent = new Intent(RuntimeEnvironment.application, IntentOneActivity.class);
        intent.putExtra("activity_extra", extra);

        activity = controller
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void createsAndDestroysActivity() {
        createWithIntent("my extra_value");
        // ... add assertions ...
    }

    // Simulates phone call during app usage type of scenario
    @Test
    public void pausesAndResumesActivity() {
        createWithIntent("my extra_value");
        controller.pause().resume();
        // ... add assertions ...
    }

    // Simulates device rotation
    @Test
    public void recreatesActivity() {
        createWithIntent("my extra_value");
        activity.recreate();
        // ... add assertions ...
    }
}