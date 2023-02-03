package main.java.analyzer.algorithms;

import main.java.analyzer.miscellaneous.PrefixFunctionCalculator;

/**
 * Knuth-Morris-Pratt algorithm.
 *
 * @author PavelVashkevich
 */
public class KMPSearchAlgorithm implements SearchAlgorithm {
    private static KMPSearchAlgorithm instance;

    private KMPSearchAlgorithm() {
    }

    public static synchronized KMPSearchAlgorithm getInstance() {
        if (instance == null) {
            instance = new KMPSearchAlgorithm();
        }
        return instance;
    }

    @Override
    public synchronized boolean contains(char[] text, char[] pattern) {
        if (text.length < pattern.length) {
            return false;
        }
        int shift = 0;
        boolean intermediateSymbolFound = false;
        int[] prefixFunction = PrefixFunctionCalculator.getPrefixFunction(pattern);
        char[] intermediateCorrespondingText = new char[pattern.length];
        System.arraycopy(text, 0, intermediateCorrespondingText, 0, pattern.length);
        int indexInIntermediateCorrespondingText = 0;
        int patternIndex = 0;
        int textIndex = 0;
        while (textIndex < text.length) {
            if (intermediateCorrespondingText[indexInIntermediateCorrespondingText] == pattern[patternIndex]) {
                if (intermediateSymbolFound && patternIndex == pattern.length - 1) {
                    return true;
                } else {
                    intermediateSymbolFound = true;
                    patternIndex++;
                    textIndex++;
                    indexInIntermediateCorrespondingText++;
                }
            } else {
                intermediateSymbolFound = false;
                shift += patternIndex == 0 ? 1 : (patternIndex) - prefixFunction[patternIndex - 1];
                if (shift + pattern.length > text.length) {
                    return false;
                }
                System.arraycopy(text, shift, intermediateCorrespondingText, 0, pattern.length);
                indexInIntermediateCorrespondingText = Math.max((textIndex - shift), 0);
                patternIndex = Math.max((textIndex - shift), 0);
                if (patternIndex == 0 && shift == 1) {
                    textIndex++;
                }
            }
        }
        return false;
    }
}
