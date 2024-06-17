import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static int[][] dp;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static int dfs(int x, int y) {

        // 이미 탐색 되었더라면 리턴
        if (dp[y][x] != 0) return dp[y][x];

        // 상 하 좌 우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            // 현재 칸보다 다음 칸이 크다면 전진
            if (arr1[ny][nx] > arr1[y][x]) {
                // 가장 큰 값을 저장
                dp[y][x] = Math.max(dp[y][x], dfs(nx, ny) + 1);
            }
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int answer = 0;
        arr1 = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 자리별 dfs탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(j, i));
            }
        }

        System.out.println(answer + 1);
    }
}