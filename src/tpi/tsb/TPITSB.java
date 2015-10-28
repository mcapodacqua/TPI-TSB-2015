package tpi.tsb;

import database.ConnectionFactory;
import handlers.PersistentHandler;
import handlers.StdoutHandler;
import helpers.FileParser;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TPITSB {

    public static void main(String[] args) {
        File file = new File("libros" + File.separator + "test.txt");
        FileParser parser = new FileParser(file);
        PersistentHandler handler = new PersistentHandler(file);
        parser.parse(handler);
    }
    
}
