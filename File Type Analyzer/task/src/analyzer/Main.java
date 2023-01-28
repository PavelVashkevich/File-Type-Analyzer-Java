package analyzer;

import java.io.File;

public class Main {
    public static void main(String[] args)  {
        FileTypeAnalyzer fta = new FileTypeAnalyzer(args[0], args[1]);
        fta.checkFileType();
    }
}
