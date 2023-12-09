package com.github.creme332.utils;

import java.util.prefs.Preferences;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsManager {
    private Map<String, String[]> settingDict = new HashMap<String, String[]>();

    private Preferences preferences;
    private String nodeName = "com.github.creme332.view.Settings.Section";
    public static String invalidOption = "Unknown";

    public SettingsManager() {
        // initialize all settings and their possible options
        settingDict.put("Mode", new String[] { "word", "quote" });
        settingDict.put("Difficulty",
                new String[] { "easy", "medium", "hard" });
        settingDict.put("Live speed", new String[] { "hide", "show" });
        settingDict.put("Live accuracy", new String[] { "hide", "show" });
        settingDict.put("Live timer", new String[] { "hide", "show" });

        // define a node where settings will be stored
        preferences = Preferences.userRoot().node(nodeName);
    }

    /**
     * 
     * @return A dictionary containing all settings and their valid options
     */
    public Map<String, String[]> getSettings() {
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
        List<String> settingOptions = Arrays.asList(settingDict.get(key));
        if (!settingOptions.contains(data)) {
            return;
        }
        preferences.put(key, data);
    }
}
