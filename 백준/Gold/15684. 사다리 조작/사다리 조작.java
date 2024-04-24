import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, H;
    public static int[][] arr1;

    public static boolean makeLadders(int start, int limit) {

        boolean isOk = false;

        if (start == limit) {
            if (isPossible()) return true;
            return false;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (arr1[i][j] == 0 && arr1[i][j + 1] == 0) {
                    arr1[i][j] = 1;
                    arr1[i][j + 1] = 2;
                    isOk |= makeLadders(start + 1, limit);
                    arr1[i][j] = arr1[i][j + 1] = 0;
                }
            }
        }

        return isOk;
    }

    public static boolean isPossible() {

        for (int i = 0; i < N; i++) {
            int x = i;
            int y = 0;

            while (true) {
                if (y == H) {
                    break;
                }

                if (arr1[y][x] == 1) {
                    x++;
                } else if (arr1[y][x] == 2) {
                    x--;
                }
                y++;
            }
            if (x != i) return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int answer = -1;

        arr1 = new int[H + 1][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            arr1[s][e] = 1;
            arr1[s][e + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            if (makeLadders(0, i)) {
                answer = i;
                break;
            }
        }
        System.out.print(answer);
    }
}