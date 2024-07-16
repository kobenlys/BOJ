import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr1 = new int[N + 1][2];
        int[][] dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            arr1[i][0] = cost; // 공부시간
            arr1[i][1] = score; // 얻는 점수
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= T; j++) {
                if (arr1[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1],
                            Math.max(dp[i - 1][j], dp[i - 1][j - arr1[i][0]] + arr1[i][1]));

                }
            }
        }
        System.out.println(dp[N][T]);
    }
}