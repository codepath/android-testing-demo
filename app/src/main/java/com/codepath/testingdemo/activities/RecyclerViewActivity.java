package com.codepath.testingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codepath.testingdemo.R;
import com.codepath.testingdemo.adapters.PostsAdapter;
import com.codepath.testingdemo.models.Post;

import java.util.Arrays;
import java.util.List;

/*
 * Simple RecyclerView that shows a userName / caption in each item
 */
public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.rvPosts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setHasFixedSize(true);

        List<Post> posts = getPosts();
        rvPosts.setAdapter(new PostsAdapter(posts));
    }

    private List<Post> getPosts() {
        return Arrays.asList(
                new Post("Steph", "We Won!!!"),
                new Post("Lebron", "No comment"),
                new Post("Andre", "Yes!")
        );
    }
}
