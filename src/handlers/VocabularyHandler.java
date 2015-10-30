package handlers;

public interface VocabularyHandler {
    /**
     * Executes action for every content parsed.
     * 
     * @param content 
     */
    void handleContent(String content);
    /**
     * Process the data after the parse is done
     */
    void processContent();
}
