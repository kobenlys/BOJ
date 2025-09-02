import java.io.*;
import java.util.*;

public class Main {

    public static boolean isIn(int number) {
        return 0 <= number && number <= 20;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        long[][] dp = new long[N][21];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][arr1[0]] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                if (isIn(j + arr1[i])) {
                    dp[i][j + arr1[i]] += dp[i - 1][j];
                }

                if (isIn(j - arr1[i])) {
                    dp[i][j - arr1[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N-2][arr1[N-1]]);
    }
}