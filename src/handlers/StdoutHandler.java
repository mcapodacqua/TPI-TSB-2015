package handlers;

public class StdoutHandler implements VocabularyHandler{
    @Override
    public void doAction(String content) {
        System.out.println(content);
    }
}
