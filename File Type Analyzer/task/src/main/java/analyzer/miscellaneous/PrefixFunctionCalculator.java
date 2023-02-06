package analyzer.miscellaneous;

public class PrefixFunctionCalculator {

    private PrefixFunctionCalculator() {
        // no instance
    }

    /**
     Algorithm steps:
     1.Set p[0]=0.
     2.For every prefix s[0,i] of the string 's' calculate the value of p[i] as follows: define j=p[i−1].
     3.Compare the symbols s[j] and s[i]. If they match, set p[i]=j+1.
     4.Otherwise, take j=p[j−1] and repeat step 3.If we reach j=0 and there is no match, we conclude that p[i]=0.
     */
    public static synchronized int[] getPrefixFunction(char[] pattern) {
        int[] prefixFunction = new int[pattern.length];

        for(int i = 1; i < pattern.length; i++) {
            int j = prefixFunction[i - 1];
            if (pattern[i] == pattern[j]) {
                prefixFunction[i] = j + 1;
            } else {
                if (j - 1 < 0) {
                    prefixFunction[i] = 0;
                    continue;
                }
                j = prefixFunction[j - 1];
                if (pattern[i] == pattern[j]) {
                    prefixFunction[i] = j + 1;
                }
            }
        }
        return prefixFunction;
    }
}
