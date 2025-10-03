import java.io.*;
import java.util.*;

public class Main {

    public static Map<Integer, Node> tree = new HashMap<>();
    public static int nodeCnt;

    public static class Node {

        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void dfs(int node) {
        Node now = tree.get(node);
        if (now.right > 0) {
            nodeCnt++;
            dfs(now.right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            int center = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree.put(center, new Node(left, right));
        }

        dfs(1);
        System.out.println((N -1) * 2 - nodeCnt);
    }
}