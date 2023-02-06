package analyzer.miscellaneous;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class PrefixFunctionCalculatorTest {


    @ParameterizedTest
    @MethodSource("stringToPrefixFunction")
    void prefixFunctionProperlyCalculation(String string, int[] expectedPrefixResult) {
        int[] result = PrefixFunctionCalculator.getPrefixFunction(string.toCharArray());
        Assertions.assertArrayEquals(expectedPrefixResult, result);
    }

    private static List<Arguments> stringToPrefixFunction() {
        return List.of(Arguments.arguments("abab", new int[]{0,0,1,2}),
                Arguments.arguments("baab", new int[]{0,0,0,1}),
                Arguments.arguments("bbba", new int[]{0,1,2,0}),
                Arguments.arguments("aabb", new int[]{0,1,0,0}),
                Arguments.arguments("abbb", new int[]{0,0,0,0}),
                Arguments.arguments("ABRACADABRA", new int[]{0,0,0,1,0,1,0,1,2,3,4}),
                Arguments.arguments("ACCABACCAC", new int[]{0,0,0,1,0,1,2,3,4,2}));
    }


}