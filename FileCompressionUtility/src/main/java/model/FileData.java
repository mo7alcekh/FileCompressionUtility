package model;

import java.io.File;
import java.io.Serializable;

public class FileData implements Serializable {
    private String fileName;
    private String fileType; // TEXT, IMAGE, VIDEO, etc.
    private byte[] content;
    private long size;
    
    public FileData(File file, byte[] content) {
        this.fileName = file.getName();
        this.content = content;
        this.size = content.length;
        this.fileType = detectFileType(file);
    }
    
    private String detectFileType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".txt") || name.endsWith(".java") || name.endsWith(".xml")) {
            return "TEXT";
        } else if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif")) {
            return "IMAGE";
        } else if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".mkv")) {
            return "VIDEO";
        } else if (name.endsWith(".mp3") || name.endsWith(".wav")) {
            return "AUDIO";
        } else {
            return "BINARY";
        }
    }
    
    // Getters and setters
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public byte[] getContent() { return content; }
    public long getSize() { return size; }
}