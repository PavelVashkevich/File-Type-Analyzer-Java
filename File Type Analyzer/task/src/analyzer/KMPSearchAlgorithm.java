package analyzer;

/**
 * Knuth-Morris-Pratt algorithm.
 * @author PavelVashkevich
 * */
public class KMPSearchAlgorithm implements SearchAlgorithm {
    private static KMPSearchAlgorithm instance;

    private KMPSearchAlgorithm() {}

    public static KMPSearchAlgorithm getInstance() {
        if (instance == null) {
            instance = new KMPSearchAlgorithm();
        }
        return instance;
    }

    @Override
    public boolean contains(char[] text, char[] pattern) {
        if (text.length < pattern.length) {
            return false;
        }
        int[] prefixFunction = PrefixFunctionCalculator.getPrefixFunction(pattern);
        int shift = -1;
        int patternCharIndex = 0;
        boolean intermediateSymbolFound = false;
        for (int textCharIndex = 0; textCharIndex < text.length; textCharIndex++) {
            if (text[textCharIndex] == pattern[patternCharIndex]) {
                if (intermediateSymbolFound && patternCharIndex == pattern.length - 1) {
                    System.out.println("Pattern found at index: " + (textCharIndex - patternCharIndex));
                    return true;
                } else {
                    intermediateSymbolFound = true;
                    patternCharIndex++;
                }
            } else {
                if (textCharIndex == text.length - 1) {
                    return false;
                }
                intermediateSymbolFound = false;
                shift += patternCharIndex - prefixFunction[patternCharIndex - 1];
                textCharIndex = shift;
                patternCharIndex = 0;
            }
        }
        return false;
    }
}
