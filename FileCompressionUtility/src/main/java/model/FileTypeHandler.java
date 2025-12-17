package model;

public interface FileTypeHandler {
    void process(FileData fileData);
    String getType();
}

class TextFileHandler implements FileTypeHandler {
    @Override public void process(FileData fileData) {
        // Special processing for text files (e.g., encoding detection)
    }
    @Override public String getType() { return "TEXT"; }
}

class ImageFileHandler implements FileTypeHandler {
    @Override public void process(FileData fileData) {
        // Special processing for images (e.g., compression optimization)
    }
    @Override public String getType() { return "IMAGE"; }
}

class VideoFileHandler implements FileTypeHandler {
    @Override public void process(FileData fileData) {
        // Special processing for videos
    }
    @Override public String getType() { return "VIDEO"; }
}

class AudioFileHandler implements FileTypeHandler {
    @Override public void process(FileData fileData) {
        // Special processing for audio
    }
    @Override public String getType() { return "AUDIO"; }
}

class BinaryFileHandler implements FileTypeHandler {
    @Override public void process(FileData fileData) {
        // Default processing for binary files
    }
    @Override public String getType() { return "BINARY"; }
}