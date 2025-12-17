package model;
import java.io.File;
import java.util.List;

public class EncryptionDecorator extends CompressionDecorator {
    public EncryptionDecorator(CompressionStrategy strategy) {
        super(strategy);
    }

    @Override
    public boolean compress(List<File> sourceFiles, File destinationFile) throws Exception {
        System.out.println(">>> [ENCRYPT] Encrypting data stream...");
        return super.compress(sourceFiles, destinationFile);
    }

    @Override
    public boolean decompress(File sourceArchive, File destinationDir) throws Exception {
        System.out.println(">>> [DECRYPT] Decrypting data stream...");
        return super.decompress(sourceArchive, destinationDir);
    }
}