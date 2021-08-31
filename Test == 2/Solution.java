
// todo  ===================>  Contest Link https://www.hackerrank.com/pp-2021-jan-15-test-2  ===============================>



// you are given radius & center of two circles. Find out whether the given circles :
// touches each other at one point
// touches each other at two points
// are far-apart from each other
// one circle overlap's other 
// print "overlaps" or "far-apart" or "touches at 1 point" or "touches at 2 point" or "concentric" , accordingly.
// Input Format

// x(x1) & y(y1) center coordinates of circle 1
// x(x2) & y(y2) center coordinates of circle 2
// radius(r1) of circle 1
// radius(r2) of circle 2
// Constraints

// x1,x2,y1,y2 are integers & r1,r2 are positive numbers
// Output Format

// print "overlaps" or "far-apart" or "touches at 1 point" or "touches at 2 point" or "concentric"

// Sample Input 0

// 0 0
// 0 0
// 2
// 5
// Sample Output 0

// concentric


import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt(), y1 = sc.nextInt();
        int x2 = sc.nextInt(), y2 = sc.nextInt();
        int r1 = sc.nextInt(), r2 = sc.nextInt();
        if (x1 == x2 && y1 == y2) {
            if (r1 == r2) {
                // overlap
                System.out.println("overlaps");
            } else {
                // concentric
                System.out.println("concentric");
            }
        } else {
            double distCenter = Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
            double distRadius = Math.pow((r1 + r2), 2);
            if (distCenter == distRadius) {
                // touch
                System.out.println("touches at 1 point");
            } else if (distCenter > distRadius) {
                // far-apart
                System.out.println("far-apart");
            } else {
                if (r1 - r2 < Math.sqrt(distCenter)) 
                    System.out.println("touches at 2 point"); // condition for touching at two points : d < r1 + r2 && d > r1 - r2
                else
                    System.out.println("overlaps"); // it intersects hence overlaps
            }
        }
    }
}
