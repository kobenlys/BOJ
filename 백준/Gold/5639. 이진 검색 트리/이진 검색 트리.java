import java.io.*;
import java.util.*;

public class Main {

    public static class node {
        int val;
        node left, right;

        public node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public void insert(int n) {

            if (n < val) {
                if (this.left == null) {
                    this.left = new node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = new node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    public static void postorder(node nd) {
        if (nd == null) return;
        postorder(nd.left);
        postorder(nd.right);
        System.out.println(nd.val);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        node root = new node(Integer.parseInt(br.readLine()));
        String input = "";
        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }
            int N = Integer.parseInt(input);
            root.insert(N);
        }

        postorder(root);
    }
}