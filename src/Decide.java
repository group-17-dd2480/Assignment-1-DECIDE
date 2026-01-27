import java.awt.geom.Point2D;
public class Decide {

    // Helper function to calculate area of a triangle by three coordinates, 
    private static double triangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
        return 0.5 * Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
    }
    // Lic 3 checks triangle area
    // Checks if any three points form a triangle with an area greater than area 1
    public static boolean lic3(Point2D.Double [] points, double area1) {
        if (points == null || points.length < 3) {
            return false;}
        if (area1 < 0) {
            throw new IllegalArgumentException("Radius must be bigger than 0");
        }
        for (int i = 0; i < points.length -2; i++){
            double area = triangleArea(points[i], points[i+1], points[i+2]);
            if (area > area1)
                return true;
            }
            return false;
        }
    }
