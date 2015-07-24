package com.codepath.testingdemo.activities;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.codepath.testingdemo.BuildConfig;
import com.codepath.testingdemo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static org.assertj.android.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class IntentOneActivityTest {

    private ActivityController<IntentOneActivity> controller;
    private IntentOneActivity activity;

    @Before
    public void setup() {
        controller = Robolectric.buildActivity(IntentOneActivity.class);
    }

    private void createWithIntent(String message) {
        Intent intent = new Intent(RuntimeEnvironment.application, IntentOneActivity.class);
        intent.putExtra(IntentOneActivity.EXTRA_MESSAGE, message);

        activity = controller
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void textViewSetWithIntentMessage() {
        String firstActivityMessage = "First Activity";

        createWithIntent(firstActivityMessage);
        TextView tvMessage = (TextView)activity.findViewById(R.id.tvMessage);

        assertThat(tvMessage).hasText(firstActivityMessage);
    }

    @Test
    public void secondActivityStartedOnNextClick() {
        String firstActivityMessage = "First Activity";

        createWithIntent(firstActivityMessage);

        activity.findViewById(R.id.btnNext).performClick();

        Intent expectedIntent = new Intent(activity, IntentTwoActivity.class);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        // Determine if two intents are the same for the purposes of intent resolution (filtering).
        // That is, if their action, data, type, class, and categories are the same. This does
        // not compare any extra data included in the intents
        assertTrue(actualIntent.filterEquals(expectedIntent));

        // Make sure extra is included and correct
        assertThat(actualIntent).hasExtra(IntentTwoActivity.EXTRA_MESSAGE);
        assertTrue(actualIntent.getStringExtra(IntentTwoActivity.EXTRA_MESSAGE)
                .equals("Second Activity"));
    }
}
