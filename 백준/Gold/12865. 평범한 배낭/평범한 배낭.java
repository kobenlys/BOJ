import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   public static int N, K;
   public static int[][] item;
    public static int[][] dp;

    public static void algorithm() { // 냅색 알고리즘
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (item[i][0] > j) { // J = 현재 무게 보다 물건의 무게가 더 크다면 윗 행 복사
                    dp[i][j] = dp[i - 1][j];
                }else{ // 현재 무게 보다 물건의 무게가 적거나 같다면 더 큰 값으로 갱신
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[i][0]] + item[i][1]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 물건 수
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        item = new int[N +1][2];
        dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            item[i][0] = Integer.parseInt(st.nextToken()); // 물건 무게
            item[i][1] = Integer.parseInt(st.nextToken()); // 물건 만족도
        }

        algorithm();
        //결국 테이블에서 가장 끝부분이 최적값임.
        System.out.println(dp[N][K]);
    }
}
