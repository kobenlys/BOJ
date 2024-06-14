import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int[][] arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += Math.abs(arr1[0][j] - arr1[i][j]);
            }

            if (sum > 2000) cnt++;
        }

        if ((N - 1) % 2 == 0) {
            System.out.println((N - 1) / 2 <= cnt ? "YES" : "NO");
        } else {
            System.out.println((N - 1) / 2 < cnt ? "YES" : "NO");
        }
    }
}