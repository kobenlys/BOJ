import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;
    public static int N;

    public static class node {
        int i, x, y, z;

        public node(int i, int x, int y, int z) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class planet implements Comparable<planet> {
        int s, e, v;

        public planet(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(planet o) {
            return v - o.v;
        }
    }

    // 부모노드 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 자식노드에 부모노드 표시.
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
        PriorityQueue<planet> pq = new PriorityQueue<>();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        node[] arr1 = new node[N];

        // parent 배열 초기셋팅 및 입력
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            arr1[i] = new node(i, x, y, z);
        }

        // x좌표 기준으로 정렬
        Arrays.sort(arr1, (p1, p2) -> Integer.compare(p1.x, p2.x));
        for (int i = 0; i < N - 1; i++) {
            int v = Math.abs(arr1[i].x - arr1[i + 1].x);
            pq.offer(new planet(arr1[i].i, arr1[i + 1].i, v));
        }
        // y좌표 기준으로 정렬
        Arrays.sort(arr1, (p1, p2) -> Integer.compare(p1.y, p2.y));
        for (int i = 0; i < N - 1; i++) {
            int v = Math.abs(arr1[i].y - arr1[i + 1].y);
            pq.offer(new planet(arr1[i].i, arr1[i + 1].i, v));
        }
        // z좌표 기준으로 정렬
        Arrays.sort(arr1, (p1, p2) -> Integer.compare(p1.z, p2.z));
        for (int i = 0; i < N - 1; i++) {
            int v = Math.abs(arr1[i].z - arr1[i + 1].z);
            pq.offer(new planet(arr1[i].i, arr1[i + 1].i, v));
        }

        // 크루스칼 알고리즘
        long ans = 0;

        while (!pq.isEmpty()) {
            planet nd = pq.poll();
            
            if(find(nd.s) == find(nd.e)) continue;
            
            if (!union(nd.s, nd.e)) {
                ans += nd.v;
            }
        }

        System.out.println(ans);
    }
}