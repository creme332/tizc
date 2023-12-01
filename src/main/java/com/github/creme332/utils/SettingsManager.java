package com.github.creme332.utils;

import java.util.prefs.Preferences;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsManager {
    // available settings
    private Map<String, String[]> dict = new HashMap<String, String[]>();

    private Preferences preferences;
    private String nodeName = "com.github.creme332.view.Settings.Section";
    private String unknownPreference = "Unknown";

    public SettingsManager() {
        // initialize all settings and their possible options
        dict.put("Mode", new String[] { "word", "quote" });
        dict.put("Difficulty",
                new String[] { "easy", "medium", "hard" });
        dict.put("Live speed", new String[] { "hide", "show" });
        dict.put("Live accuracy", new String[] { "hide", "show" });
        dict.put("Live timer", new String[] { "hide", "show" });

        // define a node where settings will be stored
        preferences = Preferences.userRoot().node(nodeName);
    }

    public  Map<String, String[]> getSettings() {
        return dict;
    }

    private boolean validateKey(String key) {
        for (String c : dict.keySet()) {
            if (c.equals(key))
                return true;
        }

        return false;
    }

    public String getData(String key) {
        if (!validateKey(key)) {
            return "Invalid key";
        }
        return preferences.get(key, unknownPreference);
    }

    public void setData(String key, String data) {
        // validate key
        if (!validateKey(key)) {
            return;
        }

        // validate data
        List<String> settingOptions = Arrays.asList(dict.get(key));
        if (!settingOptions.contains(data)) {
            return;
        }
        preferences.put(key, data);
    }
}
