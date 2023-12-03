package com.github.creme332.model;

import com.github.creme332.model.Model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Model.
 */
public class ModelTest {
    Model m;

    @Before
    public void setup() {
        m = new Model();
    }

    public void checkDefaultModel() {
        assertTrue(m.getCursorPos() == 0);
        assertTrue(m.getStartTime() < 0);
        assertTrue(m.getTotalMistakes() == 0);
        assertTrue(m.getGameDuration() == 0);
        assertTrue(m.getTimeArray().length == 0);
        assertTrue(m.getWPMArray().length == 0);
    }

    @Test
    public void initializeModel() {
        checkDefaultModel();
    }

    @Test
    public void resetModel() {
        m.setGameDuration(1000);
        m.reset();
        checkDefaultModel();
    }

    @Test
    public void allowGameDurationZero() {
        m.setGameDuration(0);
        assertTrue(m.getGameDuration() == 0);
    }

    @Test
    public void rejectNegativeGameDuration() {
        m.setGameDuration(-1);
        assertTrue(m.getGameDuration() == 0);

        m.setGameDuration(-534);
        assertTrue(m.getGameDuration() == 0);
    }

    @Test
    public void incrementCursorByOne() {
        m.setTypeText("abcdefghijklmn");

        for (int i = 1; i < 3; i++) {
            m.incrementCursor();
            assertTrue(m.getCursorPos() == i);
        }
    }

    @Test
    public void stopIncrementingCursorAfterEndOfText() {
        m.setTypeText("abcdefghijklmn");

        for (int i = 1; i < m.getTypeText().length() + 5; i++) {
            m.incrementCursor();
        }

        assertTrue(m.getCursorPos() == m.getTypeText().length());
    }

    @Test
    public void returnCharWhenTextInitialized() {
        m.setTypeText("abcdefghijklmn");
        try {
            assertTrue(m.getCurrentChar() == 'a');

        } catch (Exception e) {
            fail("No exception should be thrown since text has been initialized");
        }
    }

    @Test
    public void returnPositiveTimeWhenTimerStarts() {
        m.initStartTime();

        assertTrue(m.getStartTime() > 0);
    }

    @Test
    public void throwErrorWhenTypeTextNotInitialised() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.getCurrentChar());

        assertEquals("typeText has not been initialized", exception.getMessage());

        m.setTypeText(null);
        exception = assertThrows(
                Exception.class,
                () -> m.getCurrentChar());

        assertEquals("typeText has not been initialized", exception.getMessage());
    }

    @Test
    public void throwErrorWhenCharPtrOutOfBounds() {
        m.setTypeText("a");
        m.incrementCursor();
        Exception exception = assertThrows(
                Exception.class,
                () -> m.getCurrentChar());

        assertEquals("charPtr is out of bounds", exception.getMessage());
    }

    @Test
    public void incrementTotalMistakes() {
        m.incrementMistakes();
        assertTrue(m.getTotalMistakes() == 1);

        m.incrementMistakes();
        assertTrue(m.getTotalMistakes() == 2);
    }

    @Test
    public void recordValidWPM() {
        double[] time = new double[] { 0, 1, 2, 3, 4, 5, 6 };
        double[] wpm = new double[] { 0, 30, 30, 40, 50, 60, 70 };
        for (int i = 0; i < time.length; i++) {
            try {
                m.recordWPM(time[i], wpm[i]);

            } catch (Exception e) {
                fail("No exception should be thrown since time and wpm values are valid.");
            }
        }

        double delta = 0.0001;
        assertArrayEquals(time, m.getTimeArray(), delta);
        assertArrayEquals(wpm, m.getWPMArray(), delta);
    }

    @Test
    public void throwErrorIfRecordNegativeTime() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.recordWPM(-1, 0));

        assertEquals("currentSecond cannot be negative", exception.getMessage());
    }

    @Test
    public void throwErrorIfRecordNegativeWPM() {
        Exception exception = assertThrows(
                Exception.class,
                () -> m.recordWPM(1, -23));

        assertEquals("wpm cannot be negative", exception.getMessage());
    }
}
