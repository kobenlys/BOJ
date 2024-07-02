import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static Integer[][] dp;
    public static int cnt0, cnt1;

    public static Integer[] fibo(int n) {

        if (dp[n][0] == null || dp[n][1] == null) {

            dp[n][0] = fibo(n - 1)[0] + fibo(n - 2)[0];
            dp[n][1] = fibo(n - 1)[1] + fibo(n - 2)[1];

        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        dp = new Integer[41][2];

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;


        for (int tcase = 0; tcase < T; tcase++) {
            int n = Integer.parseInt(br.readLine());

            fibo(n);

            System.out.println(dp[n][0] + " " + dp[n][1]);
        }

    }

}
