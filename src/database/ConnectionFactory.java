package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final String JDBC_NAME = "org.sqlite.JDBC";
    private static final String CONNECTION_PATH = "jdbc:sqlite:vocabulary.sqlite";
    
    public static Connection getConntection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_NAME);
            conn = DriverManager.getConnection(CONNECTION_PATH);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
