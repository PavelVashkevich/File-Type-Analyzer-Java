package analyzer;

import analyzer.algorithms.KMPSearchAlgorithm;
import analyzer.miscellaneous.MessageResourceBundler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileTypeAnalyzeWorker implements Callable<String> {
    private final File file;
    private final String fileTypePattern;
    private final String fileType;
    private final Lock readLock = new ReentrantReadWriteLock().readLock();

    public FileTypeAnalyzeWorker(File file, String fileTypePattern, String fileType) {
        this.file = file;
        this.fileTypePattern = fileTypePattern;
        this.fileType = fileType;
    }

    @Override
    public String call()  {
        try {
            readLock.lock();
            SubstringSearcher substringSearcher = new SubstringSearcher();
            substringSearcher.setSearchAlgorithm(KMPSearchAlgorithm.getInstance());
            boolean isFileTypePatternFound = false;
            try(Scanner scanner = new Scanner(new FileInputStream(file))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(substringSearcher.textContainsPattern(line, fileTypePattern)) {
                        isFileTypePatternFound = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return String.format("%s: %s", file.getName(), isFileTypePatternFound ? fileType : MessageResourceBundler.UNKNOWN_FILE_TYPE);
        }finally {
            readLock.unlock();
        }
    }
}
