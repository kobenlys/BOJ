import java.io.*;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static class node {
        int val;
        node left, right;

        public node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        // 전위순회 순으로 입력받는다면
        public void insert(int n) {
            // 상위 노드 보다 n이 작다면 left노드에 입력
            if (n < val) {
                if (this.left == null) {
                    this.left = new node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                // 상위노드 보다 n이 크다면 right노드에 입력
                if (this.right == null) {
                    this.right = new node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    // 후위순회 탐색 + 출력
    public static void postorder(node nd) {
        if (nd == null) return;
        postorder(nd.left);
        postorder(nd.right);
        sb.append(nd.val).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 루트노드 초기 생성.
        node root = new node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }
            // 자식노드 설정.
            int N = Integer.parseInt(input);
            root.insert(N);
        }
        // 후위순회 탐색
        postorder(root);
        System.out.print(sb);
    }
}