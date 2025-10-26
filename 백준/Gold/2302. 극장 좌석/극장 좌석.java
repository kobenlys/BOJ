import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int answer = 1;
        int[] dp = new int[41];
        dp[0] = dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int before = 0;
        while (M-- > 0) {

            int after = Integer.parseInt(br.readLine());
            answer *= dp[after - before - 1];
            before = after;
        }
        answer *= dp[N - before];
        System.out.println(answer);
    }
}