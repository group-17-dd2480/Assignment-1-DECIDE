import java.awt.geom.Point2D;

// https://www.geeksforgeeks.org/dsa/check-whether-a-given-point-lies-inside-a-triangle-or-not/
public class Decide {

    static enum CONNECTORS {
        NOTUSED, ORR, ANDD
    }

    static enum COMPTYPE {
        LT, EQ, GT
    };

    double LENGTH1; // Length in LICs 0, 7, 12
    double RADIUS1; // Radius in LICs 1, 8, 13
    double EPSILON; // Deviation from PI in LICs 2, 9
    double AREA1; // Area in LICs 3, 10, 14
    int Q_PTS; // No. of consecutive points in LIC 4
    int QUADS; // No. of quadrants in LIC 4
    double DIST; // Distance in LIC 6
    int N_PTS; // No. of consecutive pts. in LIC 6
    int K_PTS; // No. of int. pts. in LICs 7, 12
    int A_PTS; // No. of int. pts. in LICs 8, 13
    int B_PTS; // No. of int. pts. in LICs 8, 13
    int C_PTS; // No. of int. pts. in LIC 9
    int D_PTS; // No. of int. pts. in LIC 9
    int E_PTS; // No. of int. pts. in LICs 10, 14
    int F_PTS; // No. of int. pts. in LICs 10, 14
    int G_PTS; // No. of int. pts. in LIC 11
    double LENGTH2; // Maximum length in LIC 12
    double RADIUS2; // Maximum radius in LIC 13
    double AREA2; // Maximum area in LIC 14
    int NUMPOINTS;

    CONNECTORS[][] LCM2;
    Point2D.Double[] COORDINATES;
    boolean[][] PUM;
    boolean[] CMV, FUV;
    boolean LAUNCH;

    /**
     * @param a
     *            first integer
     * @param b
     *            second integer
     * @return the sum of a and b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Helper function to calculate Euclidian distance between two points
     * 
     * @param p1
     *            Point 1
     * @param p2
     *            Point 2
     * @return Euclidian distance between points 1 and 2
     */
    public static double distSq(Point2D.Double p1, Point2D.Double p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    /**
     * Helper function to calculate area of a triangle by three coordinates
     * 
     * @param p1
     *            Point 1
     * @param p2
     *            Point 2
     * @param p3
     *            Point 3
     * @return area of the triangle formed from points 1, 2, and 3
     */
    public static double triangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        return 0.5 * Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
    }

    /**
     * Helper function to determine if three points can be contained in a circle of radius R, following Welzl MEC algo
     * https://www.geeksforgeeks.org/dsa/minimum-enclosing-circle-using-welzls-algorithm/
     * 
     * @param p1
     *            Point 1
     * @param p2
     *            Point 2
     * @param p3
     *            Point 3
     * @param radius
     *            the radius of the circle
     * @return whether points 1, 2, and 3 can be contained in a circle with radius radius
     */
    public static boolean fitInCircle(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, double radius) {

        // Calc the distance between two points and diam to check if the distance between two points is > the diameter if not they cannot fit
        double d12Sq = distSq(p1, p2);
        double d23Sq = distSq(p2, p3);
        double d31Sq = distSq(p3, p1);
        double diamSq = 4.0 * radius * radius;

        // diamSq = (2R)^2, check if any two points are farther apart than the diam of the circle
        // if so no circle of radius R can contain both at the same time
        if (d12Sq > diamSq || d23Sq > diamSq || d31Sq > diamSq) {
            return false;
        }

        // Right or obtuse triangle check: the longest side forms the diameter of the
        // minimum enclosing circle, so containment within the given radius is ensured
        if (d12Sq + d23Sq <= d31Sq || d12Sq + d31Sq <= d23Sq || d23Sq + d31Sq <= d12Sq) {
            return true;
        }

        // Check if circumradius R = (abc / 4*Area) is less or equal to radius
        double a = Math.sqrt(d12Sq);
        double b = Math.sqrt(d23Sq);
        double c = Math.sqrt(d31Sq);
        double area = triangleArea(p1, p2, p3);

        if (area == 0)
            return true;

        double circumradius = (a * b * c) / (4.0 * area);
        return circumradius <= radius;
    }

