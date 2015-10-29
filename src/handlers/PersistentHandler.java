package handlers;

import database.ConnectionFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private void checkKnownFile(Connection conn)
    {
        try (
                PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM vocabulary WHERE filename LIKE ?");
                PreparedStatement stDelete = conn.prepareStatement("DELETE FROM vocabulary WHERE filename LIKE ?");
                ){
            st.setString(1, filename);
            Long countElementsSameFilename = st.executeQuery().getLong(1);
            if (countElementsSameFilename > 0) {
                stDelete.setString(1, filename);
                stDelete.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    @Override
    public void processContent() {
        try (
                Connection conn = ConnectionFactory.getConntection();
                PreparedStatement stat = conn.prepareStatement("INSERT INTO vocabulary (word,count,filename) VALUES (?,?,?)");
                ){
            this.checkKnownFile(conn);
            for (Entry<String,Long> item : map.entrySet()) {
                String word = item.getKey();
                Long count = item.getValue();
                stat.setString(1, word);
                stat.setLong(2, count);
                stat.setString(3, filename);
                stat.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
}
