package com.codepath.testingdemo.models;

import com.google.common.base.Joiner;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

public class Post implements Serializable {

    public String userName;
    public String caption;

    public Post() {
        // do nothing
    }

    public Post(String userName, String caption) {
        this.userName = userName;
        this.caption = caption;
    }

    // Names of people that have liked this post
    public List<String> likers;

    /**
     * Returns a string representation of the likers
     * that can be used for display.
     *
     * Rules:
     *   0 likers => Empty string
     *   1 - 5 likers => Comma separated userNames (i.e. Bob, Nancy, Joe)
     *   > 5 likers => Comma separated number plus numericSuffix (i.e. 1,234 likes)
     */
    public String formatLikersForDisplay(String numericSuffix) {
        String result;

        if (likers == null || likers.isEmpty()) {
            result = "";
        } else if (likers.size() <= 5) {
            result = Joiner.on(", ").join(likers);
        } else {
            if (numericSuffix == null) {
                throw new IllegalArgumentException("No numericSuffix specified for likers format string");
            }
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setGroupingUsed(true);
            result = String.format("%s %s", numberFormat.format(likers.size()), numericSuffix);
        }
        return result;
    }
}
