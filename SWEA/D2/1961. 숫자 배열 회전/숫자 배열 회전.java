import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());
            int[][] arr1 = new int[N][N];
            String[][] tmp = new String[N][3];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) { // 90도
                StringBuilder copy = new StringBuilder();
                for (int j = N - 1; 0 <= j; j--) {
                    copy.append(arr1[j][i]);
                }
                tmp[i][0] = copy.toString();
            }

            for (int i = N - 1; 0 <= i; i--) { // 180도
                StringBuilder copy = new StringBuilder();
                for (int j = N - 1; 0 <= j; j--) {
                    copy.append(arr1[i][j]);
                }
                tmp[(N - 1) - i][1] = copy.toString();
            }

            for (int i = N - 1; 0 <= i; i--) { // 270도
                StringBuilder copy = new StringBuilder();
                for (int j = 0; j < N; j++) {
                    copy.append(arr1[j][i]);
                }
                tmp[(N - 1) - i][2] = copy.toString();
            }

            sb.append("#").append(tc).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(tmp[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}