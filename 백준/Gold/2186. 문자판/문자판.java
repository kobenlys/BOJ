import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, K;
    public static String pos;
    public static int[][][] dp;
    public static char[][] arr1;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static int dfs(int depth, int x, int y) {

        if (depth == pos.length() - 1) {
            return dp[depth][y][x] = 1;
        }

        if (dp[depth][y][x] != -1) {
            return dp[depth][y][x];
        }
        dp[depth][y][x] = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= K; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(pos.charAt(depth+1) != arr1[ny][nx]) continue;
                dp[depth][y][x] += dfs(depth + 1, nx, ny);
            }
        }

        return dp[depth][y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int answer = 0;

        arr1 = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
            }
        }

        pos = br.readLine();
        dp = new int[pos.length()][N][M];
        for (int i = 0; i < pos.length(); i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pos.charAt(0) == arr1[i][j]) {
                    answer += dfs(0, j, i);
                }
            }
        }

        System.out.println(answer);
    }
}