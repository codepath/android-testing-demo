package com.codepath.testingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codepath.testingdemo.R;

/*
 * Display a TextView with "Hello World!" in the user's native language (as long as that language
 * is English, French, or Spanish).
 */
public class LocalizedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localized);
    }
}
