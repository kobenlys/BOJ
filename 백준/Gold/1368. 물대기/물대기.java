import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node>{
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

    // 유니온 파인드
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
        PriorityQueue<node> pq = new PriorityQueue<node>();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        parent = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 0 -> i 까지 우물을 직접 팠을때.
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            pq.offer(new node(0, i, n));
        }
        
        // i -> j 간선 연결시 n 가중치 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                pq.offer(new node(i, j, n));
            }
        }
        
        // 크루스칼 알고리즘
        while (!pq.isEmpty()) {
            node nd = pq.poll();
            if (!union(nd.s, nd.e)) {
                answer += nd.v;
            }
        }
        System.out.println(answer);
    }
}