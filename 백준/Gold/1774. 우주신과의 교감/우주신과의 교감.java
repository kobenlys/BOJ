import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node> {
        int s, e;
        double val;

        public node(int s, int e, double val) {
            this.s = s;
            this.e = e;
            this.val = val;
        }

        @Override
        public int compareTo(node o) {
            return Double.compare(val, o.val);
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
        PriorityQueue<node> pq = new PriorityQueue<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        double ans = 0;
        int[][] arr1 = new int[N][2];
        boolean[][] vi = new boolean[N][N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            parent[i] = i;
            arr1[i][0] = x;
            arr1[i][1] = y;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sIdx = Integer.parseInt(st.nextToken()) - 1;
            int eIdx = Integer.parseInt(st.nextToken()) - 1;
            vi[sIdx][eIdx] = true;
            union(sIdx, eIdx);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if(vi[i][j]) continue;

                double v = Math.sqrt(Math.pow(arr1[i][0] - arr1[j][0], 2)
                        + Math.pow(arr1[i][1] - arr1[j][1], 2));
                pq.offer(new node(i, j, v));
            }
        }

        while (!pq.isEmpty()) {
            node nd = pq.poll();

            if (!union(nd.s, nd.e)) {
                ans += nd.val;
            }
        }

        System.out.printf("%.2f%n", ans);
    }
}