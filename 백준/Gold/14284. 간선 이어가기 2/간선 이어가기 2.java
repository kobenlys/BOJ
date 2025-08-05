import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static List<List<Node>> graph = new ArrayList<>();

    public static class Node implements Comparable<Node> {

        int goal, value;

        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            for (Node tmp : graph.get(now.goal)) {

                if (dist[tmp.goal] > dist[now.goal] + tmp.value) {
                    dist[tmp.goal] = dist[now.goal] + tmp.value;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, v));
            graph.get(e).add(new Node(s, v));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end));

    }
}
