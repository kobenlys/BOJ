import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, K;
    public static List<List<Node>> list;

    public static class Node implements Comparable<Node> {

        int goal, val;

        public Node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }
    }

    public static int dijkstra(int start, int end) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {

            Node nd = pq.poll();

            if (dist[nd.goal] < nd.val) {
                continue;
            }

            for (Node tmp : list.get(nd.goal)) {
                if (dist[tmp.goal] > dist[nd.goal] + tmp.val) {
                    dist[tmp.goal] = dist[nd.goal] + tmp.val;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        int minPath = dijkstra(1, N);
        int minPathWithK = dijkstra(1, K) + dijkstra(K, N);
        System.out.println(minPath >= minPathWithK ? "SAVE HIM" : "GOOD BYE");
    }
}