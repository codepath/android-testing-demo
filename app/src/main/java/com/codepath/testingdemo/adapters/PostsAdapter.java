package com.codepath.testingdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.testingdemo.R;
import com.codepath.testingdemo.models.Post;

import java.util.ArrayList;
import java.util.List;

/*
 * Simple Adapter that populates a single textView that shows a userName / caption in each item
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostItemViewHolder> {
    private static final String TAG = "InstagramPostsAdapter";

    private List<Post> posts;

    public PostsAdapter(List<Post> posts) {
        this.posts = (posts == null ? new ArrayList<Post>() : posts);
    }

    @Override
    public PostItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item_post, viewGroup, false);
        return new PostItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostItemViewHolder postItemViewHolder, int position) {
        final Post post = posts.get(position);

        if (!TextUtils.isEmpty(post.caption)) {
            postItemViewHolder.tvCaption.setText(
                    String.format("%s: %s", post.userName, post.caption));
        } else {
            postItemViewHolder.tvCaption.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public static final class PostItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCaption;

        public PostItemViewHolder(View itemView) {
            super(itemView);

            tvCaption = (TextView) itemView.findViewById(R.id.tvCaption);
        }
    }
}