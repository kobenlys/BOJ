import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static class Node implements Comparable<Node> {

        int x, y, z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Node o) {
            return this.z - o.z;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int answer = 0;
        int unionCnt = 0;

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());

                if (i >= j) {
                    continue;
                }

                if (cost < 0) {
                    cost *= -1;
                    answer += cost;
                    union(i, j);

                } else {
                    pq.offer(new Node(i, j, cost));
                    pq.offer(new Node(j, i, cost));
                }
            }
        }

        while (!pq.isEmpty()) {
            Node nd = pq.poll();
            if (union(nd.x, nd.y)) {
                answer += nd.z;
                unionCnt++;
                sb.append(nd.x + 1).append(" ").append(nd.y + 1).append("\n");
            }
        }

        System.out.println(answer + " " + unionCnt);
        System.out.println(sb);
    }
}