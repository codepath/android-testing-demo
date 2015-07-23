package com.codepath.testingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.testingdemo.R;

/*
 * Take in a "message" as an intent extra. Use the message to set the TextView text.
 */
public class IntentTwoActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_two);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE);

        TextView tvMessage = (TextView)findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
