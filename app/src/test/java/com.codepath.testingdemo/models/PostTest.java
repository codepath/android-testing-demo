package com.codepath.testingdemo.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

// JUnit tests
public class PostTest {

    private final String NUMERIC_SUFFIX = "likes";

    private static Post postWithLikers(String... userNames) {
        Post post = new Post();
        post.likers = Arrays.asList(userNames);
        return post;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code executed before the first test method
    }

    @Before
    public void setUp() throws Exception {
        // Code executed before each test
    }

    @Test
    public void formatLikersWithThreeLikers() {
        // Setup input
        Post post = postWithLikers("Steph", "Klay", "Andre");

        // Method under test
        String formattedLikers = post.formatLikersForDisplay(NUMERIC_SUFFIX);

        // Validation
        assertEquals("Steph, Klay, Andre", formattedLikers);
    }

    @Test
    public void formatLikersWithZeroLikers() {
        Post post = postWithLikers();
        String formattedLikers = post.formatLikersForDisplay(NUMERIC_SUFFIX);
        assertEquals("", formattedLikers);
    }

    @Test
    public void formatLikersWithSixLikers() {
        Post post = postWithLikers("Steph", "Klay", "Andre", "Lebron", "David", "Harrison");
        String formattedLikers = post.formatLikersForDisplay(NUMERIC_SUFFIX);
        assertEquals("6 likes", formattedLikers);
    }

    @Test(expected=IllegalArgumentException.class)
    public void missingNumericSuffixThrowsIllegalArgumentException() {
        Post post = postWithLikers("Steph", "Klay", "Andre", "Lebron", "David", "Harrison");
        post.formatLikersForDisplay(null);
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code executed after the last test method
    }
}