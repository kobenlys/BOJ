import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr1;
    public static long[][] dp;

    public static boolean isIn(int number) {
        return 0 <= number && number <= 20;
    }

    public static long dfs(int step, int res) {

        if (dp[step][res] != -1) {
            return dp[step][res];
        }

        if (step == arr1.length - 1) {
            if (res == arr1[arr1.length - 1]) {
                return dp[step][res] = 1;
            }
            return 0;
        }

        dp[step][res] = 0;

        if (isIn(res + arr1[step])) {
            dp[step][res] += dfs(step + 1, res + arr1[step]);
        }

        if (step > 0 && isIn(res - arr1[step])) {
            dp[step][res] += dfs(step + 1, res - arr1[step]);
        }

        return dp[step][res];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        dp = new long[N][21];

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < 21; k++) {
                dp[j][k] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
}