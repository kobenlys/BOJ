import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;

        for (int i = 1; i <= str.length(); i++) {

            int now = str.charAt(i - 1) - '0';

            if (now >= 1 && now <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= 1000000;
            }

            if(i == 1) continue;

            int before = str.charAt(i - 2) - '0';
            int prev = before * 10 + now;

            if (prev >= 10 && prev <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= 1000000;
            }
        }
        System.out.println(dp[str.length()]);
    }
}
