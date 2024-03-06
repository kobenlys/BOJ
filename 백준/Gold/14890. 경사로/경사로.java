import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, L, ans;
    public static int[][] arr1;

    public static boolean isPossible1(int y) {
        int left=0, right=0;
        int flag = 0;
        boolean ischeck = false;
        while (right != N) {

            if (arr1[y][left] == arr1[y][right]) {
                right++;
                flag++;

            } else if (arr1[y][left] + 1 == arr1[y][right]) {
                if (flag >= L) {
                    flag = 0;
                    ischeck = true;
                    left = right;
                } else {
                    ischeck = false;
                    break;
                }

            }else if (arr1[y][left] == arr1[y][right]+1) {
                flag = 1;
                int n = arr1[y][right];
                for (int i = 0; i < L - 1; i++) {
                    ++right;
                    if(right >= N) break;
                    if (n == arr1[y][right]) flag++;
                }
                //2 2 3 2 2 2
                if (flag >= L) {
                    flag = -1;
                    ischeck = true;
                    left = right;
                } else {
                    ischeck = false;
                    break;
                }

            } else {
                ischeck = false;
                break;
            }
        }
        return ischeck;
    }

    public static boolean isPossible2(int x) {

        int up=0, down=0;
        int flag = 0;
        boolean ischeck = false;
        while (down != N) {

            if (arr1[up][x] == arr1[down][x]) {
                down++;
                flag++;

            } else if (arr1[up][x] + 1 == arr1[down][x]) {
                if (flag >= L) {
                    flag = 0;
                    ischeck = true;
                    up = down;
                } else {
                    ischeck = false;
                    break;
                }
            } else if (arr1[up][x] == arr1[down][x] + 1) {
                flag = 1;
                int n = arr1[down][x];
                for (int i = 0; i < L-1; i++) {
                    ++down;
                    if(down >= N) break;
                    if(n == arr1[down][x]) flag++;
                }

                if (flag >= L) {
                    flag = -1;
                    ischeck = true;
                    up = down;
                } else {
                    ischeck = false;
                    break;
                }

            } else {
                ischeck = false;
                break;
            }
        }
        return ischeck;
    }

    public static boolean isAllSame1(int flag) {
        boolean isSame = true;
        int n = arr1[flag][0];
        for (int i = 0; i < N; i++) {
            if (n != arr1[flag][i]) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }

    public static boolean isAllSame2(int flag) {
        boolean isSame = true;
        int n = arr1[0][flag];
        for (int i = 0; i < N; i++) {
            if (n != arr1[i][flag]) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }



    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            if (isPossible1(i)) {
                ans++;
            }
            if (isPossible2(i)) {
                ans++;
            }
            if (isAllSame1(i)) {
                ans++;
            }
            if (isAllSame2(i)) {
                ans++;
            }
        }
        System.out.print(ans);
    }
}