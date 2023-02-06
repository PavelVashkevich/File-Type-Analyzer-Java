package analyzer.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class NaiveSearchAlgorithmTest {

    private static NaiveSearchAlgorithm naiveSearchAlgorithm;

    @BeforeAll
    static void newKMPSearchAlgorithm() {
        naiveSearchAlgorithm = NaiveSearchAlgorithm.getInstance();
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(resources = {"/string-pattern-pos.csv"}, numLinesToSkip = 1)
    void stringShouldContainsPattern(String string, String pattern) {
        Assertions.assertTrue(naiveSearchAlgorithm.contains(string.toCharArray(), pattern.toCharArray()));
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(resources = {"/string-pattern-neg.csv"}, numLinesToSkip = 1)
    void stringShouldNotContainsPattern(String string, String pattern) {
        Assertions.assertFalse(naiveSearchAlgorithm.contains(string.toCharArray(), pattern.toCharArray()));
    }


}