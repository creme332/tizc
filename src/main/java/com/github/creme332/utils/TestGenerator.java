package com.github.creme332.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

/**
 * Generates the text to be typed based on settings set.
 */
public class TestGenerator {
    private SettingsManager gameSettings = new SettingsManager();
    private ArrayList<String> dictionary; // list of all words in dictionary.txt
    private int dictionarySize; // total number of words in dictionary.txt
    private Random rand; // used for generating random words

    public TestGenerator() {
        dictionary = loadDictionary();
        dictionarySize = dictionary.size();
        rand = new Random();
    }

    /**
     * Return a space-separated string containing random words.
     * String is in lowercase and has no numbers or special characters.
     * The number of words is given by `wordCount`.
     * 
     * @param wordCount Number of words in string
     * 
     * @return A sentence containing random words.
     */
    public String getRandomText(int wordCount) throws Exception {
        if (wordCount <= 0) {
            throw new Exception("Word count must be a positive value");
        }

        if (wordCount > 2000) {
            throw new Exception("Word count must be between 1 and 2000 inclusive");
        }

        String result = "";
        for (int i = 0; i < wordCount; i++) {
            result += getRandomWord();
            if (i != wordCount - 1) {
                result += " ";
            }
        }
        return result.toLowerCase();
    }

    /**
     * 
     * @return word count based on difficulty setting
     */
    public int getWordCount() {
        int wordCount;
        String difficulty = gameSettings.getData("Difficulty");
        switch (difficulty) {
            case "easy":
                wordCount = 10;
                break;
            case "medium":
                wordCount = 50;
                break;
            case "hard":
                wordCount = 100;
                break;
            default:
                wordCount = 10;
                break;
        }
        return wordCount;
    }

    /**
     * 
     * @return A space-separated lowercase string with a default number of words.
     *         Default is
     *         10.
     */
    public String getRandomText() {
        // String difficulty = gameSettings.getData("Difficulty");
        // System.out.println(String.format("Current difficulty for generation: %s",
        // difficulty));

        String result = "error";
        try {
            result = getRandomText(getWordCount());
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 
     * @return A single random word from dictionary
     */
    private String getRandomWord() {
        return dictionary.get(rand.nextInt(dictionarySize));
    }

    /**
     * Loads all words from dictionary.txt to array
     * 
     * @return an array containing all words in dictionary
     */
    private ArrayList<String> loadDictionary() {
        Set<String> allWords = new HashSet<String>();
        String dictionaryPath = "/data/dictionary.txt";
        InputStream inputStream = TestGenerator.class.getResourceAsStream(dictionaryPath);

        if (inputStream == null) {
            System.out.println("Unable to load dictionary.");
            return new ArrayList<String>();
        }

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            allWords.add(data);
        }
        scanner.close();

        return new ArrayList<>(allWords);
    }
}
