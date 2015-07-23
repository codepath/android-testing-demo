package com.codepath.testingdemo.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.testingdemo.R;

/*
 * Single button that kicks off an AsyncTask (simulating a network request) and updates
 * the button text once "request" is complete.
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
    }

    public void runAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(5000); // 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                Toast.makeText(AsyncTaskActivity.this, "Done", Toast.LENGTH_SHORT).show();
                btnSubmit.setText("Done");
            }
        }.execute();
    }

    public void handleSubmit(View view) {
        Toast.makeText(this, "Sending request...", Toast.LENGTH_SHORT).show();
        runAsyncTask();
    }
}
