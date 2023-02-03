package main.java.analyzer.datatypes;

import main.java.analyzer.algorithms.KMPSearchAlgorithm;
import main.java.analyzer.algorithms.NaiveSearchAlgorithm;
import main.java.analyzer.algorithms.RobinKarpSearchAlgorithm;
import main.java.analyzer.algorithms.SearchAlgorithm;

public class UserInput {
    private String testFilesDirectoryPath;
    private String patternDb;
    private SearchAlgorithm algorithm;

    public UserInput(String testFilesDirectoryPath, String patternDb, String algorithm) {
        this.testFilesDirectoryPath = testFilesDirectoryPath;
        this.patternDb = patternDb;
        setAlgorithm(algorithm);
    }

    public String getTestFilesDirectoryPath() {
        return testFilesDirectoryPath;
    }

    public void setTestFilesDirectoryPath(String testFilesDirectoryPath) {
        this.testFilesDirectoryPath = testFilesDirectoryPath;
    }

    public String getPatternDb() {
        return patternDb;
    }

    public void setPatternDb(String patternDb) {
        this.patternDb = patternDb;
    }

    public SearchAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        switch (algorithm) {
            case "naive":
                this.algorithm = NaiveSearchAlgorithm.getInstance();
                break;
            case "robin-karp":
                this.algorithm = RobinKarpSearchAlgorithm.getInstance();
                break;
            case "knuth-morris-pratt":
                this.algorithm = KMPSearchAlgorithm.getInstance();
                break;
        }
    }
}
