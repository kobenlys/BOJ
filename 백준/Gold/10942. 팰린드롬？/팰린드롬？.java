import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;
    public static boolean[][] dp;

    public static void isPalindrome() {

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
            if (i != N - 1 && arr1[i] == arr1[i+1]) {
                dp[i][i + 1] = true;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                if (arr1[j] == arr1[j + i] && dp[j + 1][i + j - 1]) {
                    dp[j][j+i] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        dp = new boolean[N][N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        isPalindrome();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dp[start][end] ? 1 : 0).append("\n");

        }
        System.out.print(sb);
    }
}