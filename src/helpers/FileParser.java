package helpers;

import handlers.VocabularyHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Separates a file into words.
 * Basically by removing any non alphabetical content 
 * of the word (like numbers, accents, symbos, etc)
 * and delegates the control to a VocabularyHandler instance
 * @see VocabularyHandler
 */
public class FileParser {
    private final File file;

    public FileParser(File file) {
        this.file = file;
    }
    
    public void parse(VocabularyHandler handler) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));){
            String line;
            while ( (line = bReader.readLine()) != null) {
                Scanner sc2 = new Scanner(this.normalize(line));
                //Split the line into words (uses space as separator)
                while (sc2.hasNext()) {
                    handler.handleContent(sc2.next());
                }
            }
            handler.processContent();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    private String normalize(String content) {
        String withoutAccents = Normalizer.normalize(content, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        String onlyLetters = withoutAccents.replaceAll("[^A-Za-z]", " ");
        return onlyLetters;
    }
            
}
