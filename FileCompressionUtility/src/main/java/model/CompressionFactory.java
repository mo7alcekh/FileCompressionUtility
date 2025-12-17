package model;

public class CompressionFactory {
    
    public static CompressionStrategy createStrategy(CompressionType type) {
        switch (type) {
            case ZIP:
                return new ZipCompression();
            case TAR: // Updated
                return new TarCompression(); // Updated
            case RAR:
                return new RarCompression();
            default:
                throw new IllegalArgumentException("Unknown compression type: " + type);
        }
    }
}