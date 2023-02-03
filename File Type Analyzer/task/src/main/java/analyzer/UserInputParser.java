package analyzer;

import analyzer.datatypes.UserInput;
import analyzer.miscellaneous.MessageResourceBundler;
import analyzer.validators.InputAlgorithm;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UserInputParser {

    @Parameter(names = "--in", description = "Path to directory with files to analyze")
    private String testFilesDirectoryPath;
    @Parameter(names = "--db", description = "Path to the db file with patterns")
    private String patternDBfile;
    @Parameter(names = "--alg", description = "Algorithms to use: naive, robin-karp, knuth-morris-pratt ",
            validateWith = InputAlgorithm.class)
    private String algorithm;
    private static final String PROGRAM_NAME = "File type analyzer";

    public UserInput parseUserCliInput(String[] userCliInput) {
        if (userCliInput.length < 3) {
            throw new RuntimeException(MessageResourceBundler.WRONG_PARAMETERS_NUMBER + userCliInput.length);
        }
        JCommander.newBuilder().addObject(this).build().parse(userCliInput);
        return new UserInput(this.testFilesDirectoryPath, this.patternDBfile, this.algorithm);
    }
}