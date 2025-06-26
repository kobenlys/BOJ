import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;
    public static int[] map = new int[1_000_001];

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {

        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
            map[find(x)] = map[from] + map[to];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        parent = new int[1_000_001];

        for (int i = 1; i <= 1_000_000; i++) {
            parent[i] = i;
            map[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String dir = st.nextToken();
            int A = Integer.parseInt(st.nextToken());

            if (dir.equals("I")) {
                int B = Integer.parseInt(st.nextToken());
                union(A, B);
            } else {
                sb.append(map[find(A)]).append("\n");
            }
        }
        System.out.println(sb);
    }
}