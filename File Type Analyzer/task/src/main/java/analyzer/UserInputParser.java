package main.java.analyzer;

import main.java.analyzer.datatypes.UserInput;
import main.java.analyzer.miscellaneous.MessageResourceBundler;
import main.java.analyzer.validators.InputAlgorithm;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UserInputParser {

    @Parameter(names = "--in", description = "Path to directory with files to analyze")
    private String testFilesDirectoryPath;
    @Parameter(names = "--db", description = "Path to the db file with patterns")
    private String patternDBfile;
    @Parameter(names = "--alg", description = "Algorithms to use: naive, robin-karp, knuth-morris-pratt",
            validateWith = InputAlgorithm.class)
    private String algorithm;


    public UserInput parseUserCliInput(String[] userCliInput) {
        if (userCliInput.length < 3) {
            throw new RuntimeException(MessageResourceBundler.WRONG_PARAMETERS_NUMBER + userCliInput.length);
        }
        JCommander.newBuilder().addObject(this).build().parse(userCliInput);
        return new UserInput(this.testFilesDirectoryPath, this.patternDBfile, this.algorithm);
    }
}