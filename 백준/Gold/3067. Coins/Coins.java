import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        // 냅색 알고리즘.

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr1 = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1; // 초기화

            for (int i = 0; i < N; i++) {
                for (int j = arr1[i]; j <= M; j++) {
                    dp[j] += dp[j - arr1[i]]; // 현재 동전이 포함가능하다면 +1
                    // 동전 = 7 일때 arr1[7-7] = dp[7] += dp[0]
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}