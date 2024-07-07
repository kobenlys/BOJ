import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static char[][] arr1;
    public static char[][] res;
    public static int[][] mines;

    public static void mineChecker(int x, int y) {
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            mines[ny][nx]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new char[N][N];
        res = new char[N][N];
        mines = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                arr1[i][j] = input.charAt(j);
                res[i][j] = arr1[i][j];
                if (arr1[i][j] == '*') mineChecker(j, i);
            }
        }

        boolean isExplosion = false;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'x' && res[i][j] == '.') {
                    res[i][j] = (char) (mines[i][j] + '0');

                }
                if (input.charAt(j) == 'x' && res[i][j] == '*') {
                    isExplosion = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isExplosion) {
                    char c = res[i][j] == '*' ? '.' : res[i][j];
                    sb.append(c);
                } else {
                    sb.append(res[i][j]);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}