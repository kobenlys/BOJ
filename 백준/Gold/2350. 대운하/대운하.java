import java.io.*;
import java.util.*;

public class Main {
    public static List<List<Node>> list = new ArrayList<>();
    public static int[] parent;
    public static boolean[] vi;
    public static int answer = 0;

    public static class Node implements Comparable<Node> {
        int goal, width;

        public Node(int goal, int width) {
            this.goal = goal;
            this.width = width;
        }

        @Override
        public int compareTo(Node o) {
            return width - o.width;
        }
    }

    public static class MstNode implements Comparable<MstNode> {
        int s, e, w;

        public MstNode(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(MstNode o) {
            return o.w - w;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
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

    public static void dfs(int now, int minWidth, int target) {

        if (now == target) {
            answer = Math.max(answer, minWidth);
            return;
        }

        for (Node next : list.get(now)) {
            if (!vi[next.goal]) {
                vi[next.goal] = true;
                dfs(next.goal, Math.min(minWidth, next.width), target);
                vi[next.goal] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        PriorityQueue<MstNode> pq = new PriorityQueue<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new MstNode(s, e, w));
        }

        while (!pq.isEmpty()) {
            MstNode mn = pq.poll();
            if (union(mn.s, mn.e)) {
                list.get(mn.s).add(new Node(mn.e, mn.w));
                list.get(mn.e).add(new Node(mn.s, mn.w));
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            answer = 0;
            vi = new boolean[N + 1];
            vi[s] = true;
            dfs(s, 201, e);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}