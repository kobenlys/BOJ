import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int res = 26;
    public static int[][] arr1 = new int[10][10];
    public static int[] paper = new int[6];


    public static void dfs(int x, int y, int cnt) {


        if (x == 10 && y == 9) {
            res = Math.min(res, cnt);
            return;
        }

        if (x == 10) {
            dfs(0, y + 1, cnt);
            return;
        }


        if (arr1[y][x] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (isCover(x, y, i) && paper[i] > 0) {
                    paper[i]--;
                    coverPaper(x, y, i, 0);
                    dfs(x + 1, y, cnt + 1);
                    coverPaper(x, y, i, 1);
                    paper[i]++;
                }
            }
        } else {
            dfs(x + 1, y, cnt);
        }
    }

    public static void coverPaper(int x, int y, int size, int paperN) {
        for (int i = y; i < size + y; i++) {
            for (int j = x; j < size + x; j++) {
                arr1[i][j] = paperN;
            }
        }
    }

    public static boolean isCover(int x, int y, int size) {
        for (int i = y; i < size + y; i++) {
            for (int j = x; j < size + x; j++) {
                if (i >= 10 || j >= 10) return false;
                if (arr1[i][j] == 0) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(paper, 5);
        dfs(0, 0, 0);
        System.out.println(res == 26 ? -1 : res);
    }
}