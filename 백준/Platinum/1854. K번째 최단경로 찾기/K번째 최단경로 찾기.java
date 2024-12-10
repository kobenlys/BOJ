import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static List<List<Node>> list = new ArrayList<>();

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

    public static PriorityQueue<Integer>[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        PriorityQueue<Integer>[] dist = new PriorityQueue[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        pq.offer(new Node(1, 0));
        dist[1].add(0);

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node tmp : list.get(now.goal)) {
                int len = now.val + tmp.val;

                if (dist[tmp.goal].size() < K) {
                    dist[tmp.goal].add(len);
                    pq.offer(new Node(tmp.goal, len));
                } else {
                    if (dist[tmp.goal].peek() > len) {
                        dist[tmp.goal].poll();
                        dist[tmp.goal].add(len);
                        pq.offer(new Node(tmp.goal, len));
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
        }

        PriorityQueue<Integer>[] res = dijkstra();

        for (int i = 1; i <= N; i++) {
            if (res[i].size() < K) {
                sb.append(-1);
            } else {
                sb.append(res[i].peek());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}