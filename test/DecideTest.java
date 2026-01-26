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
     * Contract: LIC 9 returns true when there exist three data points separated by
     * C_PTS and D_PTS consecutive points that form an angle outside the range
     * (PI - EPSILON, PI + EPSILON).
     */
    @Test
    void lic9_positiveTest_rightAngle() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 1.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 0.0)
        };
        decide.C_PTS = 1;
        decide.D_PTS = 1;
        decide.EPSILON = 0.1;
        // angle at (0,0) between (0,1) and (1,0) is 90 degrees
        assertTrue(decide.lic9());
    }

    /**
     * Contract: LIC 9 returns false when all valid three-point combinations form
     * angles within the range (PI - EPSILON, PI + EPSILON).
     */
    @Test
    void lic9_negativeTest_straightLine() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(2.0, 0.0)
        };
        decide.C_PTS = 1;
        decide.D_PTS = 1;
        decide.EPSILON = 0.1;
        // all points on a line, angle at vertex = PI
        assertFalse(decide.lic9());
    }

    /**
     * Contract: LIC 9 returns false when there are not enough data points.
     */
    @Test
    void lic9_negativeTest_tooFewPoints() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(1.0, 1.0),
                new Point2D.Double(2.0, 0.0),
                new Point2D.Double(3.0, 1.0)
        };
        decide.C_PTS = 1;
        decide.D_PTS = 1;
        decide.EPSILON = 0.1;
        assertFalse(decide.lic9());
    }
}
