import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[N+1][2];
        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr1[i][1] > arr1[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(N - Arrays.stream(dp).max().orElse(0));
    }
}