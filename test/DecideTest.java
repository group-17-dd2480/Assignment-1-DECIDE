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
    void lic12_positiveTest_bothDistancesMet() {
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
            new Point2D.Double(7, 0),   
            new Point2D.Double(0, 0)
        };
        assertTrue(decide.lic12());
    }

    /**
     * Contract: LIC 12 returns false when there is no pair of points
     * separated by K_PTS points with distance > LENGTH1
     */

    @Test
    void lic12_negativeTest_distanceSmaller() {
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
        static class setCMVTestDecideStub extends Decide {
        boolean[] ret = new boolean[15];
        @Override public boolean lic0(){ return ret[0]; }
        @Override public boolean lic1(){ return ret[1]; }
        @Override public boolean lic2(){ return ret[2]; }
        @Override public boolean lic3(){ return ret[3]; }
        @Override public boolean lic4(){ return ret[4]; }
        @Override public boolean lic5(){ return ret[5]; }
        @Override public boolean lic6(){ return ret[6]; }
        @Override public boolean lic7(){ return ret[7]; }
        @Override public boolean lic8(){ return ret[8]; }
        @Override public boolean lic9(){ return ret[9]; }
        @Override public boolean lic10(){ return ret[10]; }
        @Override public boolean lic11(){ return ret[11]; }
        @Override public boolean lic12(){ return ret[12]; }
        @Override public boolean lic13(){ return ret[13]; }
        @Override public boolean lic14(){ return ret[14]; }
    }


    /**
     * Tests that setCMV correctly maps each LIC functions return value
     * to the corresponding index in the CMV array.
     */
    @Test
    void setCMV_mapsEachLICToSameIndex() {
        setCMVTestDecideStub d = new setCMVTestDecideStub();
        d.CMV = new boolean[15];
    
        for (int i = 0; i < 15; i++) d.ret[i] = false;
        d.setCMV();
    
        int k = 7;
        d.ret[k] = true;
        d.setCMV();
    
        for (int i = 0; i < 15; i++) {
            if (i == k) assertTrue(d.CMV[i]);
            else assertFalse(d.CMV[i]);
        }
    }

    /** 
     * Tests that PUM is correctly set based on CMV and LCM2
     */
    @Test
    void setPUM_appliesConnectorsCorrectly() {
        Decide d = new Decide();

        d.CMV = new boolean[15];
        d.PUM = new boolean[15][15];
        d.LCM2 = new Decide.CONNECTORS[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                d.LCM2[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }

        d.CMV[0] = true;  d.CMV[1] = false; // ANDD => false
        d.CMV[2] = false; d.CMV[3] = true; // ORR  => true 

        // Set connectors
        d.LCM2[0][1] = d.LCM2[1][0] = Decide.CONNECTORS.ANDD;
        d.LCM2[2][3] = d.LCM2[3][2] = Decide.CONNECTORS.ORR;

        d.setPUM();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if ((i == 0 && j == 1) || (i == 1 && j == 0)) {
                    assertFalse(d.PUM[i][j]);
                } else if ((i == 2 && j == 3) || (i == 3 && j == 2)) {
                    assertTrue(d.PUM[i][j]);
                } else {
                // All other entries should be true (NOTUSED)
                assertTrue(d.PUM[i][j]);

                }
            }
        }
    }

    /**
     * Tests that FUV is correctly set based on PUM
     */
    @Test
    void setFUV_setsFUVBasedOnPUM() {
        Decide d = new Decide();
        d.PUM = new boolean[15][15];
        d.FUV = new boolean[15];
        // Set PUM such that:
        // FUV[0] = true (all true)
        // FUV[1] = false (one false)
        for (int j = 0; j < 15; j++) {
            d.PUM[0][j] = true;
            d.PUM[1][j] = true;
        }
        d.PUM[1][7] = false; // One false entry for row 1
        d.setFUV();
        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                assertTrue(d.FUV[i]);
            } else if (i == 1) {
                assertFalse(d.FUV[i]);
            } else {
                // All other FUV entries should be false (empty rows)
                assertFalse(d.FUV[i]);
            }
        }
    }

    /**
     * Tests that LAUNCH is true only if all FUV are true, and false otherwise
     */
    @Test
    void decide_launchIsTrueOnlyIfAllFUVTrue() {
        Decide d = new Decide();
        d.FUV = new boolean[15];
        // All true case
        for (int i = 0; i < 15; i++) {
            d.FUV[i] = true;
        }
        d.FUV[2] = false; // One false case
        d.setLAUNCH();
        assertFalse(d.LAUNCH);
        d.FUV[2] = true; // All true again
        d.setLAUNCH();
        assertTrue(d.LAUNCH);
    }
    }
    





