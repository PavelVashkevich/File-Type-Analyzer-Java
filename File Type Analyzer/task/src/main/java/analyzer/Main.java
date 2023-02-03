package main.java.analyzer;

import main.java.analyzer.datatypes.UserInput;

import java.io.File;

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