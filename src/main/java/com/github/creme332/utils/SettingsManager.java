package com.github.creme332.utils;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;

import com.github.creme332.model.Setting;

public class SettingsManager {
    private Map<String, Setting> settingDict = new HashMap<String, Setting>();

    public SettingsManager() {
        // initialize all settings and their possible options
        FontIcon icon = FontIcon.of(BootstrapIcons.SPEEDOMETER, 20, Color.white);
        Setting s = new Setting("Mode", new String[] { "word", "quote" },
                "In word mode you must type a list random words whereas in quote mode, you are given a random quote. The number of words in each mode is determined by the difficulty setting.",
                icon);
        settingDict.put(
                "Mode",
                s);

        icon = FontIcon.of(BootstrapIcons.STAR_FILL, 20, Color.white);
        s = new Setting("Difficulty", new String[] { "easy", "medium", "hard" },
                "Normal is the classic type test experience. Expert fails the test if you submit (press space) an incorrect word. Master fails if you press a single incorrect key (meaning you have to achieve 100% accuracy).",
                icon);
        settingDict.put("Difficulty", s);

        icon = FontIcon.of(BootstrapIcons.SPEEDOMETER, 20, Color.white);
        s = new Setting("Live speed", new String[] { "hide", "show" },
                "Displays a live speed during the test. Updates once every second.", icon);
        settingDict.put("Live speed", s);

        icon = FontIcon.of(BootstrapIcons.CURSOR, 20, Color.white);
        s = new Setting("Live accuracy", new String[] { "hide", "show" }, "Displays live accuracy during the test.",
                icon);
        settingDict.put("Live accuracy", s);

        icon = FontIcon.of(BootstrapIcons.STOPWATCH, 20, Color.white);
        s = new Setting("Live timer", new String[] { "hide", "show" },
                "Displays a live timer for timed tests and word count for word based tests (word, quote or custom mode).",
                icon);
        settingDict.put("Live timer", s);

        icon = FontIcon.of(BootstrapIcons.MOON, 20, Color.white);
        s = new Setting("Lazy mode", new String[] { "off", "on" },
                "Replaces accents / diacritics / special characters with their normal letter equivalents.", icon);
        settingDict.put("Lazy mode", s);

        icon = FontIcon.of(BootstrapIcons.SPEAKER, 20, Color.white);
        s = new Setting("Typing sound", new String[] { "off", "on" },
                "Plays a short sound when you press a key.", icon);
        settingDict.put("Typing sound", s);
    }

    /**
     * 
     * @return A dictionary containing all settings and their valid options
     */
    public Map<String, Setting> getSettings() {
        return settingDict;
    }

    /**
     * Validate setting key
     * 
     * @param key Setting key
     * @return Boolean value indicating whether setting key is valid
     */
    private boolean validateKey(String key) {
        for (String c : settingDict.keySet()) {
            if (c.equals(key))
                return true;
        }

        return false;
    }

    /**
     * Get data stored for a setting.
     * 
     * @param key setting key
     * @return Data stored at particular key. If invalid key, returns invalidOption.
     */
    public String getData(String key) {
        return settingDict.get(key).getData();
    }

    /**
     * Stores setting. If key or data is invalid, no action is taken.
     * 
     * @param key  setting key
     * @param data setting option
     */
    public void setData(String key, String data) {
        // validate key
        if (!validateKey(key)) {
            return;
        }

        // validate data
        List<String> settingOptions = Arrays.asList(settingDict.get(key).getOptions());
        if (!settingOptions.contains(data)) {
            return;
        }

        settingDict.get(key).setData(data);
    }
}
