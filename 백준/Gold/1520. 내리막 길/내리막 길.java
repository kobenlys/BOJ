import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static int[][] dp;

    public static int dfs(int x, int y) {

        if (x == M - 1 && y == N - 1) {
            // 목표도착 시 1을 리턴한다.
            return 1;
        }
        // 가본적 없는 경로라면
        if (dp[y][x] == -1) {
            // 0 으로 초기화
            dp[y][x] = 0;
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                // 현재위치보다 이동할 위치의 숫자가 낮다면 경로 진행
                if (arr1[y][x] > arr1[ny][nx]) {
                    // 현재위치에 dfs를 통해 리턴받은 숫자 입력(경로 수)
                    dp[y][x] += dfs(nx, ny);
                }
            }
        }
        // 이미 지나온 경로라면, dp배열에 경로 수를 입력했기 때문에 바로 리턴한다.
        // dp알고리즘 사용하는 이유는 이미 방문했던 경로를 다시 갈 필요없기때문에 이전경로 수를 입력해
        // 중복 탐색을 방지한다.
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        dp = new int[N][M]; // dp + dfs 풀이

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 초기 dp 설정
            }
        }
        // dfs실행.
        System.out.print(dfs(0, 0));
    }
}