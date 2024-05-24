import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int answer = Integer.MIN_VALUE; // 답이 음수값이 될 수도 있기 때문.
        int[] arr1 = new int[N + 1];
        int[][] dp = new int[N + 1][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 초기화, 무조건 하나를 선택해야하기 때문. + 누적합.
        dp[1][0] = arr1[1];
        dp[1][1] = arr1[1];

        for (int i = 2; i <= N; i++) {
            // 시작위치에 따른 기본 누적합
            dp[i][0] = Math.max(dp[i - 1][0] + arr1[i], arr1[i]); 
            // 이전 하나를 제외한 누적합 + 현재값 or 기본 누적합에서 ,현재값 제외(전값)
            dp[i][1] = Math.max(dp[i - 1][1] + arr1[i], dp[i - 1][0]);
        }

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }
        
        System.out.println(answer);
    }
}