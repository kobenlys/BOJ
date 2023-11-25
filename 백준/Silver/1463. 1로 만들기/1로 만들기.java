import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Integer[] dp;

    public static int algorithm(int N) {

        if (dp[N] == null) {

            if (N % 6 == 0) {
                dp[N] = Math.min(algorithm(N - 1), Math.min(algorithm(N / 3), algorithm(N / 2))) + 1;
            }
            else if (N % 3 == 0) {
                dp[N] = Math.min(algorithm(N / 3), algorithm(N - 1)) +1;
            }
            else if (N % 2 ==0) {
                dp[N] = Math.min(algorithm(N / 2), algorithm(N - 1)) + 1;
            }
            else {
                dp[N] = algorithm(N - 1) +1;

            }
        }
        return dp[N];

    }


    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];

        dp[0] = dp[1] = 0;

        System.out.println(algorithm(N));

    }
}
