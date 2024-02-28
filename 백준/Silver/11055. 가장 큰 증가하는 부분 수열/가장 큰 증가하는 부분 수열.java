import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int[] arr1 = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N ; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= N ; i++) {
            dp[i] = arr1[i]; // 기준점 초기화
            for (int j = 0; j < i; j++) {
                // 핵심. 현재기준 작은 숫자만 값을 더할 수 있다.
                if (arr1[i] > arr1[j]) {
                    //핵심2. j가 기준과 가까울 수록 dp[j] + arr1[i]값은 커져야 증가되는 수 이다.
                    // 그렇기 때문에 그런 arr1[i] 보다 작은 수열 중 가장 큰 dp[j] + arr1[i]값을 구해야 한다.
                    // dp 배열로 값을 메모이제이션 하기 때문에 가능하다.
                    dp[i] = Math.max(dp[j] + arr1[i], dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.print(max);
    }
}