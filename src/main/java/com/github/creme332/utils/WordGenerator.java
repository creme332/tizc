package com.github.creme332.utils;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Generates the text to be typed.
 */
public class WordGenerator {
    private ArrayList<String> dictionary; // list of all words in dictionary.txt
    private int totalWordCount; // total number of words in dictionary.txt
    private Random rand; // used for generating random words

    public WordGenerator() {
        dictionary = loadDictionary();
        totalWordCount = dictionary.size();
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
     * @return A space-separated lowercase string with a default number of words.
     *         Default is
     *         10.
     */
    public String getRandomText() {
        String result = "error";
        try {
            result = getRandomText(10);

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
        return dictionary.get(rand.nextInt(totalWordCount));
    }

    /**
     * Loads all words from dictionary.txt to array
     * 
     * @return an array containing all words in dictionary
     */
    private ArrayList<String> loadDictionary() {
        // Reference: https://www.w3schools.com/java/java_files_read.asp
        ArrayList<String> allWords = new ArrayList<String>();
        String dictionaryPath = "/data/dictionary.txt";

        try {
            File myObj = new File(this.getClass().getResource(dictionaryPath).getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allWords.add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return allWords;
    }
}
