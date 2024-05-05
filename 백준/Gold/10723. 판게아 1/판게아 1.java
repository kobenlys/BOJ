import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    public static ArrayList<node> arr1;

    public static class node implements Comparable<node> {
        int s, e;
        long v;

        public node(int s, int e, long v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(node o) {
            return (int) (v - o.v);
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
            return false;
        }
        return true;
    }

    public static long KrusKal() {
        Collections.sort(arr1, Comparator.comparingLong((node o) -> o.v));
        long res = 0;

        for (int i = 0; i < arr1.size(); i++) {
            node nd = arr1.get(i);
            if (!union(nd.s, nd.e)) {
                res += nd.v;
            }
        }
       
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long res = 0;
            long ans = 0;

            arr1 = new ArrayList<>();
            parent = new int[N];

            for (int i = 1; i <= N - 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int e = Integer.parseInt(st.nextToken());
                long v = Integer.parseInt(st.nextToken());

                arr1.add(new node(i, e, v));
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                long v = Integer.parseInt(st.nextToken());
                arr1.add(new node(s, e, v));
                for (int k = 0; k < N; k++) {
                    parent[k] = k;
                }
                ans ^= KrusKal();
            }

            System.out.println(ans);
        }
    }
}