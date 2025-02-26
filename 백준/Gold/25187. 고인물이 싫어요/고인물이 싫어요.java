import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    public static int[] waterStatus;

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from == to) return;
        parent[from] = to;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        waterStatus = new int[N + 1];
        parent = new int[N + 1];
        int[] scoreBoard = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            waterStatus[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e);
        }

        for (int i = 1; i <= N; i++) {
            int setNumber = find(i);
            if (waterStatus[i] == 1) {
                scoreBoard[setNumber]++;
            } else {
                scoreBoard[setNumber]--;
            }
        }

        for (int i = 0; i < Q; i++) {
            int target = Integer.parseInt(br.readLine());
            if (scoreBoard[find(target)] > 0) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }
}