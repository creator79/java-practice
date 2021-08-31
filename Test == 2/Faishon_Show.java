// mr. pepper has a very weird, fashion sense(as given below). 

// Suppose you are given n elements in an array, thus elements should follow a 
// fashion(discipline).

// ele1 < ele2 > ele3 < ele4 > ele5 ........ (upto nth element).

// your task is to re-arrange the "DISTINCT" elements of array in proper fashion in O(n) time.
// The converted array should be in form a < b > c < d > e < f.

// Example : 
// input {5, 4, 3, 2}
// output {4, 5, 2, 3}
// Input Format

// 1. N (a number, representing size of input)
// 2. ele1 , ele2 , ele3 , ...... elen ( N more inputs,elements)
// Constraints

// 0 <= N <= 100,000
// Output Format

// space seperated array elements(following proper fashion/discipline).
// Sample Input 0

// 4
// 5 4 3 2
// Sample Output 0

// 4 5 2 3
// Sample Input 1

// 7
// 3 -1 5 8 6 2 1
// Sample Output 1

// -1 5 3 8 2 6 1


import java.io.*;
import java.util.*;
 
public class Faishon_Show{
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int flag = 1; // flag to do alternative works
        for (int i = 1; i < n; i++) {
            int a = arr[i - 1];
            int b = arr[i];
            if (flag == 1) { 
                // lhs < rhs - condition 1
                if (a < b) {
                    // print left one
                    System.out.print(a + " ");
                } else { 
                    // right one is printed hence make left as new right one so as to help next iteration
                    System.out.print(b + " ");
                    arr[i] = a;
                }
            } else {
                // lhs > rhs - condition 2
                if (a > b) {
                    // print left one
                    System.out.print(a + " ");
                } else {
                    // right one is printed hence make left as new right one so as to help next iteration
                    System.out.print(b + " ");
                    arr[i] = a;
                }
            }
            // changing flag
            flag *= -1;
        }
        // all items are printed in required order except the last one which would be in order only
        System.out.println(arr[n - 1]);
    }
}