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
     * Contract: LIC 12 returns true when there exists at least one pair of points
     * separated by K_PTS points with distance > LENGTH1 and at least one pair
     * separated by K_PTS points with distance < LENGTH2.
     */

    @Test
    void lic12_positiveTest() {
        Decide decide = new Decide();
        decide.K_PTS = 2;
        decide.LENGTH1 = 5.0;
        decide.LENGTH2 = 2.0;
        decide.COORDINATES = new Point2D.Double[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),   
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(6, 0),   
            new Point2D.Double(0, 0)
        };
        assertTrue(decide.lic12());
    }

    /**
     * Contract: LIC 12 returns false when there is no pair of points
     * separated by K_PTS points with distance > LENGTH1
     */

    @Test
    void lic12_negativeTest() {
        Decide decide = new Decide();
        decide.K_PTS = 2;
        decide.LENGTH1 = 10.0; 
        decide.LENGTH2 = 2.0;  
        decide.COORDINATES = new Point2D.Double[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 0)
        };
        assertFalse(decide.lic12());
    }

    /**
     * Contract: LIC 12 returns false when there are too few points
     */

    @Test
    void lic12_invalidTest_tooFewPoints() {
        Decide decide = new Decide(); 
        decide.K_PTS = 1;
        decide.LENGTH1 = 1.0;
        decide.LENGTH2 = 1.0;
        decide.COORDINATES = new Point2D.Double[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0)
        };
        assertFalse(decide.lic12());
    }
    /**
     * Contract: LIC 12 throws IllegalArgumentException when LENGTH2 is negative
     */

    @Test
    void lic12_invalidTest_throwsException() {
        Decide decide = new Decide(); 
        decide.K_PTS = 1;
        decide.LENGTH1 = 1.0;
        decide.LENGTH2 = -1.0;
        decide.COORDINATES = new Point2D.Double[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0)
        };
        assertThrows(IllegalArgumentException.class, () -> {
        decide.lic12();
    });
    }
    




}
