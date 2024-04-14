import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, 100_001);

        // 초기 설정.
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j * j <= i; j++) {
                // i - j^2  자리 찾기 +1 해주기.
                //ex N= 10 -> 3^2 + 1^1 => dp[10 - 3^2] = dp[10] -> dp[1]+1;
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}