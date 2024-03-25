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

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

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
            int from = find(nd.s);
            int to = find(nd.e);
            
            if (from != to) {
                
                total += nd.v;
                union(nd.s, nd.e);
            }
        }
        System.out.println(total);
    }
}