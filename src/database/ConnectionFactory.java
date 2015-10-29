package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JDBC_NAME = "org.sqlite.JDBC";
    private static final String CONNECTION_PATH = "jdbc:sqlite:vocabulary.sqlite";
    
    public static Connection getConntection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_NAME);
            conn = DriverManager.getConnection(CONNECTION_PATH);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return conn;
    }
}
