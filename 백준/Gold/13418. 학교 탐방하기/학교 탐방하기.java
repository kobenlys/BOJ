import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static class Node{

        int s, e, v;

        public Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
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

    public static void initParent(int size) {
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int res1 = 0;
        int res2 = 0;


        Comparator<Node> asc = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.v - o2.v;
            }
        };

        Comparator<Node> desc = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.v - o1.v;
            }
        };

        parent = new int[N + 1];
        PriorityQueue<Node> pq1 = new PriorityQueue<>(asc);
        PriorityQueue<Node> pq2 = new PriorityQueue<>(desc);
        initParent(N);

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq1.offer(new Node(s, e, v));
        }
        
        while (!pq1.isEmpty()) {
            Node nd = pq1.poll();
            if (union(nd.s, nd.e)) {
                if (nd.v == 0) {
                    res1++;
                }
            }
            pq2.offer(nd);
        }

        initParent(N);

        while (!pq2.isEmpty()) {
            Node nd = pq2.poll();
            if (union(nd.s, nd.e)) {
                if (nd.v == 0) {
                    res2++;
                }
            }
        }

        System.out.println((int) (Math.pow(res1, 2) - Math.pow(res2, 2)));
    }
}