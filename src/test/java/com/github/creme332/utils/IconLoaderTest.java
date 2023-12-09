package com.github.creme332.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

public class IconLoaderTest {
    IconLoader m = new IconLoader();

    @Test
    public void notThrowErrorForValidPath() {
        try {
            m.loadIcon("/bg.jpg");

        } catch (Exception e) {
            System.out.println(e);
            fail("No exception should be thrown since path starts with /");
        }
    }

    @Test
    public void notThrowErrorForValidPathAndIconSize() {
        try {
            m.loadIcon("/bg.jpg", 100);

        } catch (Exception e) {
            System.out.println(e);
            fail("No exception should be thrown since path starts with / and icon size is positive");
        }
    }

    @Test
    public void throwErrorForValidIconSizeAndInvalidPath() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.loadIcon("sad/", 10));

        assertEquals("Path should start with /", exception.getMessage());

    }

    @Test
    public void throwErrorIfPathEmpty() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.loadIcon(""));

        assertEquals("Path should start with /", exception.getMessage());

    }

    @Test
    public void throwErrorIfPathNotStartWithSlash() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.loadIcon("images/"));

        assertEquals("Path should start with /", exception.getMessage());
    }

    @Test
    public void throwErrorIfIconSizeInvalid() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.loadIcon("/bg.jpg", -2));

        assertEquals("Icon size must be a positive integer", exception.getMessage());
    }

}
