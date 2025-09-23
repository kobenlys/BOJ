import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb;
    public static int[] cache;
    public static boolean[] vi;
    public static int N, M;

    public static void dfs(int idx) {

        if (idx == M) {
            for (int i = 0; i < M; i++) {
                sb.append(cache[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            cache[idx] = i;
            dfs(idx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cache = new int[M];
        vi = new boolean[N + 1];
        dfs(0);
        System.out.println(sb);
    }
}