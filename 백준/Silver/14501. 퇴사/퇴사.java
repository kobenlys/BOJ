import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, H, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[] p = new int[N + 2];
        int[] t = new int[N + 2];
        int[] dp = new int[N + 2];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {

            int now = i + t[i];

            if (now > N + 1) {
                dp[i] = dp[i + 1];
            }else{
                // dp[i+1]를 사용한다 or (now가 N+1이하 라면)누적값+현재값 을 사용한다
                dp[i] = Math.max(dp[i + 1], dp[now] + p[i]);
            }
        }

        System.out.println(dp[1]);
    }
}
