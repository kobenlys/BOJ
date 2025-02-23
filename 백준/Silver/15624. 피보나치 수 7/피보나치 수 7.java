import java.io.*;
import java.util.*;

public class Main {
    public static int[] dp = new int[1_000_001];

    public static int dfs(int N) {
        if (dp[N] > 0) return dp[N];

        if (N == 1) {
            return 1;
        } else if (N == 0) {
            return 0;
        }
        dp[N] = (dfs(N - 1) + dfs(N - 2)) % 1_000_000_007;
        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp[1] = 1;
        dfs(N);
        System.out.println(dp[N]);
    }
}