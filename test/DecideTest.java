import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

public class DecideTest {

    /**
     * Test for the add method in Decide class.
     */
    @Test
    void addWorks() {
        assertEquals(5, Decide.add(2, 3));
    }

    /**
     * Contract: LIC 5 returns true when there exist two consecutive data points
     * where the second point has a smaller x-coordinate than the first.
     */
    @Test
    void lic5_positiveTest_xDecreases() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(2.0, 2.0),
                new Point2D.Double(1.0, 1.0)
        };
        assertTrue(decide.lic5());
    }

    /**
     * Contract: LIC 5 returns false when all consecutive data points have
     * non-decreasing x-coordinates.
     */
    @Test
    void lic5_negativeTest_xNeverDecreases() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(1.0, 1.0),
                new Point2D.Double(2.0, 2.0),
                new Point2D.Double(3.0, 3.0)
        };
        assertFalse(decide.lic5());
    }

    /**
     * Contract: LIC 5 returns false when fewer than two data points are provided.
     */
    @Test
    void lic5_negativeTest_onePoint() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {new Point2D.Double(1.0, 1.0)};
        assertFalse(decide.lic5());
    }
}
