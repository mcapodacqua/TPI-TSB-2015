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
import java.util.logging.Level;
import java.util.logging.Logger;

public class TPITSB {

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getConntection();
//        try {
//            conn.createStatement().execute("delete from vocabulary");
//        } catch (SQLException ex) {
//            Logger.getLogger(TPITSB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        long init = System.currentTimeMillis();
        File file = new File("libros" + File.separator + "16082-8.txt");
        FileParser parser = new FileParser(file);
        PersistentHandler handler = new PersistentHandler(file);
        parser.parse(handler);
        long end = System.currentTimeMillis();
        
        System.out.println("Duration: " + (end-init));
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from vocabulary");
            while(rs.next()) {
                System.out.println( rs.getString(2) + "-" + rs.getLong(3) + "-" + rs.getString(4));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TPITSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
