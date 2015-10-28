package handlers;

public class StdoutHandler implements VocabularyHandler{
    @Override
    public void handleContent(String content) {
        System.out.println(content);
    }

    @Override
    public void processContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
