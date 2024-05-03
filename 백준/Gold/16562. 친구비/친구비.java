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

    public static void union(int x, int y) {

        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
        }
    }

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

        Arrays.sort(cost, Comparator.comparingInt(o -> o.e));


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            if (s == e) continue;

            if (s > e) {
                int tmp = s;
                s = e;
                e = tmp;
            }
            union(s, e);
        }

        for (int i = 0; i < N; i++) {

            node nd = cost[i];
            int root = find(nd.s);
            if (!vi[root]) {
                vi[root] = true;
                answer += nd.e;
            }
        }

        System.out.println(K >= answer ? answer : "Oh no");
    }
}