package analyzer.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class KMPSearchAlgorithmTest {

    private static KMPSearchAlgorithm kmpSearchAlgorithm;

    @BeforeAll
    static void newKMPSearchAlgorithm() {
        kmpSearchAlgorithm = KMPSearchAlgorithm.getInstance();
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(resources = {"/string-pattern-pos.csv"}, numLinesToSkip = 1)
    void stringShouldContainsPattern(String string, String pattern) {
        Assertions.assertTrue(kmpSearchAlgorithm.contains(string.toCharArray(), pattern.toCharArray()));
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(resources = {"/string-pattern-neg.csv"}, numLinesToSkip = 1)
    void stringShouldNotContainsPattern(String string, String pattern) {
        Assertions.assertFalse(kmpSearchAlgorithm.contains(string.toCharArray(), pattern.toCharArray()));
    }


}