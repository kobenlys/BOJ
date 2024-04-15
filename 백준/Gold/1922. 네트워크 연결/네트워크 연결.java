import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node> {
        int x, y, v;

        public node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
        @Override
        public int compareTo(node o) {
            return v - o.v;
        }
    }

    // 부모노드 찾기, 좌표압축으로 최적화.
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 집합에 포함하기(같은 부모노드 가지기)
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
        PriorityQueue<node> pq = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int ans = 0;
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        // 간선 노드 비용 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            if (s == e) continue;
            pq.offer(new node(s, e, v));
        }

        // 크루스칼 알고리즘.
        while (!pq.isEmpty()) {
            int x = pq.peek().x;
            int y = pq.peek().y;
            int v = pq.poll().v;
            if (union(x, y)) {
                // 간선을 이었다면 ans에 더해주기.
                ans += v;
            }
        }

        System.out.print(ans);
    }
}