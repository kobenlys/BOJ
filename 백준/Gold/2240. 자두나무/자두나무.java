import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][] dp = new int[T + 1][W + 1];

        for (int i = 1; i <= T; i++) {
            int treeNumber = Integer.parseInt(br.readLine());
            for (int j = 0; j <= W; j++) {

                if (j == 0) {
                    if (treeNumber == 1) {
                        dp[i][j] = dp[i - 1][j] + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    continue;
                }

                if (j % 2 == 0) {
                    if (treeNumber == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + 1);
                    }
                }else{
                    if (treeNumber == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    }else{
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
                    }
                }
            }
        }

        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, dp[T][i]);
        }

        System.out.println(answer);
    }
}