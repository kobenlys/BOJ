import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1;

    public static void dfs(int x, int y) {
        if (y == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr1[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
        }

        if (x == 9) {
            dfs(0, y+1);
            return;
        }

        if (arr1[y][x] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check1(x, y, i) && check2(x, y, i)) {
                    arr1[y][x] = i;
                    dfs(x + 1, y);
                    arr1[y][x] = 0;
                }
            }
        } else {
            dfs(x + 1, y);
        }
    }

    public static boolean check1(int x, int y, int flag) {
        for (int i = 0; i < 9; i++) {
            if (i != y) {
                if (flag == arr1[i][x]) return false;
            }
            if (i != x) {
                if (flag == arr1[y][i]) return false;
            }
        }
        return true;
    }

    public static boolean check2(int x, int y, int flag) {
        int newX = (x / 3) * 3;
        int newY = (y / 3) * 3;
        for (int i = newY; i < newY + 3; i++) {
            for (int j = newX; j < newX + 3; j++) {
                if (flag == arr1[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr1 = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
    }
}