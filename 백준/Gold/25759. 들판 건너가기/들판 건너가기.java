import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[101];
        Arrays.fill(dp, -1);

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        dp[n] = 0; // 가장 첫 시작 입력

        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= 100; j++) {
                if (dp[j] > -1) { // 아직 입력되지 않아 짝을 이룰 수 없는 숫자 제외
                    // dp[num] 의 숫자를 가장 큰 숫자로 유지한다.
                    // dp[j] + (num - j) + (num - j)
                    // 문제조건은 (num - j) ^2 으로 값을 구하고 dp[j](이전 결과값)을 더해준다.
                    dp[num] = Math.max(dp[num], dp[j] + (num - j) * (num - j));
                }
            }
        }
        // dp배열중 최댓값 구하기.
        int answer = Arrays.stream(dp).max().getAsInt();
        System.out.println(answer);
    }
}