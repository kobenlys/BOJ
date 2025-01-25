import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static int[][] dp;
    public static Set<Integer> set;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static int dfs(int x, int y) {

        if (!isRange(x, y) || arr1[y][x] == 'H') {
            return 0;
        }
        if (dp[y][x] != 0) return dp[y][x];

        for (int i = 0; i < 4; i++) {
            int step = Character.getNumericValue(arr1[y][x]);

            int nx = x + dx[i] * step;
            int ny = y + dy[i] * step;

            int nextStep = ny * M + nx;

            if (isRange(nx, ny) && set.contains(nextStep)) {
                System.out.println(-1);
                System.exit(0);
            }

            if(isRange(nx, ny)){
                set.add(nextStep);
                dp[y][x] = Math.max(dp[y][x], dfs(nx, ny) + 1);
                set.remove(nextStep);
            }else{
                dp[y][x] = Math.max(dp[y][x], dfs(nx, ny) + 1);
            }
        }
        return dp[y][x];
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static void printArray(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new HashSet<>();
        arr1 = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                dp[i][j] = 0;
            }
        }

        set.add(0);
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
}