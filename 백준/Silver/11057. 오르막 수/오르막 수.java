import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 요소는 1 ~ 9 까지 생성
        int[][] dp = new int[n+1][11];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
            for (int j = 2; j <= 10; j++) {
                //dp에 N의 길이 만큼 오르막 수 경우의 수 저장
                // 00 -> 01, 11 -> 02 12 22
                //  1 ->   3 ->  6
                // 오르막 수 경우의 수  저장 점화식
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                dp[i][j] %= 10007; // 출력 조건 1. 10007로 나눠야 한다.
            }
        }

        int sum =0;
        // 길이 N에 대한 모든 오르막 수 경우의 수 합 
        for (int i = 1; i <= 10; i++) {
            sum += dp[n][i];
        }

        System.out.println(sum % 10007); // 출력

    }
}
