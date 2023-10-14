import java.util.ArrayList;
import java.util.Random;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class WordGenerator {
    ArrayList<String> allWords;
    int totalWordCount;
    private Random rand;
    int wordCount;

    WordGenerator(int word_count) {
        allWords = getAllWords();
        totalWordCount = allWords.size();
        rand = new Random();
        wordCount = word_count;
    }

    public String getString() {
        String result = "";
        for (int i = 0; i < wordCount; i++) {
            result += getRandomWord();
            if (i != wordCount - 1) {
                result += " ";
            }
        }
        return result;
    }

    private String getRandomWord() {
        return allWords.get(rand.nextInt(totalWordCount));
    }

    private ArrayList<String> getAllWords() {
        ArrayList<String> allWords = new ArrayList<>();
        try {
            File myObj = new File("src/words.txt");
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
