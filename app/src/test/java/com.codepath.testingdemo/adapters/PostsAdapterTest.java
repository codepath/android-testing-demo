package com.codepath.testingdemo.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codepath.testingdemo.BuildConfig;
import com.codepath.testingdemo.models.Post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.android.recyclerview.v7.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class PostsAdapterTest {

    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void postsAdapterViewRecyclingCaption() {
        // Set up input
        List<Post> posts = Arrays.asList(
                new Post("Lebron", null),
                new Post("Steph", "We Won!!!")
        );


        PostsAdapter adapter = new PostsAdapter(posts);

        RecyclerView rvParent = new RecyclerView(context);
        rvParent.setLayoutManager(new LinearLayoutManager(context));

        // Run test
        PostsAdapter.PostItemViewHolder viewHolder =
                adapter.onCreateViewHolder(rvParent, 0);

        adapter.onBindViewHolder(viewHolder, 0);

        // JUnit Assertion
        assertEquals(View.GONE, viewHolder.tvCaption.getVisibility());

        // AssertJ-Android Assertion
        assertThat(viewHolder.tvCaption).isGone();

        adapter.onBindViewHolder(viewHolder, 1);

        // JUnit Assertion
        assertEquals("Steph: We Won!!!", viewHolder.tvCaption.getText().toString());

        // AssertJ-Android Assertion
        assertThat(viewHolder.tvCaption).isVisible().containsText("Won");

        assertThat(adapter).hasItemCount(2);
    }
}