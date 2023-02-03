package main.java.analyzer;

import main.java.analyzer.algorithms.SearchAlgorithm;
import main.java.analyzer.datatypes.FileDescriptor;
import main.java.analyzer.miscellaneous.MessageResourceBundler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class FileTypeAnalyzeWorker implements Callable<String> {
    private final File file;
    private final FileDescriptor[] fileDescriptors;
    private final SearchAlgorithm searchAlgorithm;

    public FileTypeAnalyzeWorker(File file, FileDescriptor[] fileDescriptors, SearchAlgorithm searchAlgorithm) {
        this.file = file;
        this.fileDescriptors = fileDescriptors;
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public String call() {
        SubstringSearcher substringSearcher = new SubstringSearcher();
        substringSearcher.setSearchAlgorithm(this.searchAlgorithm);
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
            throw new RuntimeException("File " + file + " was not found");
        }
        return String.format("%s: %s", file.getName(), MessageResourceBundler.UNKNOWN_FILE_TYPE);
    }
}