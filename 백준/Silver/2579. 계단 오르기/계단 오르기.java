import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static int N, max;
    public static int[] arr1;
    public static int[] dp;

    public static void algorithm() { // 구현

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i - 3] + arr1[i - 1], dp[i - 2]) + arr1[i];
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N + 1];
        dp = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr1[1];

        if (N >= 2) {
            dp[2] = arr1[1] + arr1[2];
        }
        if (N >= 3) {
            dp[3] = Math.max(arr1[1], arr1[2]) + arr1[3];
        }

        algorithm();
        System.out.println(dp[N]);
    }
}
