//todo  1. You are given a partially written BinaryTree class.
//todo  2. You are given an element.
//todo  3. You are required to complete the body of find and nodeToRoot function. The functions are expected to 
// todo     3.1. find -> return true or false depending on if the data is found in binary tree.
// todo     3.2. nodeToRoot -> returns the path from node (correspoding to data) to root in 
//todo      form of an arraylist (root being the last element)
// todo 4. Input iand Output is managed for you.


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
//todo     The problem here is to find a particular node in the given input binary tree.

// todo    The problem is based on traversals on the tree.
//todo     Here we only need to traverse the tree and check for every node whether the current node is the desired node or not. 
//todo     If yes, then we return true and if we reach the end of the branch then we return false, 
//todo     if in any case, we get true then we can simply return true without checking the rest of the branches.

  public static boolean find(Node node, int data){
    if(node == null){
      return false;
    }

    if(node.data == data){
      return true;
    }

    boolean filc = find(node.left, data);
    if(filc){
      return true;
    }

    boolean firc = find(node.right, data);
    if(firc){
      return true;
    }

    return false;
  }
//todo   The problem here is to get the desired node to the root path in the given input generic tree.

// todo  The problem is based on traversals on the tree. Here we need to traverse the tree and check for every node whether the current node is the desired node or not. 
// todo  If yes, then we return an ArrayList having the node value in it and if we reach the end of the branch then we return an empty ArrayList 
// todo  if in any case, we get a non-empty ArrayList then we add the current node to the list and return immediately without considering the following branches as the search there would be redundant. 
// todo  So in the end, we will have a path from the desired node then way back to the root node. This problem is similar to our previous problem, Find in a binary tree where we are finding the desired node 
// todo  if found then instead of returning a Boolean this time we are returning an ArrayList containing the path.

// Below is the implementation in Java:



  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    if(node == null){
      return new ArrayList<>();
    }

    if(node.data == data){
      ArrayList<Integer> list = new ArrayList<>();
      list.add(node.data);
      return list;
    }

    ArrayList<Integer> llist = nodeToRootPath(node.left, data);
    if(llist.size() > 0){
      llist.add(node.data);
      return llist;
    }

    ArrayList<Integer> rlist = nodeToRootPath(node.right, data);
    if(rlist.size() > 0){
      rlist.add(node.data);
      return rlist;
    }

    return new ArrayList<>();
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
    boolean found = find(root, data);
    System.out.println(found);

    ArrayList<Integer> path = nodeToRootPath(root, data);
    System.out.println(path);
  }

}
                        