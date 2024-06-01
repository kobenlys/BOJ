import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static int[] dp;

    public static int dfs(int pos, int idx) {
        if (idx >= N) return 0;
        if (dp[pos] != -1) return dp[pos];

        dp[pos] = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if ((pos & (1 << i)) != 0) {
                dp[pos] = Math.min(dp[pos], dfs(pos ^ (1 << i), idx + 1) + arr1[idx][i]);

            }
        }
        return dp[pos];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int pos = 0;
        arr1 = new int[N][N];
        dp = new int[1 << N];

        Arrays.fill(dp, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
            pos = pos | (1 << i);
        }

        int answer = dfs(pos, 0);
        System.out.println(answer);
    }
}