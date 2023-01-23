package analyzer;

import java.io.File;
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
                System.out.println("Error. Directory doest not exist or type is not directory");
                return;
            }
            for (File fileToAnalyze : Objects.requireNonNull(directory.listFiles())) {
                fileTypeAnalyzeWorkers.add(new FileTypeAnalyzeWorker(fileToAnalyze, fileTypePattern, fileType));
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
