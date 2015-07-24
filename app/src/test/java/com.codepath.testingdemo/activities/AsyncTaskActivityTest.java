package com.codepath.testingdemo.activities;

import android.os.Build;
import android.widget.Button;

import com.codepath.testingdemo.BuildConfig;
import com.codepath.testingdemo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class AsyncTaskActivityTest {

    private AsyncTaskActivity activity;

    @Before
    public void setUp() {
        // Convenience method to create ActivityController and run thru the Activity Lifecycle
        // methods: create().start().postCreate(null).resume().visible()
        // Followed by a get() to return the activity
        activity = Robolectric.setupActivity(AsyncTaskActivity.class);
    }

    @Test
    public void recreatesActivity() {
        // Simulate click
        Button btnSubmit = (Button) activity.findViewById(R.id.btnSubmit);
        btnSubmit.performClick();

        // This results in essentially the same flow as when the Activity is created due to a
        // configuration change -- the current instance will go through its lifecycle to onDestroy()
        // and a new instance then created after it
        activity.recreate();

        // Validation - wait for background tasks to finish (i.e. AsyncTask)
        ShadowApplication.runBackgroundTasks();

        // Verify button text
        String buttonText = ((Button) activity.findViewById(R.id.btnSubmit))
                .getText().toString();
        assertEquals("Done", buttonText);
    }
}