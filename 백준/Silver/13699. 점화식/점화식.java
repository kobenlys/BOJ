import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];

        if (N == 0) {
            System.out.println(1);
            return;
        }
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        System.out.println(dp[N]);
    }
}