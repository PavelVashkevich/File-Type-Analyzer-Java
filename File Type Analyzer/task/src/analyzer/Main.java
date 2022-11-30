package analyzer;

public class Main {
    public static void main(String[] args)  {
        FileTypeAnalyzer fta = new FileTypeAnalyzer(args[0], args[1], args[2]);
        fta.checkFileType();
    }
}
