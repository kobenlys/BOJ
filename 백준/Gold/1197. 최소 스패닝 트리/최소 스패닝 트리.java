import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        // 최소 가중치를 가지는 간선 우선으로 정렬
        @Override
        public int compareTo(node o) {
            return v - o.v;
        }
    }
    // 경로압축을 통한. 부모노드 찾기.
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    // 자식노드에 대한 부모노드 표시.
    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int ans = 0;

        parent = new int[V];
        // 부모노드 초기값 설정
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
        // 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken());
            pq.offer(new node(s, e, v));
        }
        // 크루스칼 알고리즘
        for (int i = 0; i < E; i++) {

            node nd = pq.poll();
            int from = find(nd.s);
            int to = find(nd.e);
            // 부모노드가 같다면 -> 사이클 발생했다는 의미. 현재 간선은 제외하고 다음 간선 탐색
            if (from != to) {
                // 부모노드가 다르다면 -> 가중치 합 구하기 + 부모노드 자식노드 표시
                ans += nd.v;
                union(from, to);
            }
        }
        System.out.println(ans);
    }
}