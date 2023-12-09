package com.github.creme332.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
    Calculator m = new Calculator();
    double wpm, accuracy;

    @Test
    public void returnCorrectWPMForValidInputs() {
        wpm = m.wpm(100, 100);
        assertEquals(12, wpm, 0.001);
    }

    @Test
    public void returnCorrectAccuracyForValidInputs() {
        accuracy = m.accuracy(1000, 5);
        assertEquals(99.5, accuracy, 0.001);
    }

    @Test
    public void returnZeroWPMForInvalidInputs() {
        wpm = m.wpm(100, -10);
        assertEquals(0, wpm, 0.001);

        wpm = m.wpm(-10, 10);
        assertEquals(0, wpm, 0.001);

        wpm = m.wpm(10, 0);
        assertEquals(0, wpm, 0.001);
    }

    @Test
    public void returnZeroAccuracyForInvalidInputs() {
        accuracy = m.accuracy(0, 100);
        assertEquals(0, accuracy, 0.001);

        accuracy = m.accuracy(0, -100);
        assertEquals(0, accuracy, 0.001);

        accuracy = m.accuracy(0, 0);
        assertEquals(0, accuracy, 0.001);
    }
}
