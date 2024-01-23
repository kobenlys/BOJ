import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[N + 1];
        int[] dp = new int[K + 1];

        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = arr1[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr1[i]] + 1);
            }
        }
        System.out.print(dp[K] == Integer.MAX_VALUE-1 ? -1 : dp[K]);
    }
}
