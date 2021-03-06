package ui;

import handlers.PersistentHandler;
import helpers.FileParser;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class LoadFileWorker extends SwingWorker<Void, Integer>{

    private final File[] files;
    private JProgressBar progressBar;
    private final JButton button;
    private final VocabularyApp app;

    public LoadFileWorker(VocabularyApp app, File[] files, JProgressBar progressBar, JButton button) {
        this.app = app;
        this.files = files;
        this.progressBar = progressBar;
        this.button = button;
        this.progressBar = progressBar;
        this.progressBar.setValue(0);
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        int i = 0;
        this.button.setText("Cargando...");
        this.button.setEnabled(false);
        this.button.setOpaque(true);
        this.progressBar.setVisible(true);
        for (File file : files) {
            i++;
            FileParser parser = new FileParser(file);
            parser.parse(new PersistentHandler(file));
            publish(i);
        }
        Thread.sleep(100);
        this.progressBar.setVisible(false);
        this.button.setEnabled(true);
        this.button.setOpaque(false);
        this.button.setText("Agregar Archivo/s");
        this.app.loadTable();
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        this.progressBar.setValue(chunks.get(0) * 100 / files.length);
    }
    
    
}
