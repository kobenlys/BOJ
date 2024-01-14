import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1;
    public static int maxN, N, M, siteX, siteY;

    public static void algorithm(int x, int y, int range) {

        for (int i = y; i < range - 1; i++) {
            isRight(maxN, x, i);
            arr1[i][x] = maxN--;
        }

        for (int i = x; i < range - 1; i++) {
            isRight(maxN, i, range - 1);
            arr1[range - 1][i] = maxN--;
        }

        for (int i = range - 1; i > y; i--) {
            isRight(maxN, range - 1, i);
            arr1[i][range - 1] = maxN--;
        }

        for (int i = range - 1; i > x; i--) {
            isRight(maxN, i, y);
            arr1[y][i] = maxN--;
        }
    }

    public static void isRight(int max, int x, int y) {
        if (M == 1) {
            siteX = N / 2;
            siteY = N / 2;
        } else if (max == M) {
            siteX = x;
            siteY = y;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        maxN = N * N;

        int x = 0;
        int y = 0;
        int range = N;
        while (maxN != 1) {
            algorithm(x, y, range);
            range--;
            x++;
            y++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                sb.append(arr1[i][j] == 0 ? 1 : arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(siteY + 1).append(" ").append(siteX + 1);
        System.out.print(sb);
    }
}
