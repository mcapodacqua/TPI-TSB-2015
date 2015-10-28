package handlers;

import database.ConnectionFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PersistentHandler implements VocabularyHandler{
    private String filename;
    private Map<String,Long> map = new HashMap<>();

    public PersistentHandler(String filename) {
        this.filename = filename;
    }
    public PersistentHandler(File file) {
        this.filename = file.getName();
    }
    @Override
    public void handleContent(String content) {
        Long countForContent = map.getOrDefault(content, 0L);
        if (countForContent == 0L) {
            map.put(content, 1L);
        } else {
            map.replace(content, countForContent+1);
        }

    }

    @Override
    public void processContent() {
        try (Connection conn = ConnectionFactory.getConntection(); Statement stat = conn.createStatement();){
            for (Entry<String,Long> item : map.entrySet()) {
                System.out.println(item.getKey() + item.getValue());
            }
            stat.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
}
