import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr1 = new int[40][40];
        arr1[1][1] = 1;

        for (int i = 2; i <= 30; i++) {
            for (int j = 1; j <= 30; j++) {
                if (arr1[i - 1][j] == 0 && arr1[i - 1][j - 1] == 0) {
                    break;
                }
                arr1[i][j] = arr1[i - 1][j] + arr1[i - 1][j - 1];
            }
        }

        System.out.println(arr1[N][M]);
    }
}