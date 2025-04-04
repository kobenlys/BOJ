import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    public static int[][] vi;

    public static class Pos {
        int x1, y1, x2, y2;

        public Pos(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[from] = to;
        }
    }

    public static void makeRectengle(int x1, int y1, int x2, int y2, int idx) {
        for (int i = x1; i <= x2; i++) {
            if (vi[y1][i] > 0) {
                union(idx, vi[y1][i]);
            }

            if (vi[y2][i] > 0) {
                union(idx, vi[y2][i]);
            }

            vi[y1][i] = idx;
            vi[y2][i] = idx;
        }

        for (int i = y1; i <= y2; i++) {
            if (vi[i][x1] > 0) {
                union(idx, vi[i][x1]);
            }

            if (vi[i][x2] > 0) {
                union(idx, vi[i][x2]);
            }

            vi[i][x1] = idx;
            vi[i][x2] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Pos[] arr1 = new Pos[N + 1];
        vi = new int[1002][1002];
        arr1[0] = new Pos(0, 0, 0, 0);
        Set<Integer> set = new HashSet<>();

        boolean isOk = false;

        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        arr1[0] = new Pos(0, 0, 0, 0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 500;
            int y1 = Integer.parseInt(st.nextToken()) + 500;
            int x2 = Integer.parseInt(st.nextToken()) + 500;
            int y2 = Integer.parseInt(st.nextToken()) + 500;

            arr1[i] = new Pos(x1, y1, x2, y2);
            makeRectengle(x1, y1, x2, y2, i);
        }


        for (int i = 1; i <= N; i++) {
            set.add(find(parent[i]));
        }

        if (vi[500][500] > 0) {
            isOk = true;
        }


        if (isOk) {
            System.out.println(set.size() - 1);
        } else {
            System.out.println(set.size());
        }
    }
}