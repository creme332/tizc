package utils;

import java.util.ArrayList;
import java.util.Random;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class WordGenerator {
    private ArrayList<String> allWords;
    private int totalWordCount;
    private Random rand;
    private int wordCount;

    public WordGenerator(int word_count) {
        allWords = getAllWords();
        totalWordCount = allWords.size();
        rand = new Random();
        wordCount = word_count > 0 ? word_count : 0;
    }

    /**
     * Return a space-separated string containing random words.
     * String is in lowercase.
     * The number of words is given by `wordCount`.
     * 
     * @return String
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

    private String getRandomWord() {
        return allWords.get(rand.nextInt(totalWordCount));
    }

    private ArrayList<String> getAllWords() {
        // Reference: https://www.w3schools.com/java/java_files_read.asp
        ArrayList<String> allWords = new ArrayList<String>();
        try {
            // System.out.println(new File(".").getAbsolutePath());
            File myObj = new File("resources/data/words.txt");
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
