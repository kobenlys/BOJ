import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] fuel;
    public static List<List<Node>> list = new ArrayList<>();

    public static class Node implements Comparable<Node> {
        int goal, minCost;
        long len;

        public Node(int goal, long len, int minCost) {
            this.goal = goal;
            this.len = len;
            this.minCost = minCost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(len, o.len);
        }
    }

    public static long dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[][] dp = new long[N + 1][2501];
        long res = Long.MAX_VALUE;

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[s][fuel[s]] = 0;
        pq.offer(new Node(s, 0, fuel[s]));

        while (!pq.isEmpty()) {

            Node now = pq.poll();
            if (dp[now.goal][now.minCost] < now.len) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {

                long cost = tmp.len * now.minCost;
                if (dp[tmp.goal][now.minCost] > now.len + cost) {
                    dp[tmp.goal][now.minCost] = now.len + cost;
                    if (tmp.goal == N) {
                        res = Math.min(res, dp[tmp.goal][now.minCost]);
                    }

                    pq.offer(new Node(tmp.goal, dp[tmp.goal][now.minCost], Math.min(fuel[tmp.goal], now.minCost)));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fuel = new int[N + 1];
        list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            list.add(new ArrayList<>());
            fuel[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v, -1));
            list.get(e).add(new Node(s, v, -1));
        }

        long answer = dijkstra(1);
        System.out.println(answer);
    }
}