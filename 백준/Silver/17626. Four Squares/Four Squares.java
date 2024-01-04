import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[1] = 1;

        for (int i = 2; i <= N ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j*j <= i ; j++) {
                int idx = i - j * j;
                min = Math.min(min, dp[idx]);
            }
            dp[i] = ++min;
        }
        System.out.println(dp[N]);
    }
}
