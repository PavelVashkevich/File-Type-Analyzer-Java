package analyzer;

import analyzer.algorithms.KMPSearchAlgorithm;
import analyzer.datatypes.FileDescriptor;
import analyzer.miscellaneous.MessageResourceBundler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class FileTypeAnalyzeWorker implements Callable<String> {
    private final File file;
    private final FileDescriptor[] fileDescriptors;

    public FileTypeAnalyzeWorker(File file, FileDescriptor[] fileDescriptors) {
        this.file = file;
        this.fileDescriptors = fileDescriptors;
    }

    @Override
    public String call() {
        SubstringSearcher substringSearcher = new SubstringSearcher();
        substringSearcher.setSearchAlgorithm(KMPSearchAlgorithm.getInstance());
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = fileDescriptors.length - 1; i >= 0; i--) {
                    if (substringSearcher.textContainsPattern(line, fileDescriptors[i].getPattern())) {
                        return String.format("%s: %s", file.getName(), fileDescriptors[i].getFileType());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return String.format("%s: %s", file.getName(), MessageResourceBundler.UNKNOWN_FILE_TYPE);

    }
}