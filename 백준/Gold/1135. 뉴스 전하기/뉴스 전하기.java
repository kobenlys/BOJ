import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static List<List<Integer>> list = new ArrayList<>();
    public static int[] dp;
    public static int[] depths;

    public static class Node implements Comparable<Node> {
        int next, depth;

        public Node(int next, int depth) {
            this.next = next;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return o.depth - depth;
        }
    }

    public static int dfs(int start) {

        int res = 0;
        int time = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int next : list.get(start)) {
            int depth = dfs(next);
            pq.offer(new Node(next, depth));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            time++;
            res = Math.max(res, now.depth + time);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        depths = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < N; i++) {
            int parentNode = Integer.parseInt(st.nextToken());
            list.get(parentNode).add(i);
        }


        int time = dfs(0);
        if (N == 1) time = 0;
        System.out.println(time);
    }
}