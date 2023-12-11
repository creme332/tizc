package com.github.creme332.model;

import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

public class Setting {
    private String key;
    private String options[];
    private String description;

    private Preferences preferences;
    static String nodeName = "com.github.creme332.view.Settings.Section";
    public static String invalidOption = "Unknown";

    public Setting(String name, String[] options, String description) {
        this.key = name;
        this.options = options;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public String[] getOptions() {
        return options;
    }

    /**
     * Get data stored for a setting.
     * 
     * @return Data stored at particular key. If invalid key, returns invalidOption.
     */
    public String getData() {
        return preferences.get(key, invalidOption);
    }

    /**
     * Stores setting. If key or data is invalid, no action is taken.
     * 
     * @param data setting option
     */
    public void setData(String data) {
        // validate data
        List<String> settingOptions = Arrays.asList(options);
        if (!settingOptions.contains(data)) {
            return;
        }
        preferences.put(key, data);
    }
}
