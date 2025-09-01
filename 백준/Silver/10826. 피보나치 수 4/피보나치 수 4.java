import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static BigInteger[] dp;

    public static BigInteger fibo(int n) {

        if (dp[n].compareTo(new BigInteger("-1")) > 0) {
            return dp[n];
        }

        if (n == 0) {
            return dp[n] = new BigInteger("0");
        } else if (n == 1) {
            return dp[n] = new BigInteger("1");
        }

        return dp[n] = fibo(n - 1).add(fibo(n - 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp = new BigInteger[N + 1];

        Arrays.fill(dp, new BigInteger("-1"));
        System.out.println(fibo(N));
    }
}