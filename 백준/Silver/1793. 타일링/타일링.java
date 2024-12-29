import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        BigInteger[] dp = new BigInteger[251];

        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        for (int i = 3; i <= 250; i++) {
            BigInteger tmp1 = dp[i - 2].multiply(new BigInteger(String.valueOf(2)));
            tmp1 = tmp1.add(dp[i - 1]);
            dp[i] = tmp1;
        }

        while (true) {
            String str = br.readLine();
            if(str == null) break;
            int N = Integer.parseInt(str);

            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}