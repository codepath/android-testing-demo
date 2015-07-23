package com.codepath.testingdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.testingdemo.R;
import com.google.common.base.Joiner;

import java.util.ArrayList;

/*
 * Keep track of recent searches and allow user to share thesee
 * with a friend.
 */
public class SearchHistoryActivity extends AppCompatActivity {

    ArrayList<String> previousSearches;

    EditText etSearch;
    TextView tvSearchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        tvSearchHistory = (TextView)findViewById(R.id.tvSearchHistory);
        etSearch = (EditText)findViewById(R.id.etSearch);
    }

    // User tapped Submit button
    public void handleSubmit(View view) {
        String searchText = etSearch.getText().toString();

        // Clear search box
        etSearch.setText("");

        // Add search to search history
        tvSearchHistory.append(searchText);
        tvSearchHistory.append("\n");

        // Lazy initialization of previous searches
        if (previousSearches == null) {
            previousSearches = new ArrayList<>();
        }

        // Record previous searches for sharing purposes
        previousSearches.add(searchText);
    }

    // User tapped Search History button
    public void onShareSearchHistory(View view) {
        String searchesToShare = Joiner.on(", ").join(previousSearches);

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, searchesToShare);

        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}
