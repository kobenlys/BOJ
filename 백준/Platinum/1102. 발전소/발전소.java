import java.io.*;
import java.util.*;

public class Main {
    public static int N, P;
    public static int[][] arr1;
    public static int[] dp;

    public static int dfs(int cnt, int pos) {

        if (cnt >= P) return 0;
        if (dp[pos] != -1) return dp[pos];

        dp[pos] = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            // 작동중인 발전소 찾기
            if ((pos & (1 << i)) != 0) {

                for (int j = 0; j < N; j++) {
                    // 고장난 발전소 찾기
                    if ((pos & (1 << j)) == 0) {
                        // 이전 저장된 dp[pos]값 보다, 현재 경로(탑다운)가 더 작다면 갱신한다.
                        dp[pos] = Math.min(dp[pos], dfs(cnt + 1, pos | (1 << j)) + arr1[i][j]);
                    }
                }
            }
        }

        return dp[pos];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        dp = new int[1 << N];
        Arrays.fill(dp, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String str = br.readLine();
        P = Integer.parseInt(br.readLine());
        int pos = 0;
        int cnt = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'Y') {
                cnt++;
                pos = pos | (1 << i);
            }
        }

        int answer = dfs(cnt, pos);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}