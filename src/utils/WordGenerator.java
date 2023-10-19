package utils;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Generates the text to be typed.
 */
public class WordGenerator {
    private ArrayList<String> allWords; // list of all words in dictionary.txt
    private int totalWordCount; // total number of words in dictionary.txt
    private Random rand; // used for generating random words
    private int wordCount; // required number of words in text to be generated

    public WordGenerator(int word_count) {
        allWords = getAllWords();
        totalWordCount = allWords.size();
        rand = new Random();
        wordCount = word_count > 0 ? word_count : 0;
    }

    /**
     * Return a space-separated string containing random words.
     * String is in lowercase and has no numbers or special characters.
     * The number of words is given by `wordCount`.
     * 
     * @return A sentence containing random words.
     */
    public String getString() {
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
     * @return A single random word from dictionary
     */
    private String getRandomWord() {
        return allWords.get(rand.nextInt(totalWordCount));
    }

    /**
     * Loads all words from dictionary.txt to array
     * 
     * @return an array containing all words in dictionary
     */
    private ArrayList<String> getAllWords() {
        // Reference: https://www.w3schools.com/java/java_files_read.asp
        ArrayList<String> allWords = new ArrayList<String>();
        try {
            // System.out.println(new File(".").getAbsolutePath());
            File myObj = new File("resources/data/dictionary.txt");
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
