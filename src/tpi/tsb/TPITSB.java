package tpi.tsb;

import database.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.VocabularyApp;

public class TPITSB {

    public static void main(String[] args) {
        /* Code used to refresh database */
//        Connection conn = ConnectionFactory.getConntection();
//        try {
//            conn.createStatement().execute("delete from vocabulary");
//        } catch (SQLException ex) {
//            Logger.getLogger(TPITSB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.exit(1);
        VocabularyApp app = new VocabularyApp();
        app.setVisible(true);
    }
    
}
