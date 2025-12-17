package utils;

import java.io.File;

public class FileUtils {
    
    // Helper method to ensure file extensions are correct
    public static String ensureExtension(String fileName, String ext) {
        if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
            return fileName;
        }
        return fileName + "." + ext;
    }
    
    // Validate write permissions
    public static boolean canWrite(File directory) {
        return directory != null && directory.exists() && directory.isDirectory() && directory.canWrite();
    }
}