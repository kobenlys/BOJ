import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];
        Arrays.fill(dp, Integer.MAX_VALUE); // 최소값을 구하기위해 Max 밸류로 초기화
        dp[0] = 0; // 사람이 0 명이라면 돈도 들지 않음

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            for (int j = val; j < C + 101; j++) {
                int tmp = dp[j - val];// 이전값 구하기

                if (tmp != Integer.MAX_VALUE) { // 이전 값이 있다면
                    // j명을 모을때 까지 든 이전 비용과 현재 비용을 구한다!
                    dp[j] = Math.min(dp[j], tmp + cost);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        // "최소 C명"이기때문에 C+50명을 구해도 아무 문제 없음
        // TC에 따라 C명을 맞춰 구하는 비용보다, C명을 초과해서 구하는게 비용이 적음
        for (int i = C; i < C + 101; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }
}