    /**
     * Helper to calculate angle at vertex formed by p1-vertex-p3 using Law of Cosines
     * https://www.geeksforgeeks.org/maths/law-of-cosine/
     *
     * @param p1
     *            Point 1
     * @param vertex
     *            Vertex point
     * @param p3
     *            Point 3
     * @return angle in radians
     */
    private static double calculateAngle(Point2D.Double p1, Point2D.Double vertex, Point2D.Double p3) {
        double a2 = distSq(vertex, p3);
        double b2 = distSq(vertex, p1);
        double c2 = distSq(p1, p3);

        double a = Math.sqrt(a2);
        double b = Math.sqrt(b2);

        double cosAngle = (a2 + b2 - c2) / (2.0 * a * b);
        cosAngle = Math.max(-1.0, Math.min(1.0, cosAngle));  // Clamp to [-1, 1]
        return Math.acos(cosAngle);
    }

    /**
     * LIC 0 checks if two points are separated by a distance bigger than length 1
     * 
     * @return whether criteria LIC 0 is true or false
     */

    public boolean lic0() {
        // Conditions to check for at least two points, and non-negative
        if (COORDINATES == null || COORDINATES.length < 2)
            return false;
        if (LENGTH1 < 0)
            throw new IllegalArgumentException("length must be at least 0");

        // Compare dx^2 + dy^2 > lim^2
        double limitSq = LENGTH1 * LENGTH1;
        // iterate though pair and return true if conditions met
        for (int i = 1; i < COORDINATES.length; i++) {
            if (distSq(COORDINATES[i - 1], COORDINATES[i]) > limitSq)
                return true;
        }
        return false;
    }

