package handlers;

public class PersistentHandler implements VocabularyHandler{
    @Override
    public void doAction(String content) {
        System.out.println(content);
    }
    
}
