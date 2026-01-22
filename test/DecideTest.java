import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DecideTest {

    /**
     * Test for the add method in Decide class.
     */
    @Test
    void addWorks() {
        assertEquals(5, Decide.add(2, 3));
    }
}
