import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, white, blue;
    public static int[][] arr1;

    public static void algorithm(int x, int y, int size) {


        if (calc(x, y, size)) {
            if (arr1[y][x] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }
        
        int nSize = size / 2;
        algorithm(x, y, nSize);
        algorithm(x + nSize, y, nSize);
        algorithm(x, y + nSize, nSize);
        algorithm(x + nSize, y + nSize, nSize);
    }

    public static boolean calc(int x, int y, int size) {
        int color = arr1[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (arr1[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        algorithm(0, 0, N);
        System.out.println(white+"\n"+blue);
    }
}