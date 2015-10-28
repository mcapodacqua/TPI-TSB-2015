package tpi.tsb;

import handlers.StdoutHandler;
import helpers.FileParser;
import java.io.File;

public class TPITSB {

    public static void main(String[] args) {
        File file = new File("libros" + File.separator + "16082-8.txt");
        FileParser parser = new FileParser(file);
        parser.parse(new StdoutHandler());
    }
    
}
