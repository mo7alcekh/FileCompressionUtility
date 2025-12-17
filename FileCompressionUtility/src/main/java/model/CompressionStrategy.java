package model;

import java.io.File;
import java.util.List;

public interface CompressionStrategy {
    // Compress a list of files/folders into one archive
    boolean compress(List<File> sourceFiles, File destinationFile) throws Exception;
    
    // Decompress a source archive into a destination folder
    boolean decompress(File sourceArchive, File destinationDir) throws Exception;
}