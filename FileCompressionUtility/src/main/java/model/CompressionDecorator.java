package model;
import java.io.File;
import java.util.List;

public abstract class CompressionDecorator implements CompressionStrategy {
    protected CompressionStrategy wrappedStrategy;

    public CompressionDecorator(CompressionStrategy strategy) {
        this.wrappedStrategy = strategy;
    }

    @Override
    public boolean compress(List<File> sourceFiles, File destinationFile) throws Exception {
        return wrappedStrategy.compress(sourceFiles, destinationFile);
    }

    @Override
    public boolean decompress(File sourceArchive, File destinationDir) throws Exception {
        return wrappedStrategy.decompress(sourceArchive, destinationDir);
    }
}