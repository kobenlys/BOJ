import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node> {
        int s, e, val;

        public node(int s, int e, int val) {
            this.s = s;
            this.e = e;
            this.val = val;
        }
        @Override
        public int compareTo(node o) {
            return val - o.val;
        }
    }
    
    // 부모노드 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 집합에 추가하기
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
        HashMap<Integer, Boolean> map = new HashMap<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;
        int cnt = 0;
        parent = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            String s = st.nextToken();
            map.put(i, s.equals("M")); // 남자 true 여자 false
        }

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            pq.offer(new node(s, e, v));
        }

        // 크루스칼 알고리즘
        while (!pq.isEmpty()) {

            node now = pq.poll();
            // 성별이 다른지 체크
            if (map.get(now.s) != map.get(now.e)) {
                // 성별이 다르고, 도로가 이어져있지 않다면 잇기
                if (!union(now.s, now.e)) {
                    cnt++; // 트리 간선 카운트.
                    ans += now.val; // 밸류 구하기
                }
            }
        }
        // 트리의 조건 : 간선을 N-1개 가진다.
        System.out.println(cnt == N - 1 ? ans : -1);
    }
}