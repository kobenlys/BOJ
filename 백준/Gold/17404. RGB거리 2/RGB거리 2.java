import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[3][N + 1];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr1[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            int[][] dp = new int[3][N + 1];

            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[j][1] = arr1[j][1];
                } else {
                    dp[j][1] = 1_000_000;
                }
            }

            for (int j = 2; j <= N; j++) {
                dp[0][j] = Math.min(dp[2][j - 1], dp[1][j - 1]) + arr1[0][j];
                dp[1][j] = Math.min(dp[0][j - 1], dp[2][j - 1]) + arr1[1][j];
                dp[2][j] = Math.min(dp[1][j - 1], dp[0][j - 1]) + arr1[2][j];
            }


            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                answer = Math.min(answer, dp[j][N]);
            }
        }

        System.out.println(answer);
    }
}