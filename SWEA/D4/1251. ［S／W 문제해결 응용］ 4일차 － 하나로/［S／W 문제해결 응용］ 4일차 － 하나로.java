import java.io.*;
import java.util.*;

public class Solution {
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

    // 부모노드 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 집합 합치기
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
        StringTokenizer st1;
        StringTokenizer st2;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            PriorityQueue<node> pq = new PriorityQueue<node>();
            int N = Integer.parseInt(br.readLine());
            st1 = new StringTokenizer(br.readLine());
            st2 = new StringTokenizer(br.readLine());
            double tax = Double.parseDouble(br.readLine());
            double answer = 0;
            int[][] arr1 = new int[N][2];
            parent = new int[N];

            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st1.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                parent[i] = i;
                arr1[i][0] = x;
                arr1[i][1] = y;
            }

            // 각 노드별 가능한 경로 만들기 + 유클리드거리 측정
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double len = Math.sqrt(Math.pow(arr1[i][0] - arr1[j][0], 2)
                            + Math.pow(arr1[i][1] - arr1[j][1], 2));
                    pq.offer(new node(i, j, len));
                }
            }

            // 크루스칼 알고리즘
            while (!pq.isEmpty()) {
                node nd = pq.poll();

                if (!union(nd.s, nd.e)) {
                    answer += tax * (nd.val * nd.val);
                }
            }

            String ans = String.format("%.0f", answer);
            sb.append("#").append(tc).append(" ");
            sb.append(ans);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}