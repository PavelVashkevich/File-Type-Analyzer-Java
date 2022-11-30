package analyzer.miscellaneous;

public class PrefixFunctionCalculator {

    private PrefixFunctionCalculator() {
        // no instance
    }

    public static int[] getPrefixFunction(char[] pattern) {
        int[] prefixFunction = new int[pattern.length];

        for(int i = 1; i < pattern.length; i++) {
            int j = prefixFunction[i - 1];
            if (pattern[i] == pattern[j]) {
                prefixFunction[i] = prefixFunction[i - 1] + 1;
            } else {
                if (j - 1 < 0) {
                    prefixFunction[i] = 0;
                    continue;
                }
                j = prefixFunction[j - 1];
                if (pattern[i] == pattern[j]) {
                    prefixFunction[i] = prefixFunction[i - 1] + 1;
                }
            }
        }
        return prefixFunction;
    }
}
