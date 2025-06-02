import java.io.*;
import java.util.*;

public class Main {

    public static int P, W, C, V;
    public static int[] parent;
    public static List<List<Node>> list;
    public static boolean[] vi;

    public static class Node {

        int goal, val;

        public Node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }
    }

    public static class Road implements Comparable<Road> {

        int s, e, w;

        public Road(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Road o) {
            return o.w - w;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {

        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
            return true;
        }
        return false;
    }

    public static void dfs(int start, List<Integer> res) {
        if (start == V) {
            Collections.sort(res);
            System.out.println(res.get(0));
            System.exit(0);
        }

        for (Node nd : list.get(start)) {
            if (!vi[nd.goal]) {
                res.add(nd.val);
                vi[nd.goal] = true;
                dfs(nd.goal, res);
                vi[nd.goal] = false;
                res.remove(res.size() - 1);

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        PriorityQueue<Road> pq = new PriorityQueue<>();
        parent = new int[P];
        list = new ArrayList<>();
        vi = new boolean[P];

        for (int i = 0; i < P; i++) {
            list.add(new ArrayList<>());
            parent[i] = i;
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Road(s, e, w));
        }

        while (!pq.isEmpty()) {
            Road road = pq.poll();

            if (union(road.s, road.e)) {
                list.get(road.s).add(new Node(road.e, road.w));
                list.get(road.e).add(new Node(road.s, road.w));
            }
        }
        vi[C] = true;
        dfs(C, new ArrayList<>());
    }
}