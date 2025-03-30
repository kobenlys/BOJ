import java.io.*;
import java.util.*;

public class Main {
    public static long[] dist;
    public static List<List<Node>> list;
    public static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static class Node implements Comparable<Node> {
        int goal;
        long value;

        public Node(int goal, long value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(value, o.value);
        }
    }

    public static void dijkstra() {

        while (!pq.isEmpty()) {
            Node nd = pq.poll();
            if (dist[nd.goal] < nd.value) continue;

            for (Node tmp : list.get(nd.goal)) {
                if (dist[tmp.goal] > dist[nd.goal] + tmp.value) {
                    dist[tmp.goal] = dist[nd.goal] + tmp.value;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(e).add(new Node(s, v));
        }

        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int place = Integer.parseInt(st.nextToken());
            dist[place] = 0;
            pq.offer(new Node(place, 0));
        }

        dijkstra();
        int ansNode = 0;
        long maxDist = 0;
        for (int i = 1; i <= N; i++) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                ansNode = i;
            }
        }
        
        System.out.println(ansNode + "\n" + maxDist);
    }
}