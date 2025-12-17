package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static FileHandler instance;
    
    private FileHandler() {}
    
    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }
    
    public List<FileData> readFiles(List<File> files) throws IOException {
        List<FileData> fileDataList = new ArrayList<>();
        
        for (File file : files) {
            if (file.isDirectory()) {
                fileDataList.addAll(readDirectory(file));
            } else {
                fileDataList.add(readFile(file));
            }
        }
        
        return fileDataList;
    }
    
    private FileData readFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] content = new byte[(int) file.length()];
            fis.read(content);
            return new FileData(file, content);
        }
    }
    
    private List<FileData> readDirectory(File directory) throws IOException {
        List<FileData> fileDataList = new ArrayList<>();
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileDataList.addAll(readDirectory(file));
                } else {
                    fileDataList.add(readFile(file));
                }
            }
        }
        
        return fileDataList;
    }
    
    public String detectFileType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".zip")) return "ZIP";
        if (name.endsWith(".gz") || name.endsWith(".gzip")) return "GZIP";
        if (name.endsWith(".tar")) return "TAR";
        if (name.endsWith(".rar")) return "RAR";
        if (name.endsWith(".enc")) return "ENCRYPTED";
        throw new IllegalArgumentException("Unsupported file type: " + name);
    }
}