import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class Node implements Comparable<Node> {
        int s, e, y, idx;

        public Node(int s, int e, int y, int idx) {
            this.s = s;
            this.e = e;
            this.y = y;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return s - o.s;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, e, y, i));
        }


        int nowIdx = pq.peek().idx;
        int tmpEnd = Objects.requireNonNull(pq.poll()).e;
        while (!pq.isEmpty()) {

            Node pos = pq.poll();

            if (pos.s <= tmpEnd) {
                tmpEnd = Math.max(tmpEnd, pos.e);
                union(nowIdx, pos.idx);
            } else {
                nowIdx = pos.idx;
                tmpEnd = pos.e;
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (find(s) == find(e)) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb);
    }
}