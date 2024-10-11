import java.io.*;
import java.util.*;

public class Solution {
    public static int N, X;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static boolean checkRowLeft(int x, int y) {

        int bottom = arr1[y][x - 1];
        if (bottom == arr1[y][x]) return true;
        if(bottom +1 < arr1[y][x]) return false;

        if (bottom + 1 == arr1[y][x]) {
            for (int j = 1; j <= X; j++) {
                int tmpX = x + dx[2] * j;
                int tmpY = y + dy[2] * j;
                if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= N) {
                    return false;
                }
                if (arr1[tmpY][tmpX] != bottom || vi[tmpY][tmpX]) {
                    return false;
                }
                vi[tmpY][tmpX] = true;
            }
        }
        return true;
    }

    public static boolean checkRowRight(int x, int y) {


        int bottom = arr1[y][x + 1];
        if (bottom == arr1[y][x]) return true;
        if(bottom +1 < arr1[y][x]) return false;

        if (bottom + 1 == arr1[y][x]) {

            for (int j = 1; j <= X; j++) {
                int tmpX = x + dx[3] * j;
                int tmpY = y + dy[3] * j;
                if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= N) {
                    return false;
                }
                if (arr1[tmpY][tmpX] != bottom || vi[tmpY][tmpX]) {
                    return false;
                }
                vi[tmpY][tmpX] = true;
            }
        }
        return true;
    }

    public static boolean checkColUp(int x, int y) {


        int bottom = arr1[y - 1][x];
        if (bottom == arr1[y][x]) return true;
        if(bottom +1 < arr1[y][x]) return false;

        if (bottom + 1 == arr1[y][x]) {

            for (int j = 1; j <= X; j++) {
                int tmpX = x + dx[0] * j;
                int tmpY = y + dy[0] * j;
                if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= N) {
                    return false;
                }
                if (arr1[tmpY][tmpX] != bottom || vi[tmpY][tmpX]) {
                    return false;
                }
                vi[tmpY][tmpX] = true;
            }
        }
        return true;
    }

    public static boolean checkColDown(int x, int y) {

        int bottom = arr1[y + 1][x];
        if (bottom == arr1[y][x]) return true;
        if(bottom +1 < arr1[y][x]) return false;

        if (bottom + 1 == arr1[y][x]) {
            for (int j = 1; j <= X; j++) {
                int tmpX = x + dx[1] * j;
                int tmpY = y + dy[1] * j;
                if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= N) {
                    return false;
                }
                if (arr1[tmpY][tmpX] != bottom || vi[tmpY][tmpX]) {
                    return false;
                }
                vi[tmpY][tmpX] = true;
            }
        }
        return true;
    }

    


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int T = 1; T <= tc; T++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            int answer = 0;
            arr1 = new int[N][N];

            vi = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                boolean isPossible = true;
                for (int j = 0; j < N; j++) {
                    if (j != 0 && !checkRowLeft(j, i)) {
                        isPossible = false;
                        break;
                    }

                    if (j != N - 1 && !checkRowRight(j, i)) {
                        isPossible = false;
                        break;
                    }

                }
                if (isPossible){
                    answer++;
                }
            }

            vi = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                boolean isPossible = true;
                for (int j = 0; j < N; j++) {

                    if (j != 0 && !checkColUp(i, j)) {
                        isPossible = false;
                        break;
                    }

                    if (j != N - 1 && !checkColDown(i, j)) {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible){
                    answer++;
                }
            }


            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}