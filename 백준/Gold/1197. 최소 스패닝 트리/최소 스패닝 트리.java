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

        @Override // 최소값부터 계산하여 사이클 생긴다면 제외 할거임.
        public int compareTo(node o) {
            return v - o.v;
        }
    }
    // 부모 확인
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    // 같은 집합 (부모 연결) 해주기 -> parent 배열에 부모를 밸류로 설정
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
    // 부모가 같은지 판단. -> 부모가 같다면 사이클 발생 -> 트리 아님.
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 초기 값 설정.
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken());
            pq.add(new node(s, e, v));
        }

        int total = 0;

        for (int i = 0; i < E; i++) {

            node nd = pq.poll();
            int from = find(nd.s); // 부모노드가 있다면 부모노드 가져오기
            int to = find(nd.e);
            // 트리 조건(사이클 존재 x)에 맞는지 확인
            // 부모가 같다면 -> 사이클 존재 한다는 뜻.
            if (!isSameParent(from, to)) {
                total += nd.v;
                union(nd.s, nd.e);
            }
        }
        System.out.println(total);
    }
}