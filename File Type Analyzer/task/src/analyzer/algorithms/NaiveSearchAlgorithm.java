package analyzer.algorithms;

/**
 * Naive substring search algorithm.
 * @author PavelVashkevich
 * */
public class NaiveSearchAlgorithm implements SearchAlgorithm {
    private static NaiveSearchAlgorithm instance;

    private NaiveSearchAlgorithm() {}

    public static NaiveSearchAlgorithm getInstance() {
        if (instance == null) {
            instance = new NaiveSearchAlgorithm();
        }
        return instance;
    }

    /**
     * Compare every text character with a pattern character with a one symbol shift
     * List index position where pattern match with a text substring
     * */
    // izmenit na sravnenie cherez substringi
    @Override
    public boolean contains(char[] text, char[] pattern) {
        if (text.length < pattern.length) {
            return false;
        }

        int patternCharIndex = 0;
        boolean intermediateSymbolFound = false;
        boolean result = false;
        for (int textCharIndex = 0; textCharIndex < text.length; textCharIndex++) {
            if (text[textCharIndex] == pattern[patternCharIndex]) {
                if (intermediateSymbolFound && patternCharIndex == pattern.length - 1) {
                    result = true;
                    System.out.println("Pattern found at index: " + (textCharIndex - patternCharIndex));
                    textCharIndex = textCharIndex  - patternCharIndex;
                    patternCharIndex = 0;
                    intermediateSymbolFound = false;
                } else {
                    intermediateSymbolFound = true;
                    patternCharIndex++;
                }
            } else {
                intermediateSymbolFound = false;
                textCharIndex = textCharIndex - patternCharIndex;
                patternCharIndex = 0;
            }
        }
        return result;
    }
}
