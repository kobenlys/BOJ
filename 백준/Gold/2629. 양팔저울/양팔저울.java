import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;
    public static boolean[][] dp;

    public static void dfs(int cnt, int sum) {

        if (cnt == N + 1) return;
        if (dp[cnt][sum]) return;

        dp[cnt][sum] = true;

        dfs(cnt + 1, sum + arr1[cnt]);
        dfs(cnt + 1, sum);
        dfs(cnt + 1, Math.abs(sum - arr1[cnt]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N + 1];
        dp = new boolean[N + 1][40001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int w = Integer.parseInt(st.nextToken());
            boolean isPossible = false;

            for (int j = 0; j <= N; j++) {
                if (dp[j][w]) {
                    isPossible = true;
                    break;
                }
            }

            sb.append(isPossible ? "Y" : "N").append(" ");
        }
        System.out.println(sb);
    }
}