import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecideTest {

    /**
     * Test for the add method in Decide class.
     */
    @Test
    void addWorks() {
        assertEquals(5, Decide.add(2, 3));
    }

    /**
     * 
     * Parameterized test for the Decide add function
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(textBlock = "0, 1, 1\n1, 2, 3\n49, 51, 100\n1, 100, 101")
    void testDecideAddFunction(int first, int second, int expectedResult) {
        assertEquals(expectedResult, Decide.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }
}
