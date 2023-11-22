import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        
        int[][] dp = new int[31][31];
        for (int i = 0; i < 31; i++) {
            dp[i][0] = 1;
        }
        // dp 배열에 30 조합 경우 생성
        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < 31; j++) {
                // nCr = (n-1)C(r-1) + (n-1)Cr -> 조합 공식 사용
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        for (int i = 0; i < t; i++) {
            
            // 값만 받아서 dp배열에 대입 후 출력
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // n
            int M = Integer.parseInt(st.nextToken()); // m

            System.out.println(dp[M][N]);
        }
    }
}
