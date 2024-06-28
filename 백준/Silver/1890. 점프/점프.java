import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static long[][] vi;

    public static long dfs(int x, int y) {

        if(x == N-1 && y == N-1) return 1;
        if(vi[y][x] != -1) return vi[y][x];

        vi[y][x] = 0;

        int len = arr1[y][x];
        int ny = y + len;
        int nx = x + len;

        if (nx >= 0 && nx < N) {
            vi[y][x] += dfs(nx, y);
        }

        if (ny >= 0 && ny < N) {
            vi[y][x] += dfs(x, ny);
        }

        return vi[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        vi = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                vi[i][j] = -1;
            }
        }

        long answer = dfs(0, 0);
        System.out.println(answer);
    }
}