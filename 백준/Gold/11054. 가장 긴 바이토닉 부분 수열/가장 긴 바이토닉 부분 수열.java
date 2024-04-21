import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N + 2];
        int[] dp1 = new int[N + 2];
        int[] dp2 = new int[N + 2];
        int ans = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        // 1 ~ N 까지 증가하는 수열 수 dp1 입력
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr1[i] > arr1[j] && dp1[i] < dp1[j] + 1) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        // 1 ~ N 까지 감소하는 수열 수 dp2 입력
        for (int i = N; i >= 1; i--) {
            for (int j = N + 1; j >= i; j--) {
                if (arr1[i] > arr1[j] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        // i 기준 dp1 + dp2 합치고 -1 (중복제거) 가장 큰 값 출력
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dp1[i] + dp2[i] - 1);
        }
        System.out.println(ans);
    }
}