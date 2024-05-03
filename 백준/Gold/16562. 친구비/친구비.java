import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    public static boolean[] vi;

    public static class node {
        int s, e;
        public node(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    
    // 집합에 포함하기
    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
        }
    }
    
    // 부모 노드 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;

        node[] cost = new node[N];
        vi = new boolean[N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());
            cost[i] = new node(i, val);
        }
        // 가장 작은 비용이 드는 친구 우선순위 정렬
        Arrays.sort(cost, Comparator.comparingInt(o -> o.e));


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            if (s == e) continue;
            // 집합 만들기
            union(s, e);
        }

        for (int i = 0; i < N; i++) {

            node nd = cost[i];
            int root = find(nd.s); // 부모 찾기
            if (!vi[root]) {
                // 방문하지 않은 집합이면 방문처리 및 비용집계
                vi[root] = true;
                answer += nd.e;
            }
        }

        System.out.print(K >= answer ? answer : "Oh no");
    }
}