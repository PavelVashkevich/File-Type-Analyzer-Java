package main.java.analyzer;

import main.java.analyzer.algorithms.SearchAlgorithm;
import main.java.analyzer.datatypes.FileDescriptor;
import main.java.analyzer.datatypes.UserInput;
import main.java.analyzer.miscellaneous.MergeSort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileTypeAnalyzer {
    private final String directoryPath;
    private final static int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private FileDescriptor[] fileDescriptors;
    private final SearchAlgorithm searchAlgorithm;
    private static final int PRIORITY_INDEX = 0;
    private static final int PATTERN_INDEX = 1;
    private static final int FILETYPE_INDEX = 2;



    public FileTypeAnalyzer(UserInput userInput) {
        this.directoryPath = userInput.getTestFilesDirectoryPath();
        this.fileDescriptors = parseDatabase(userInput.getPatternDb());
        this.searchAlgorithm = userInput.getAlgorithm();
        MergeSort.sort(fileDescriptors);
    }

    private FileDescriptor[] parseDatabase(String databasePath) {
        File databaseFile = new File(databasePath);
        if (!databaseFile.isFile()) {
            throw new RuntimeException("File " + databaseFile + " is not a file type");
        }
        try {
            String fileText = Files.readString(databaseFile.toPath());
            System.out.println("readed line: " + fileText);
            if (fileText.isEmpty() || fileText.isBlank()) {
                throw new RuntimeException("Pattern database is empty");
            }
            String[] dbEntries = fileText.split("\r?\n");
            fileDescriptors = new FileDescriptor[dbEntries.length];
            for (int i = 0; i < fileDescriptors.length; i++) {
                String[] dbEntry = dbEntries[i].split(";");
                fileDescriptors[i] = initFileDescriptor(dbEntry);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + databaseFile + " into memory");
        }
        return fileDescriptors;
    }

    private FileDescriptor initFileDescriptor(String[] dbEntry) {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.setPriority(Integer.parseInt(dbEntry[PRIORITY_INDEX]));
        fileDescriptor.setPattern(dbEntry[PATTERN_INDEX].replace("\"", ""));
        fileDescriptor.setFileType(dbEntry[FILETYPE_INDEX].replace("\"", ""));
        return fileDescriptor;
    }

    public void checkFileType() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<Callable<String>> fileTypeAnalyzeWorkers = new ArrayList<>();
            File directory = new File(directoryPath);
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Error. Directory doest not exist or type is not directory");
                return;
            }
            for (File fileToAnalyze : Objects.requireNonNull(directory.listFiles())) {
                fileTypeAnalyzeWorkers.add(new FileTypeAnalyzeWorker(fileToAnalyze, fileDescriptors, searchAlgorithm));
            }
            List<Future<String>> fileTypeAnalyzeResults = executor.invokeAll(fileTypeAnalyzeWorkers);
            for (Future<String> fileTypeAnalyzeResult : fileTypeAnalyzeResults) {
                System.out.println(fileTypeAnalyzeResult.get());
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
