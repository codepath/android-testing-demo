package com.codepath.testingdemo.activities;

import android.os.Build;
import android.widget.TextView;

import com.codepath.testingdemo.BuildConfig;
import com.codepath.testingdemo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import static org.assertj.android.api.Assertions.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class LocalizedActivityTest {

    private TextView tvHelloWorld;

    @Before
    public void setup() {
        // Convenience method to create ActivityController and run thru the Activity Lifecycle
        // methods: create().start().postCreate(null).resume().visible()
        // Followed by a get() to return the activity
        LocalizedActivity activity = Robolectric.setupActivity(LocalizedActivity.class);
        tvHelloWorld = (TextView)activity.findViewById(R.id.tvHelloWorld);
    }

    @Test
    @Config(qualifiers = "es")
    public void localizedSpanishHelloWorld() {
        assertThat(tvHelloWorld).hasText("Hola Mundo!");
    }

    @Test
    @Config(qualifiers = "fr")
    public void localizedFrenchHelloWorld() {
        assertThat(tvHelloWorld).hasText("Bonjour le monde!");
    }
}
