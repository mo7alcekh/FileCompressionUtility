package model;

import java.io.File;
import java.util.List;

public class TarCompression implements CompressionStrategy {

    @Override
    public boolean compress(List<File> sourceFiles, File destinationFile) throws Exception {
        System.out.println("[TAR] Simulating TAR compression...");
        Thread.sleep(1000); // Simulate work
        return true;
    }

    @Override
    public boolean decompress(File sourceArchive, File destinationDir) throws Exception {
        System.out.println("[TAR] Simulating TAR decompression...");
        Thread.sleep(1000); // Simulate work
        return true;
    }
}