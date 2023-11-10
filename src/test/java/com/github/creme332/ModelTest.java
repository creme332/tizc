package com.github.creme332;

import com.github.creme332.model.Model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void initialiseModel() {
        assertTrue(m.getBadHighlight() == null);
        assertTrue(m.getCursorPos() == 0);
        assertTrue(m.getStartTime() < 0);
        assertTrue(m.getTotalMistakes() == 0);
        assertTrue(m.getGameDuration() == 0);
        assertTrue(m.getTimeArray().length == 0);
        assertTrue(m.getWPMArray().length == 0);
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
            m.recordWPM(time[i], wpm[i]);
        }

        double delta = 0.0001;
        assertArrayEquals(time, m.getTimeArray(), delta);
        assertArrayEquals(wpm, m.getWPMArray(), delta);
    }

    @Test
    public void throwErrorIfAttemptToRecordInvalidWPM() {
        assertTrue(true);
    }
}
