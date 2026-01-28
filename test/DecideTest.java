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
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(10, 0),
                new Point2D.Double(0, 10)
        };
        decide.AREA1 = 40.0;
        assertTrue(decide.lic3());
    }

    /**
     * LIC 3 returns false when the triangle area
     * is exactly equal to or less than AREA1.
     */
    @Test
    void lic3_negativeTest_areaIsEqual() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(10, 0),
                new Point2D.Double(0, 10)
        };
        decide.AREA1 = 50.0;
        assertFalse(decide.lic3());
    }

    /**
     * Contract: LIC 14 returns true when there exist three data points separated by
     * E_PTS and F_PTS consecutive points that form a triangle with area greater
     * than AREA1 AND less than AREA2.
     */
    @Test
    void lic14_positiveTest_bothConditionsMet() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(10.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(0.0, 1.0)
        };
        decide.E_PTS = 1;
        decide.F_PTS = 1;
        decide.AREA1 = 1.0;
        decide.AREA2 = 100.0;
        assertTrue(decide.lic14());
    }

    /**
     * Contract: LIC 14 returns false when no valid three-point combination forms
     * a triangle with area greater than AREA1.
     */
    @Test
    void lic14_negativeTest_areaTooSmall() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(0.0, 1.0)
        };
        decide.E_PTS = 1;
        decide.F_PTS = 1;
        decide.AREA1 = 100.0;
        decide.AREA2 = 100.0;
        assertFalse(decide.lic14());
    }

    /**
     * Contract: LIC 14 returns false when there are insufficient data points
     * to form a valid three-point set with the required separations.
     */
    @Test
    void lic14_negativeTest_tooFewPoints() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(2.0, 0.0),
                new Point2D.Double(3.0, 0.0)
        };
        decide.E_PTS = 1;
        decide.F_PTS = 1;
        decide.AREA1 = 100.0;
        decide.AREA2 = 100.0;
        assertFalse(decide.lic14());
    }

    /**
     * Contract: LIC 13 returns true when there exist three data points separated by
     * A_PTS and B_PTS consecutive points that cannot be contained in a circle of
     * radius RADIUS1 AND can be contained in a circle of radius RADIUS2.
     */
    @Test
    void lic13_positiveTest_bothConditionsMet() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 1.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(5.0, 5.0)
        };
        decide.A_PTS = 1;
        decide.B_PTS = 1;
        decide.RADIUS1 = 1.0;
        decide.RADIUS2 = 100.0;
        assertTrue(decide.lic13());
    }

    /**
     * Contract: LIC 13 returns false when all valid three-point combinations
     * can be contained in a circle of radius RADIUS1.
     */
    @Test
    void lic13_negativeTest_fitsInBothRadii() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 1.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(5.0, 5.0)
        };
        decide.A_PTS = 1;
        decide.B_PTS = 1;
        decide.RADIUS1 = 100.0;
        decide.RADIUS2 = 100.0;
        assertFalse(decide.lic13());
    }

    /**
     * Contract: LIC 13 returns false when there are not enough data points
     * to form a valid three-point set with the required separations.
     */
    @Test
    void lic13_negativeTest_tooFewPoints() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(1.0, 1.0),
                new Point2D.Double(2.0, 2.0),
                new Point2D.Double(3.0, 3.0)
        };
        decide.A_PTS = 1;
        decide.B_PTS = 1;
        decide.RADIUS1 = 100.0;
        decide.RADIUS2 = 100.0;
        assertFalse(decide.lic13());
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
        decide.COORDINATES = new Point2D.Double[] { new Point2D.Double(1.0, 1.0) };
        assertFalse(decide.lic5());
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

    /**
     * Contract: LIC 11 returns true when there exist two data points separated by
     * exactly G_PTS consecutive points and the second point has a smaller x-coordinate
     */
    @Test
    void lic11_positiveTest_xDecreases() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(2.0, 0.0),
                new Point2D.Double(10000.0, 10000.0),
                new Point2D.Double(1.0, 0.0)
        };
        decide.G_PTS = 1;
        assertTrue(decide.lic11());
    }

    /**
     * Contract: LIC 11 returns false when all pairs of data points separated by
     * G_PTS consecutive points have non-decreasing x-coordinates.
     */
    @Test
    void lic11_negativeTest_xIncreases() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(1.0, 0.0)
        };
        decide.G_PTS = 1;
        assertFalse(decide.lic11());
    }

    /**
     * Contract: LIC 11 returns false when there are not enough data points
     * to form a valid pair with G_PTS separations.
     */
    @Test
    void lic11_negativeTest_tooFewPoints() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(1.0, 0.0),
                new Point2D.Double(2.0, 0.0)
        };
        decide.G_PTS = 1;
        assertFalse(decide.lic11());
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
        /**
         * Contract: LIC 10 returns true when there exists at least one set of
         * three points separated by E_PTS and F_PTS that form a triangle
         * with area greater than AREA1.
         */

        @Test
        void lic10_positiveTest_areaGreater() {
            Decide decide = new Decide();
            decide.E_PTS = 1;
            decide.F_PTS = 1;
            decide.AREA1 = 1.0;
            decide.COORDINATES = new Point2D.Double[] {
                    new Point2D.Double(0, 0),
                    new Point2D.Double(0, 0),
                    new Point2D.Double(4, 0),
                    new Point2D.Double(0, 0),
                    new Point2D.Double(0, 4)
            };
            assertTrue(decide.lic10());
        }

        /**
         * Contract: LIC 10 returns false when no valid triple of points
         * forms a triangle with area greater than AREA1.
         */
        @Test
        void lic10_negativeTest_areaSmaller() {
            Decide decide = new Decide();
            decide.E_PTS = 1;
            decide.F_PTS = 1;
            decide.AREA1 = 10.0;

            decide.COORDINATES = new Point2D.Double[] {
                    new Point2D.Double(1, 1),
                    new Point2D.Double(1, 1),
                    new Point2D.Double(-1, 1),
                    new Point2D.Double(-1, 1),
                    new Point2D.Double(0, 0),
                    new Point2D.Double(0, 0)
            };

            assertFalse(decide.lic10());
        }

        /**
         * Contract: LIC 10 returns false when fewer than 5 points are provided.
         */
        @Test
        void lic10_invalidTest_tooFewPoints() {
            Decide decide = new Decide();
            decide.E_PTS = 1;
            decide.F_PTS = 1;
            decide.AREA1 = 1.0;

            decide.COORDINATES = new Point2D.Double[] {
                    new Point2D.Double(1, 1)
            };

            assertFalse(decide.lic10());
        }

        boolean[] ret = new boolean[15];

        @Override
        public boolean lic0() {
            return ret[0];
        }

        @Override
        public boolean lic1() {
            return ret[1];
        }

        @Override
        public boolean lic2() {
            return ret[2];
        }

        @Override
        public boolean lic3() {
            return ret[3];
        }

        @Override
        public boolean lic4() {
            return ret[4];
        }

        @Override
        public boolean lic5() {
            return ret[5];
        }

        @Override
        public boolean lic6() {
            return ret[6];
        }

        @Override
        public boolean lic7() {
            return ret[7];
        }

        @Override
        public boolean lic8() {
            return ret[8];
        }

        @Override
        public boolean lic9() {
            return ret[9];
        }

        @Override
        public boolean lic10() {
            return ret[10];
        }

        @Override
        public boolean lic11() {
            return ret[11];
        }

        @Override
        public boolean lic12() {
            return ret[12];
        }

        @Override
        public boolean lic13() {
            return ret[13];
        }

        @Override
        public boolean lic14() {
            return ret[14];
        }
    }

    /**
     * Tests that setCMV correctly maps each LIC functions return value
     * to the corresponding index in the CMV array.
     */
    @Test
    void setCMV_mapsEachLICToSameIndex() {
        setCMVTestDecideStub d = new setCMVTestDecideStub();
        d.CMV = new boolean[15];

        for (int i = 0; i < 15; i++)
            d.ret[i] = false;
        d.setCMV();

        int k = 7;
        d.ret[k] = true;
        d.setCMV();

        for (int i = 0; i < 15; i++) {
            if (i == k)
                assertTrue(d.CMV[i]);
            else
                assertFalse(d.CMV[i]);
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

        d.CMV[0] = true;
        d.CMV[1] = false; // ANDD => false
        d.CMV[2] = false;
        d.CMV[3] = true; // ORR => true

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

    /** Contract: LIC 7 returns true when there exist two points separated by exactly K_PTS intervening points that are at a distance greater than LENGTH1. */
    @Test
    void lic7_positiveTest_distanceIsGreater() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0), // Gap point 1
                new Point2D.Double(10, 10)
        };
        decide.K_PTS = 1;
        decide.LENGTH1 = 5.0;
        assertTrue(decide.lic7());
    }

    /* Contract: LIC 7 returns false when all points separated by K_PTS are at a distance less than or equal to LENGTH1. */
    @Test
    void lic7_negativeTest_distanceIsEqual() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0), // First gap
                new Point2D.Double(5, 0)
        };
        decide.K_PTS = 1;
        decide.LENGTH1 = 5.0;
        assertFalse(decide.lic7());
    }

    /* Contract: LIC 8 returns true when three points separated by A_PTS and B_PTS cannot be contained within a circle of RADIUS1. */
    @Test
    void lic8_positiveTest_cannotFitInCircle() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0), // First gap
                new Point2D.Double(10, 0),
                new Point2D.Double(0, 0), // Second gap
                new Point2D.Double(0, 10)
        };
        decide.A_PTS = 1;
        decide.B_PTS = 1;
        decide.RADIUS1 = 1.0;
        assertTrue(decide.lic8());
    }

    /**
     * Contract: LIC 8 returns false when all sets of points separated by
     * A_PTS and B_PTS can be contained within a circle of RADIUS1.
     */
    @Test
    void lic8_negativeTest_fitsInCircle() {
        Decide decide = new Decide();
        decide.COORDINATES = new Point2D.Double[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0), // First gap
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 0), // Second gap
                new Point2D.Double(0, 1)
        };
        decide.A_PTS = 1;
        decide.B_PTS = 1;
        decide.RADIUS1 = 10.0;
        assertFalse(decide.lic8());
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
