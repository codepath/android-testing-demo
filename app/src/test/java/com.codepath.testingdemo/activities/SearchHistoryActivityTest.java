package com.codepath.testingdemo.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.testingdemo.BuildConfig;
import com.codepath.testingdemo.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.assertj.core.api.Assertions.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class SearchHistoryActivityTest {

    private ActivityController<SearchHistoryActivity> controller;
    private SearchHistoryActivity activity;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(SearchHistoryActivity.class);
        activity = controller
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @After
    public void tearDown() {
        controller.destroy();
    }

    @Test
    public void testRecreatesActivity() {

        EditText etSearch = (EditText)activity.findViewById(R.id.etSearch);
        Button btnSubmit = (Button) activity.findViewById(R.id.btnSubmit);

        etSearch.setText("Justin Bieber");
        btnSubmit.performClick();

        etSearch.setText("tswift");
        btnSubmit.performClick();

        assertThat(activity.previousSearches).hasSize(2);


        Bundle bundle = new Bundle();

        // Simulate low memory scenario where activity is reclaimed and recreated
        controller.saveInstanceState(bundle).pause().stop().destroy();
        controller = Robolectric.buildActivity(SearchHistoryActivity.class)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle).resume();
        activity = controller.get();

        assertThat(activity.previousSearches).hasSize(2);
    }
}