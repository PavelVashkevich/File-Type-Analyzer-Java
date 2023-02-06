package analyzer.miscellaneous;

public class MessageResourceBundler {

    public static final String UNKNOWN_FILE_TYPE = "Unknown file type";
    public static final String WRONG_ALGORITHM = "Algorithm to use should be either 'naive', " +
            "'robin-karp' or 'knuth-morris-pratt'";
    public static final String WRONG_PARAMETERS_NUMBER = "Input does not contain required number of parameters. " +
            "Required - 3, found - ";

    private MessageResourceBundler() {
        // NO INSTANCE
    }
}
