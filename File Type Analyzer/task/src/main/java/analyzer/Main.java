package main.java.analyzer;

import main.java.analyzer.datatypes.UserInput;

public class Main {
    public static void main(String[] args)  {
        UserInput userInput = new UserInputParser().parseUserCliInput(args);
        new FileTypeAnalyzer(userInput).checkFileType();
    }
}
