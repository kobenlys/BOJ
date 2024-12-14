import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[31];

        dp[1] = 0;
        dp[2] = 3;

        for (int i = 4; i <= N; i += 2) {
            dp[i] += dp[i - 2] * 3; // 2*3칸에는 3가지 경우가 존재
            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2; // 새로운 패턴이 2개와 기존패턴 곱하기
            }
            dp[i] += 2; // 2칸이 추가될때마다 새로운 패턴 2개 있음.
        }
        System.out.println(dp[N]);
    }
}