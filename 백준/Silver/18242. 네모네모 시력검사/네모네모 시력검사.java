import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;

    public static void dfs(int x, int y, String dir) {

        if (x < 0 || x >= M || y < 0 || y >= N) return;
        if (arr1[y][x] == '.') return;
        if (vi[y][x]) return;
        vi[y][x] = true;

        dfs(x + 1, y, "UP");
        dfs(x, y + 1, "RIGHT");
        dfs(x - 1, y, "DOWN");
        dfs(x, y - 1, "LEFT");

        System.out.println(dir);
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int startX = -1;
        int startY = -1;
        arr1 = new char[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == '#' && startX == -1) {
                    startX = j;
                    startY = i;
                }
            }
        }

        dfs(startX, startY, "UP");

    }
}