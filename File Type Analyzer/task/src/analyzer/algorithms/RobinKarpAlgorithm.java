package analyzer.algorithms;

public class RobinKarpAlgorithm implements SearchAlgorithm {

    private static RobinKarpAlgorithm instance;
    private static final int a = 3;
    private static final int q = 13;


    public static synchronized RobinKarpAlgorithm getInstance() {
        if (instance == null) {
            instance = new RobinKarpAlgorithm();
        }
        return instance;
    }

    @Override
    public synchronized boolean contains(char[] text, char[] pattern) {
        if (text.length < pattern.length) {
            return false;
        }
        long patternHash = calculatePolynomialHash(pattern);
        char[] substring = new char[pattern.length];
        System.arraycopy(text, text.length - pattern.length, substring, 0, substring.length);
        // algorithm starts compare suffix with pattern in the first step
        long suffixHash = calculatePolynomialHash(substring);
        // algorithm starts compare suffix with pattern in the first step
        if (suffixHash == patternHash) {
            if (substringsMatch(substring, pattern)) {
                return true;
            }
        }
        long intermediateHash = suffixHash;
        int nextIndex = text.length - substring.length - 1;
        int previousIndex = text.length - 1;
        int subtractionIndex = 1;
        // testy jeste raz bez -1
        while (nextIndex >= 0) {
            System.arraycopy(text, text.length - pattern.length - subtractionIndex, substring, 0, substring.length);
            intermediateHash = calculateRollingHash(text, nextIndex, previousIndex, intermediateHash);
            if (intermediateHash == patternHash)
                if (substringsMatch(substring, pattern))
                    return true;
            nextIndex--;
            previousIndex--;
            subtractionIndex++;
        }
        return false;
    }

    private boolean substringsMatch(char[] substring, char[] pattern) {
        for (int i = 0; i < substring.length; i++) {
            if (substring[i] != pattern[i])
                return false;

        }
        return true;
    }

    private long calculatePolynomialHash(char[] substring) {
        long row = 0;
        long dividend = 0;
        for (char c : substring) {
            dividend += (long) c * Math.pow(a, row);
            row += 1;
        }
        return Math.floorMod(dividend, q);
    }

    private long calculateRollingHash(char[] text, int nextIndex,
                                      int previousIndex, long lastSubStringHash) {
        long dividend = (lastSubStringHash - (int) text[previousIndex] * (long) Math.pow(a, (previousIndex - nextIndex - 1))) * a + (int) text[nextIndex];
        return Math.floorMod(dividend, q);
    }
}
