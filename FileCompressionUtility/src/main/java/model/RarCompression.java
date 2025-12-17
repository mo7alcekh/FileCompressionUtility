package model;

import java.io.File;
import java.util.List;

public class RarCompression implements CompressionStrategy {

    @Override
    public boolean compress(List<File> sourceFiles, File destinationFile) throws Exception {
        System.out.println("[RAR] Simulating RAR compression...");
        Thread.sleep(1000); 
        return true;
    }

    // YOU MUST ADD THIS METHOD to fix the error
    @Override
    public boolean decompress(File sourceArchive, File destinationDir) throws Exception {
        System.out.println("[RAR] Simulating RAR decompression...");
        Thread.sleep(1000); 
        return true;
    }
}