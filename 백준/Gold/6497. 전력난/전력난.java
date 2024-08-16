import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] parent;

    public static class node implements Comparable<node> {
        int s, e, v;

        public node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(node o) {
            return v - o.v;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            String exit = br.readLine();
            if (exit.equals("0 0")) break;

            st = new StringTokenizer(exit);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int answer = 0;
            int maxPrice = 0;
            PriorityQueue<node> pq = new PriorityQueue<>();

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                maxPrice += v;
                pq.offer(new node(s, e, v));
            }

            while (!pq.isEmpty()) {
                node now = pq.poll();

                if (union(now.s, now.e)) {
                    answer += now.v;
                }
            }
            sb.append(answer).append("\n");


        }
        System.out.println(sb);
    }
}