package com.codepath.testingdemo.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PostTestParameterized {

    private Post post;
    private String expectedResult;

    private static Post postWithLikers(String... userNames) {
        Post post = new Post();
        post.likers = Arrays.asList(userNames);
        return post;
    }

    @Parameterized.Parameters( name = "{index}: postWithLikers-{1}" )
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {postWithLikers("Steph", "Klay", "Andre"), "Steph, Klay, Andre"},
                {postWithLikers("Steph", "Klay", "Andre", "Lebron", "David", "Harrison"), "6 likes"},
                {postWithLikers(), ""}
        });
    }

    public PostTestParameterized(Post inputPost, String expectedResult) {
        this.post = inputPost;
        this.expectedResult = expectedResult;
    }

    @Test
    public void test() {
        assertEquals(expectedResult, post.formatLikersForDisplay("likes"));
    }
}