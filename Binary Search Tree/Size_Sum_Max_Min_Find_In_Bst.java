import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  // todo               <==============================  PART - I ================================>

// The problem here deals with retrieving the size of the given binary search tree or the number of nodes present in the given tree.

// To solve this we will use recursion.

// Firstly let us try to understand the high-level approach of recursion, to get the size of the root node we need to have the size of all its children, and adding 1 to it will give our result. Here 1 is added to include root node too. Now let us try to analyze it at low-level, so for any node, we will recursively call all the children to get their size and add them collectively and then return.

// Here we put our faith that if we can solve the problem for the root node then we assume that the data for children will be handled by the recursion.

  public static int size(Node node) {
    // write your code here
    if (node == null) {
          return 0;
      }
      int ls = size(node.left);
      int rs = size(node.right);
      int ts = ls + rs + 1;
      return ts;
  }
  
  // todo               <==============================  PART - II ================================>

// todo  The problem here deals with retrieving the sum of the given binary search tree. To solve this we will use recursion. Firstly let us try to understand the high-level approach of recursion, to get the sum of the root node we need to have the sum of both its left subtree and right subtree, and adding the roots node value to it will give our result. Now let us try to analyze it at low-level, so for any node, we will recursively call all the children to get their subtree sum and add them collectively and then return.

// todo  Here we put our faith that if we can solve the problem for the root node then we assume that the data for children will be handled by the recursion.

//todo   Below is the implementation in Java:



  public static int sum(Node node) {
    // write your code here
     if (node == null) {
          return 0;
      }
      int ls = sum(node.left);
      int rs = sum(node.right);
      int ts = ls + rs + nod
      return ts;
  }

  // todo               <==============================  PART - III ================================>

// The problem here deals with retrieving the maximum value node of the given binary search tree. To solve this we will use recursion.

// To get the maximum valued node we need to check only for the right subtree as for BST the maximum value lies only on the right subtree. So we need to call for the right subtree whenever possible and when the right subtree is null we can conclude that our current node is the maximum valued node in the BST as if it is not the maximum valued node then it must have a right subtree which is not the case.

// Below is the implementation in Java:



  public static int max(Node node) {
      
       if (node.right == null) {
       return node.data;
    } else {
     return max(node.right);
    }
    
  }

  // todo               <==============================  PART - IV ================================>

  public static int min(Node node) {
    // write your code 
    
       if (node.left == null) {
       return node.data;
    } else {
     return min(node.left);
    }
  }
  
  // todo               <==============================  PART - VI ================================>


  public static boolean find(Node node, int data){
    // write your code here
    if (node == null) {
         return
     }
     if (data > node.data) {
         return find(node.right, data);
     } else if (data < node.data) {
         return find(node.left, data);
     } else {
         return true;
     }
  }  

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int min = min(root);
    boolean found = find(root, data);

    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(min);
    System.out.println(found);
  }

}