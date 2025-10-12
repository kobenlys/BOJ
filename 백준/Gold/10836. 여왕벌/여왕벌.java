import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static long[][] arr1;
    public static long[] diff;

    public static void placeArr() {

        for (int i = 0; i < M; i++) {
            arr1[i][0] = diff[M - i - 1] + 1;
        }

        for (int i = 1; i < M; i++) {
            arr1[0][i] = diff[M + i - 1] + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr1 = new long[M][M];
        diff = new long[M + M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int baseCnt = Integer.parseInt(st.nextToken());
            int oneCnt = Integer.parseInt(st.nextToken()) - 1;
            int twoCnt = Integer.parseInt(st.nextToken()) - 1;
            diff[baseCnt] += 1;
            diff[baseCnt + oneCnt + 1] -= 1;
            diff[baseCnt + oneCnt + 1] += 2;
        }

        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
        }
        
        placeArr();

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                long left = arr1[i][j - 1];
                long top = arr1[i - 1][j];
                long diagonal = arr1[i - 1][j - 1];
                arr1[i][j] = Math.max(diagonal, Math.max(left, top));
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}