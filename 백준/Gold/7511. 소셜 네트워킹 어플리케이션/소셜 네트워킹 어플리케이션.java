import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

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
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());
            parent = new int[N + 1];
            sb.append("Scenario ").append(i).append(":").append("\n");

            for (int j = 0; j <= N; j++) {
                parent[j] = j;
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                union(s, e);
            }

            int M = Integer.parseInt(br.readLine());

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int from = find(s);
                int to = find(e);

                if (from == to) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}