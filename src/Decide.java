import java.awt.geom.Point2D;
// https://www.geeksforgeeks.org/dsa/minimum-enclosing-circle-using-welzls-algorithm/
// https://www.geeksforgeeks.org/dsa/check-whether-a-given-point-lies-inside-a-triangle-or-not/
public class Decide {

    /**
     * @param a first integer
     * @param b second integer
     * @return the sum of a and b
     */

    
    public static int add(int a, int b) {
        return a + b;
    }

    // Helper function to calculate Euclidian distance between two points
    private static double distSq(Point2D.Double p1, Point2D.Double p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }
    
    // Helper function to calculate area of a triangle by three coordinates, 
    private static double triangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
        return 0.5 * Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
    }

    // Helper function to determine if three points can be contained in a circle of radius R, following Welzl MEC algo
    private static boolean minRadius(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, double radius){

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

    private static int quadrant(Point2D.Double p1){
        double x = p1.x;
        double y = p1.y;
        if(x>= 0 && y>=0)
            return 1;
        if(x< 0 && y>=0)
            return 2;
        if(x<0 && y<0)
            return 3;
        return 4;
    }

    // LIC 0 checks if two points are seperated by a distance bigger than length 1
    public static boolean lic0(Point2D.Double[] points, double length1) {
        // Conditions to check for at least two points, and non-negative
        if (points == null || points.length < 2)
            return false;
        if (length1 < 0)
            throw new IllegalArgumentException("length must be at least 0");

        // Compare dx^2 + dy^2 > lim^2
        double limitSq = length1 * length1;
        // iterate though pair and return true if conditions met
        for (int i = 1; i < points.length; i++) {
            if (distSq(points[i - 1], points[i]) > limitSq)
                return true;
        }
        return false;
    }

    // LIC 1 checks if there exist one set of thee data points that cant be contained within or on a circle of radius1

    public static boolean lic1(Point2D.Double[] points, double radius1) {
        // Checks if three points are given and non negative int
        if (points == null || points.length < 3) {
            return false;}
        if (radius1 < 0) {
            throw new IllegalArgumentException("Radius must be bigger than 0");
        } 
        // Check every tripplet of concec points i, i+1 and i+2
        for (int i = 0; i < points.length -2; i++){
            // returns truw if the points CANNOT fit
            if (!minRadius(points[i], points[i + 1], points[i + 2], radius1)){
                return true;
            }   
        }
        return false;
    }
    public static boolean lic4(Point2D.Double[] points, int q_pts, int quads, int NUMPOINTS) {
        if (q_pts < 2 || q_pts > NUMPOINTS) {
            throw new IllegalArgumentException("q_pts not within range");
        } 
        if (quads < 1 || quads > 3) {
            throw new IllegalArgumentException("quads not within range");
        }

        boolean[] seenQuads = new boolean[4];
        int distinctCount = 0;

        for (int i = 0; i < points.length - (q_pts-1); i++){
            for(int j =0; j<q_pts; j++){
                int quad = quadrant(points[i+j]);
                if (!seenQuads[quad - 1]) {
                    seenQuads[quad - 1] = true;
                    distinctCount++;
                }
            } 
            if(distinctCount > quads){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3));
    }
}
