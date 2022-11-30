package analyzer;

public class Main {
    public static void main(String[] args)  {
        String algorithm = args[0];
        String fileName = args[1];
        String pattern = args[2];
        String fileType = args[3];
        FileTypeAnalyzer fta = new FileTypeAnalyzer(algorithm, fileName, pattern, fileType);
        fta.checkFileType();
    }
}
