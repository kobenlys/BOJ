import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node> {
        int s, e, cost, time;

        public node(int s, int e, int cost, int time) {
            this.s = s;
            this.e = e;
            this.cost = cost;
            this.time = time;
        }
        
        // 첫번째 우선순위 가격, 두번째 우선순위 시간.
        @Override
        public int compareTo(node o) {
            if (cost == o.cost) {
                return time - o.time;
            }
            return cost - o.cost;
        }
    }
    
    // 부모 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 같은 집합에 포함하기
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

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int time = 0;
        long money = 0;
        int cnt = 0;

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.offer(new node(s, e, c, t));
        }
        
        // 크루스칼 알고리즘
        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (!union(now.s, now.e)) {
                cnt++;
                money += now.cost;
                time = Math.max(time, now.time);
            }
        }
        // cnt != N - 1 인경우 트리를 완성하지 못함.
        System.out.println(cnt == N - 1 ? time + " " + money : -1);
    }
}