    /**
     * LIC 1 checks if there exist one set of three data points that can't be contained within or on a circle of RADIUS1
     * 
     * @return whether criteria LIC 1 is true or false
     */
    public boolean lic1() {
        // Checks if three points are given and non negative int
        if (COORDINATES == null || COORDINATES.length < 3) {
            return false;
        }
        if (RADIUS1 < 0) {
            throw new IllegalArgumentException("Radius must be bigger than 0");
        }
        // Check every tripplet of concec points i, i+1 and i+2
        for (int i = 0; i < COORDINATES.length - 2; i++) {
            // returns true if the points CANNOT fit
            if (!fitInCircle(COORDINATES[i], COORDINATES[i + 1], COORDINATES[i + 2], RADIUS1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC 2 checks if
     * 
     * @return whether criteria LIC 2 is true or false
     */
    public boolean lic2() {
        // todo
        return false;
    }

    /**
     * LIC 3 checks if
     * 
     * @return whether criteria LIC 3 is true or false
     */
    public boolean lic3() {
        // todo
        return false;
    }

    /**
     * LIC 4 checks if
     * 
     * @return whether criteria LIC 4 is true or false
     */
    public boolean lic4() {
        // todo
        return false;
    }

    /**
     * LIC 5 checks if there exists two consecutive points where X[j] - X[i] < 0
     * 
     * @return whether criteria LIC 5 is true or false
     */
    public boolean lic5() {
        if (COORDINATES == null || COORDINATES.length < 2)
            return false;

        for (int i = 0; i < COORDINATES.length - 1; i++) {
            if (COORDINATES[i + 1].x - COORDINATES[i].x < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC 6 checks if
     * 
     * @return whether criteria LIC 6 is true or false
     */
    public boolean lic6() {
        // todo
        return false;
    }

    /**
     * LIC 7 checks if
     * 
     * @return whether criteria LIC 7 is true or false
     */
    public boolean lic7() {
        // todo
        return false;
    }

    /**
     * LIC 8 checks if
     * 
     * @return whether criteria LIC 8 is true or false
     */
    public boolean lic8() {
        // todo
        return false;
    }

    /**
     * LIC 9 checks if three points separated by C_PTS and D_PTS form an angle outside (PI +- EPSILON)
     * 
     * @return whether criteria LIC 9 is true or false
     */
    public boolean lic9() {
        if (COORDINATES == null || COORDINATES.length < 5)
            return false;
        if (EPSILON < 0 || EPSILON >= Math.PI)
            throw new IllegalArgumentException("epsilon must be in range [0, PI)");
        if (C_PTS < 1 || D_PTS < 1)
            throw new IllegalArgumentException("C_PTS and D_PTS must be >= 1");
        if (C_PTS + D_PTS > COORDINATES.length - 3)
            return false;

        for (int i = 0; i < COORDINATES.length - C_PTS - D_PTS - 2; i++) {
            Point2D.Double p1 = COORDINATES[i];
            Point2D.Double vertex = COORDINATES[i + C_PTS + 1];
            Point2D.Double p3 = COORDINATES[i + C_PTS + D_PTS + 2];

            if ((p1.x == vertex.x && p1.y == vertex.y) || (p3.x == vertex.x && p3.y == vertex.y))
                continue;

            double angle = calculateAngle(p1, vertex, p3);
            if (angle < (Math.PI - EPSILON) || angle > (Math.PI + EPSILON))
                return true;
        }
        return false;
    }

    /**
     * LIC 10 checks if
     * 
     * @return whether criteria LIC 10 is true or false
     */
    public boolean lic10() {
        // todo
        return false;
    }

    /**
     * LIC 11 checks if
     * 
     * @return whether criteria LIC 11 is true or false
     */
    public boolean lic11() {
        // todo
        return false;
    }

    /**
     * LIC 12 checks if
     * 
     * @return whether criteria LIC 12 is true or false
     */
    public boolean lic12() {
        // todo
        return false;
    }

    /**
     * LIC 13 checks if
     * 
     * @return whether criteria LIC 13 is true or false
     */
    public boolean lic13() {
        // todo
        return false;
    }

    /**
     * LIC 14 checks if three points separated by E_PTS and F_PTS form triangle with area > AREA1 AND area < AREA2
     *
     * @return whether criteria LIC 14 is true or false
     */
    public boolean lic14() {
        if (COORDINATES == null || COORDINATES.length < 5)
            return false;
        if (AREA1 < 0 || AREA2 < 0)
            throw new IllegalArgumentException("area values must be >= 0");
        if (E_PTS < 1 || F_PTS < 1)
            throw new IllegalArgumentException("E_PTS and F_PTS must be >= 1");
        if (E_PTS + F_PTS > COORDINATES.length - 3)
            return false;

        boolean fitA1 = false;
        boolean fitA2 = false;

        for (int i = 0; i < COORDINATES.length - E_PTS - F_PTS - 2; i++) {
            Point2D.Double p1 = COORDINATES[i];
            Point2D.Double p2 = COORDINATES[i + E_PTS + 1];
            Point2D.Double p3 = COORDINATES[i + E_PTS + F_PTS + 2];

            double area = triangleArea(p1, p2, p3);

            if (!fitA1 && area > AREA1)
                fitA1 = true;
            if (!fitA2 && area < AREA2)
                fitA2 = true;

            if (fitA1 && fitA2)
                return true;
        }
        return fitA1 && fitA2;
    }

    /**
     * Issue #20: Sets the CMV by calling each LIC function
     */
    public void setCMV() {
        CMV[0] = lic0();
        CMV[1] = lic1();
        CMV[2] = lic2();
        CMV[3] = lic3();
        CMV[4] = lic4();
        CMV[5] = lic5();
        CMV[6] = lic6();
        CMV[7] = lic7();
        CMV[8] = lic8();
        CMV[9] = lic9();
        CMV[10] = lic10();
        CMV[11] = lic11();
        CMV[12] = lic12();
        CMV[13] = lic13();
        CMV[14] = lic14();
    }

    /**
     * Issue #21: Create the Preliminary Unlocking Matrix (PUM) from CMV and LCM.
     *
     * Rules:
     *  - NOTUSED => true
     *  - ANDD    => CMV[i] && CMV[j]
     *  - ORR     => CMV[i] || CMV[j]
     */
    public void setPUM() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                CONNECTORS c = LCM2[i][j];
                switch (c) {
                    case NOTUSED -> PUM[i][j] = true;
                    case ANDD -> PUM[i][j] = CMV[i] && CMV[j];
                    case ORR -> PUM[i][j] = CMV[i] || CMV[j];
                }
            }
        }
    }

    /**
     * Issue #22: Create the Final Unlocking Vector (FUV) from PUM.
     *
     * FUV[i] is true iff all PUM[i][j] values are true.
     */
    public void setFUV() {
        for (int i = 0; i < 15; i++) {
            boolean ok = true;
            for (int j = 0; j < 15; j++) {
                if (!PUM[i][j]) {
                    ok = false;
                    break;
                }
            }
            FUV[i] = ok;
        }
    }

    /**
     * Issue #23: Create the launch decision from FUV.
     *
     * LAUNCH is true iff all entries in FUV are true.
     */
    public void setLAUNCH() {
        for (int i = 0; i < 15; i++) {
            if (!FUV[i]) {
                LAUNCH = false;
                return;
            }
        }
        LAUNCH = true;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3));
        Decide decideProblem = new Decide();
    }

    public void Decide() {
        LCM2 = new CONNECTORS[15][15];
        COORDINATES = new Point2D.Double[100];
        PUM = new boolean[15][15];
        CMV = new boolean[15];
        FUV = new boolean[15];
    }
}
