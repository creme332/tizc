package com.github.creme332.utils;

import org.junit.Test;
import static org.junit.Assert.fail;

public class PoppinsFontTest {

    @Test
    public void shouldNotThrowError() {
        try {
            new PoppinsFont();
        } catch (Exception e) {
            fail("No error should be thrown");
        }
    }
}
