package analyzer;

import analyzer.datatypes.UserInput;

public class Main {
    public static void main(String[] args) {
        try {
            UserInput userInput = new UserInputParser().parseUserCliInput(args);
            new FileTypeAnalyzer(userInput).checkFileType();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}