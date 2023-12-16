package com.github.creme332.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import com.github.creme332.model.Setting;

public class SettingsManagerTest {
    SettingsManager m;

    @Before
    public void setupSettingsManager() {
        m = new SettingsManager();
    }

    @Test
    public void returnInvalidOptionForInvalidKey() {
        assertTrue(m.getData("invalid-key").equals(Setting.invalidOption));
    }

    @Test
    public void returnValidOptionForValidKey() {
        assertTrue(Arrays.deepEquals(m.getSettings().get("Live speed").getOptions(), new String[] { "hide", "show" }));
    }

    @Test
    public void notStoreInvalidData() {
        String originalOption = m.getData("Live speed");
        // attempt to store invalid data
        m.setData("Live speed", "hidden");

        // original option must not change
        assertTrue(m.getData("Live speed").equals(originalOption));
    }

    @Test
    public void storeValidData() {
        String key = "Live speed";
        String originalOption = m.getData(key);

        m.setData(key, "hide");
        assertTrue(m.getData(key).equals("hide"));

        m.setData(key, "show");
        assertTrue(m.getData(key).equals("show"));

        // reinstate original option
        m.setData(key, originalOption);
    }

    @Test
    public void notStoreValidDataForInvalidKey() {
        String key = "Live speedzzz";

        m.setData(key, "hide");
        assertTrue(m.getData(key).equals(Setting.invalidOption));
    }

    @Test
    public void returnPositiveNumberOfSettings() {
        assertTrue(m.getSettings().keySet().size() > 0);
    }

}
