import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] arr1 = new int[N + 1];
        int[] dp = new int[N + 1];
        dp[N] = 1;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        // 감소하는 부분 수열 구하기 -> 30 - 20 - 10
        // 가장 뒤부터 탐색하면 결국 증가하는 부분수열 처럼 구하면 됨
        for (int i = N; i > 0 ; i--) {
            for (int j = N; j > i ; j--) {
                // 현재 자리 arr[i] 보다고 + dp참조하여 구해진 값이 있다면 사용 ++
                if (arr1[i] > arr1[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j]+1 ;
                }
            }
            // dp = 0 이라면 1을 입력
            if (dp[i] == 0) {
                dp[i] = 1;
            }
        }
        // 최대값 출력
        Arrays.sort(dp);
        System.out.println(dp[N]);
    }
}


