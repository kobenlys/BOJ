import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int mod = 1_000_000_000;
        int[][] dp = new int[N + 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }


        if (N > 1) {
            for (int i = 2; i <= N; i++) {
                dp[i][0] = dp[i - 1][1];
                dp[i][9] = dp[i - 1][8];
                for (int j = 1; j < 9; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                    dp[i][j] %= mod;
                }
            }

            int sum = 0;
            for (int i = 0; i <= 9; i++) {
                sum += dp[N][i];
                sum %= mod;
            }
            System.out.println(sum);
        } else {
            System.out.println(9);
        }
    }
}