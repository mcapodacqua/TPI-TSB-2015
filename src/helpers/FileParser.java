package helpers;

import handlers.VocabularyHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpi.tsb.TPITSB;

public class FileParser {
    private File file;

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
            Logger.getLogger(TPITSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String normalize(String content) {
        String withoutAccents = Normalizer.normalize(content, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        String onlyLetters = withoutAccents.replaceAll("[^A-Za-z]", " ");
        return onlyLetters;
    }
            
}
