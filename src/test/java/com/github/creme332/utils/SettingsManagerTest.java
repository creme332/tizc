package com.github.creme332.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class SettingsManagerTest {
    SettingsManager m;

    @Before
    public void setupSettingsManager() {
        m = new SettingsManager();
    }

    @Test
    public void returnInvalidOption() {
        assertTrue(m.getData("invalid-key") == SettingsManager.invalidOption);
    }

    @Test
    public void returnValidOptionForValidKey() {
        assertTrue(Arrays.deepEquals(m.getSettings().get("Live speed"), new String[] { "hide", "show" }));
    }

    @Test
    public void notStoreInvalidData() {
        String originalOption = m.getData("Live speed");
        // attempt to store invalid data
        m.setData("Live speed", "hidden");

        // original option must not change
        assertTrue(m.getData("Live speed") == originalOption);
    }

    @Test
    public void storeValidData() {
        String key = "Live speed";
        String originalOption = m.getData(key);

        m.setData(key, "hide");
        assertTrue(m.getData(key) == "hide");

        m.setData(key, "show");
        assertTrue(m.getData(key) == "show");

        // reinstate original option
        m.setData(key, originalOption);
    }

    @Test
    public void notStoreValidDataForInvalidKey() {
        String key = "Live speedzzz";

        m.setData(key, "hide");
        assertTrue(m.getData(key) == SettingsManager.invalidOption);
    }

    @Test
    public void returnFiveSettings() {
        assertTrue(m.getSettings().keySet().size() == 5);
    }

}
