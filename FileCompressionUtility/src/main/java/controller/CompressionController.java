package controller;

import model.*;
import view.CompressionGUI;
import javax.swing.*;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class CompressionController {
    private static CompressionController instance;
    private CompressionController() {}

    public static synchronized CompressionController getInstance() {
        if (instance == null) instance = new CompressionController();
        return instance;
    }

    // --- Compression Logic ---
    public void handleCompressionRequest(CompressionGUI view, List<File> files, File destDir, CompressionType type, boolean encrypt) {
        if (files == null || files.isEmpty()) { view.setStatus("Error: No files selected."); return; }
        if (destDir == null) { view.setStatus("Error: No destination selected."); return; }

        runTask(view, "Compressing...", () -> {
            CompressionStrategy strategy = CompressionFactory.createStrategy(type);
            if (encrypt) strategy = new EncryptionDecorator(strategy);
            
            // Auto-name archive
            String archiveName = "Archive_" + System.currentTimeMillis(); 
            File outFile = new File(destDir, archiveName);
            
            return strategy.compress(files, outFile);
        });
    }

    // --- Decompression Logic (NEW) ---
    public void handleDecompressionRequest(CompressionGUI view, List<File> files, File destDir, CompressionType type, boolean decrypt) {
        if (files == null || files.size() != 1) { view.setStatus("Error: Select exactly one archive to decompress."); return; }
        if (destDir == null) { view.setStatus("Error: No destination selected."); return; }

        runTask(view, "Decompressing...", () -> {
            CompressionStrategy strategy = CompressionFactory.createStrategy(type);
            if (decrypt) strategy = new EncryptionDecorator(strategy);
            
            return strategy.decompress(files.get(0), destDir);
        });
    }

    // Shared SwingWorker Logic
    private void runTask(CompressionGUI view, String startMsg, TaskAction action) {
        new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                publish(0);
                view.setStatus(startMsg);
                publish(30);
                Thread.sleep(500); // UI visual delay
                boolean result = action.execute();
                publish(100);
                return result;
            }
            @Override
            protected void process(List<Integer> chunks) {
                view.setProgress(chunks.get(chunks.size()-1));
            }
            @Override
            protected void done() {
                try {
                    if (get()) view.setStatus("Operation Successful!");
                    else view.setStatus("Operation Failed.");
                } catch (Exception e) {
                    view.setStatus("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    @FunctionalInterface
    interface TaskAction {
        boolean execute() throws Exception;
    }
}