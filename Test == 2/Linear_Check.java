// Check whether all points lie ona horizontal or vertical line ?
// print "YES" if all points lie on the line & "NO" otherwise.
// Input Format

// 1. a number n
// 2. n more inputs representing co-ordinates
// 	x y (format)
// Constraints

// n > 0 xi,yi > 0

// Output Format

// "YES" if all points lie on the line & "NO" otherwise
// Sample Input 0

// 5
// 0 1
// 0 2
// 1 3
// 0 4
// 0 5
// Sample Output 0

// NO


import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
        boolean xflag = true, yflag = true;
        int[] xarr = new int[n], yarr = new int[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            xarr[i] = x;
            yarr[i] = y;
        }
        int xc = xarr[0], yc = yarr[0];
        for (int i = 1; i < n; i++) {
            if (xflag && xarr[i] != xc)
                xflag = false;
            if (yflag && yarr[i] != yc)
                yflag = false;
        }
        if (xflag || yflag)
            System.out.println("YES");
        else
            System.out.println("NO");
 
    }
}