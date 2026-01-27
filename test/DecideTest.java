import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;

public class DecideTest {

   /**
     * LIC 3 returns true when any three points 
     * form a triangle with an area greater than AREA1.
     */
    @Test
    void lic3_positiveTest_areaIsGreater() {
        Point2D.Double[] points = {
            new Point2D.Double(0, 0), 
            new Point2D.Double(10, 0), 
            new Point2D.Double(0, 10)
        };
        assertTrue(Decide.lic3(points, 40.0));
    }

    /**
     *LIC 3 returns false when the triangle area 
     * is exactly equal to or less than AREA1.
     */
    @Test
    void lic3_negativeTest_areaIsEqual() {
        Point2D.Double[] points = {
            new Point2D.Double(0, 0), 
            new Point2D.Double(10, 0), 
            new Point2D.Double(0, 10)
        };
        assertFalse(Decide.lic3(points, 50.0));
    }
}