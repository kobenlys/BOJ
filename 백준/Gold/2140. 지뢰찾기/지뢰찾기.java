import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] mineBoard;
    public static boolean[][] arr1;

    public static boolean isPlace(int x, int y) {
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (mineBoard[ny][nx] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void 안녕하세요(int x, int y) {
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (mineBoard[ny][nx] > 1) {
                mineBoard[ny][nx]--;
            }
        }
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        mineBoard = new int[N][N];
        arr1 = new boolean[N][N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char num = str.charAt(j);

                if (num != '#') {
                    mineBoard[i][j] = Character.getNumericValue(num) + 1;
                }
            }
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (isPlace(j, i)) {
                    answer++;
                    안녕하세요(j, i);
                }
            }
        }
        System.out.println(answer);
    }
}