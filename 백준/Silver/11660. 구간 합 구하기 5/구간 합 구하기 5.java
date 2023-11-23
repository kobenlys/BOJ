import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr1 = new int[N + 1][N + 1];
        int[][] dp = new int[N + 1][N + 1];

        // 배열에 데이터 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 누적합 구하는 공식 - arr1[i][j] 부분에 바로 입력을 받아도 상관없다. 이해를 위해 나눠서 코딩하였다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 누적합 구하는 공식
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + arr1[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int x1 = Integer.parseInt(st.nextToken()); // 기준 좌표
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()); // 목표 좌표
            int y2 = Integer.parseInt(st.nextToken());
            
            // 누적합 중 구간합 구하는 공식
            long res = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            sb.append(res).append("\n");
        }
        
        System.out.print(sb);
    }
}
