package com.github.creme332;

import com.github.creme332.utils.WordGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Word Generator.
 */
public class WordGeneratorTest {
    WordGenerator m;

    @Before
    public void setup() {
        m = new WordGenerator();
    }

    @Test
    public void getTenWords() {
        try {
            int wordCount = 10;
            String a = m.getRandomText(wordCount);
            int spaceCount = a.length() - a.replaceAll(" ", "").length();
            assertTrue(a.length() > 0 && spaceCount == wordCount - 1);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void throwErrorForNegativeWordCount() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.getRandomText(-100));

        assertEquals("Word count must be a positive value", exception.getMessage());
    }

    @Test
    public void throwErrorForLargeWordCount() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.getRandomText(1000000));

        assertEquals("Word count must be between 1 and 2000 inclusive", exception.getMessage());
    }

}
