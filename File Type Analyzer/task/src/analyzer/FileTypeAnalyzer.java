package analyzer;

import analyzer.algorithms.KMPSearchAlgorithm;
import analyzer.algorithms.NaiveSearchAlgorithm;
import analyzer.algorithms.SearchAlgorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FileTypeAnalyzer {
    private final String directoryPath;
    private final String fileTypePattern;
    private final String fileType;
    private final static int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public FileTypeAnalyzer(String directoryPath, String fileTypePattern, String fileType) {
        this.directoryPath = directoryPath;
        this.fileTypePattern = fileTypePattern;
        this.fileType = fileType;
    }

    public void checkFileType() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<Callable<String>> fileTypeAnalyzeWorkers = new ArrayList<>();
            File directory = new File(directoryPath);
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Error");
                return;
            }
            for (File fileToAnalyze : directory.listFiles()) {
                fileTypeAnalyzeWorkers.add(new FileTypeAnalyzeWorker(fileToAnalyze, fileTypePattern, fileType));
            }
            List<Future<String>> fileTypeAnalyzeResults = executor.invokeAll(fileTypeAnalyzeWorkers);
            for (Future<String> fileTypeAnalyzeResult : fileTypeAnalyzeResults) {
                System.out.println(fileTypeAnalyzeResult.get());
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
