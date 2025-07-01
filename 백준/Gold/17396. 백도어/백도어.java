import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static boolean[] ward;
    public static List<List<Node>> list = new ArrayList<>();

    public static class Node implements Comparable<Node> {

        int goal;
        long value;

        public Node(int goal, long value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.value, o.value);
        }
    }

    public static long djikstra() {
        long[] dist = new long[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        while (!pq.isEmpty()) {

            Node now = pq.poll();
            if (dist[now.goal] < now.value) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {
                if (!ward[tmp.goal] && dist[tmp.goal] > dist[now.goal] + tmp.value) {
                    dist[tmp.goal] = dist[now.goal] + tmp.value;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ward = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
            if (i == N - 1) {
                continue;
            }
            ward[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        System.out.println(djikstra());
    }
}