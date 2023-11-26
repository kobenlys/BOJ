import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        dp[1] = 1;
        if (n >= 2) {
            dp[2] = 2;
        }


        // n=1 (1), n=2 (00, 11), n=3 (001, 100, 111), n=4 (0011, 1100, 1001, 1111, 0000)
        // 경우의 수 1, 2, 3, 5
        // 1+2 = 3, 2+3 =5
        // 점화식 : dp[i] = dp[i - 1] + dp[i - 2]
        
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[n]%15746);
    }
}
