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
        @Override
        public int compareTo(node o) {
            return v - o.v;
        }
    }
    // 부모노드를 찾는 함수
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    // 자식노드에 부모노드 표시하기.
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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        PriorityQueue<node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;
        int ans = 0;

        // 초기 설정하기.
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken());
            pq.offer(new node(s, e, v));
        }

        for (int i = 0; i < M; i++) {

            node nd = pq.poll();
            int from = find(nd.s);
            int to = find(nd.e);
            // 사이클이 없는 트리를 만들어야 한다.
            // 시작노드와 도착노드가 가지는 부모노드가 같다면 -> 사이클 발생 ->제외한다.
            if (!union(nd.s, nd.e)) {
                ans += nd.v;
                // 마을을 나누는 간선 정하기.
                max = Math.max(max, nd.v);
                union(from, to);
            }
        }
        // 모든 마을의 최소가중치로 다 이었을때 그 중에서 가장 큰 가중치를 삭제
        // -> 문제 조건처럼 마을을 2개로 나눌 수 있다.
        System.out.println(ans - max);
    }
}