/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.pkg10;

/**
 *
 * @author jacek
 */
public class Triangle {
    
    public double x, y, z;

    public Triangle (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double circumference (Triangle e) {
        return e.x + e.y + e.z;
    }

    public static double area (Triangle e) {
        double p = (e.x + e.y + e.z) / 2;
        return Math.sqrt(p * (p - e.x) * (p - e.y) * (p - e.z));
    }

    public static boolean right (Triangle e) {
        return (
            (e.x * e.x == e.y * e.y + e.z * e.z) ||
            (e.y * e.y == e.x * e.x + e.z * e.z) ||
            (e.z * e.z == e.x * e.x + e.y * e.y)
        );
    }

    @Override
    public String toString() { 
        return x + ", " + y + ", " + z;
    }
}
