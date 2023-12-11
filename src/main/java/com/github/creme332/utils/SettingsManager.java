package com.github.creme332.utils;

import java.util.prefs.Preferences;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.creme332.model.Setting;

public class SettingsManager {
    private Map<String, Setting> settingDict = new HashMap<String, Setting>();

    private Preferences preferences;
    private String nodeName = "com.github.creme332.view.Settings.Section"; // TODO: REMOVE
    public static String invalidOption = "Unknown"; // TODO: REMOVE

    public SettingsManager() {
        // initialize all settings and their possible options
        Setting s = new Setting("Mode", new String[] { "word", "quote" },
                "In word mode you must type a list random words. In quote mode, you must type a random quote. The number of words is determined by the difficulty setting.");
        settingDict.put(
                "Mode",
                s);

        s = new Setting("Difficulty", new String[] { "easy", "medium", "hard" },
                "Normal is the classic type test experience. Expert fails the test if you submit (press space) an incorrect word. Master fails if you press a single incorrect key (meaning you have to achieve 100% accuracy).");
        settingDict.put("Difficulty", s);

        s = new Setting("Live speed", new String[] { "hide", "show" },
                "Displays a live speed during the test. Updates once every second.");
        settingDict.put("Live speed", s);

        s = new Setting("Live accuracy", new String[] { "hide", "show" }, "Displays live accuracy during the test.");
        settingDict.put("Live accuracy", s);

        s = new Setting("Live timer", new String[] { "hide", "show" },
                "Displays a live timer for timed tests and word count for word based tests (word, quote or custom mode).");
        settingDict.put("Live timer", s);

        s = new Setting("Lazy mode", new String[] { "off", "on" },
                "Replaces accents / diacritics / special characters with their normal letter equivalents.");
        settingDict.put("Lazy mode", s);

        // define a node where settings will be stored
        preferences = Preferences.userRoot().node(nodeName);
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
        return preferences.get(key, invalidOption);
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
        preferences.put(key, data);
    }
}
