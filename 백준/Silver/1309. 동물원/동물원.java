import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        // n = 0 = 1

        // n = 1
        // ㅁ ㅁ = 3

        // n = 2
        // ㅁ ㅁ
        // ㅁ ㅁ =  7

        // n = 3
        // ㅁ ㅁ
        // ㅁ ㅁ
        // ㅁ ㅁ  =  17


        // n = 4
        // ㅁ ㅁ
        // ㅁ ㅁ
        // ㅁ ㅁ
        // ㅁ ㅁ = 41
        // 점화식 = fn = f(n-1)*2 + f(n-2)

        dp[0] = 1;
        if (N >= 1) {
            dp[1] = 3;
        }

        for (int i = 2; i <= N; i++) {
            //점화식 
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
        }

        System.out.println(dp[N] % 9901);

    }
}


