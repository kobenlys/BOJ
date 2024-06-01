import java.io.*;
import java.util.*;

public class Main {
    public static int[][] arr1;
    public static int[] dp;
    public static int N, P;

    public static int dfs(int cnt, int pos) {

        if(cnt >= P) return 0;
        if(dp[pos] != -1) return dp[pos];

        dp[pos] = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            // i 번째 발전소가 정상 작동한다면
                if ((pos & (1 << i)) != 0) {
                    for (int j = 0; j < N; j++) {
                        // j 번째 발전소가 정상 작동하지 않는다면.
                        if ((pos & (1 << j)) == 0) {
                            // 최소값 구하기, 재귀
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
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            String state = br.readLine();
            int cnt = 0;
            int pos = 0;

            for (int i = 0; i < state.length(); i++) {
                if(state.charAt(i) == 'Y'){
                    cnt++;
                    pos = pos | (1 << i);
            }
        }

        P = Integer.parseInt(br.readLine());
        int answer = dfs(cnt, pos);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}