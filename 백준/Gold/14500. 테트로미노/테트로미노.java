import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, max;
    public static int[][] arr1;

    public static boolean range(int x, int y) { // 범위체크
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static void algorithm(int x, int y) {

        int[] ans = new int[5];
        ans[0] = poly1(x, y); // 'I' 모양 테트로미노
        ans[1] = poly2(x, y); // 2*2 정사각형 모양 테트로미노
        ans[2] = poly3(x, y); // 'L' 모양 테트로미노
        ans[3] = poly4(x, y); // 'Z' 모양 테트로미노
        ans[4] = poly5(x, y); // 'ㅜ' 모양 테트로미노
        Arrays.sort(ans);
        
        max = Math.max(ans[4], max);
    }

    public static int poly1(int x, int y) { // 'I' 모양 테트로미노
        int res1 = 0;
        int res2 = 0;
        boolean check1 = false;
        boolean check2 = false;
        for (int i = 0; i < 4; i++) {
            if (!check1 && range(x + i, y)) {
                res1 += arr1[y][x + i];
            } else {
                res1 = 0;
                check1 = true;
            }
            if (!check2 && range(x, y + i)) {
                res2 += arr1[y + i][x];
            } else {
                res2 = 0;
                check2 = true;
            }
        }
        return Math.max(res1, res2);
    }

    public static int poly2(int x, int y) { // 2*2 정사각형 모양 테트로미노
        int res1 = 0;
        int[] dx = {0, 1, 0, 1};
        int[] dy = {0, 0, 1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (range(nx, ny)) {
                res1 += arr1[ny][nx];
            } else {
                break;
            }
        }
        return res1;
    }

    public static int poly3(int x, int y) { // 'L' 모양 테트로미노
        int[][] dx = {{0, -1, -1, -1}, {-1, -1, 0, 1}, {0, 1, 1, 1}, {1, 1, 0, -1},
                {1, 1, 1, 0}, {1, 0, -1, -1}, {-1, -1, -1, 0}, {-1, 0, 1, 1}};
        int[][] dy = {{1, 1, 0, -1}, {0, -1, -1, -1}, {-1, -1, 0, 1}, {0, 1, 1, 1},
                {-1, 0, 1, 1}, {1, 1, 1, 0}, {1, 0, -1, -1}, {-1, -1, -1, 0}};
        int max = 0;

        for (int i = 0; i < 8; i++) {
            int res = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[i][j];
                int ny = y + dy[i][j];
                if (range(nx, ny)) {
                    res += arr1[ny][nx];
                } else {
                    res = 0;
                    break;
                }
            }
            max = Math.max(max, res);
        }
        return max;
    }

    public static int poly4(int x, int y) { // 'Z' 모양 테트로미노
        int[][] dx = {{0, 0, -1, -1}, {0, -1, 0, 1}, {0, 0, 1, 1}, {0, 1, 0, -1},
                {0, 0, 1, 1}, {0, -1, 0, 1}, {0, 0, -1, -1}, {0, 1, 0, -1}};
        int[][] dy = {{0, 1, 0, -1}, {0, 0, -1, -1}, {0, -1, 0, 1}, {0, 0, 1, 1},
                {0, 1, 0, -1}, {0, 0, 1, 1}, {0, -1, 0, 1}, {0, 0, -1, -1}};
        int max = 0;

        for (int i = 0; i < 8; i++) {
            int res = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[i][j];
                int ny = y + dy[i][j];
                if (range(nx, ny)) {
                    res += arr1[ny][nx];
                } else {
                    res = 0;
                    break;
                }
            }
            max = Math.max(max, res);
        }
        return max;
    }

    public static int poly5(int x, int y) { // 'ㅜ' 모양 테트로미노
        int[][] dx = {{0,-1,1,0}, {0,-1,1,0}, {0,0,0,-1}, {0,0,0,1},
                {0,-1,0,1}, {0,1,1,1}, {0,-1,0,1}, {0,-1,-1,-1}};
        int[][] dy = {{0,0,0,1}, {0,0,0,-1}, {0,-1,1,0}, {0,-1,1,0},
                {0,-1,-1,-1}, {0,1,0,-1}, {0,1,1,1}, {0,-1,0,1}};
        int max = 0;
        // 00 01 02
        // 10 11 12
        // 20 21 22
        for (int i = 0; i < 8; i++) {
            int res = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[i][j];
                int ny = y + dy[i][j];
                if (range(nx, ny)) {
                    res += arr1[ny][nx];
                } else {
                    res = 0;
                    break;
                }
            }
            max = Math.max(max, res);
        }
        return max;
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                algorithm(j, i);
            }
        }
        System.out.println(max);
    }
}
