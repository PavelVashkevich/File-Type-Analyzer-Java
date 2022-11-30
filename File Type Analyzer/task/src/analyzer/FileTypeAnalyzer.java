package analyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTypeAnalyzer {
    private final String algorithm;
    private final String fileName;
    private final String filePattern;
    private final String fileType;

    public FileTypeAnalyzer(String algorithm, String fileName, String filePattern, String fileType) {
        this.algorithm = algorithm;
        this.fileName = fileName;
        this.filePattern = filePattern;
        this.fileType = fileType;
    }

    public void checkFileType() {
        boolean patternFound = false;
        SearchAlgorithm searchAlgorithm;
        if (algorithm.equals("--naive")) {
            searchAlgorithm = NaiveSearchAlgorithm.getInstance();
        } else {
            searchAlgorithm = KMPSearchAlgorithm.getInstance();
        }
        SubstringSearcher substringSearcher = new SubstringSearcher();
        substringSearcher.setSearchAlgorithm(searchAlgorithm);
        long start = System.nanoTime();
        try(Scanner sc = new Scanner(new FileInputStream(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (substringSearcher.textContainsPattern(line, filePattern)) {
                    patternFound = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found");
        }
        long end = System.nanoTime();
        System.out.println(patternFound ? fileType : "Unknown file type");
        double elapsedTimeInSeconds = (double) (end - start) / 1_000_000_000;
        System.out.printf("It took %.3f seconds%n", elapsedTimeInSeconds);
    }
}
