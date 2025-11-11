import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int M = (str.length() + 1) / N;
        char[][] arr1 = new char[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i % 2 == 0) {
                    arr1[i][j] = str.charAt(i * N + j);
                } else {
                    arr1[i][(N - 1) - j] = str.charAt(i * N + j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr1[j][i]);
            }
        }

        System.out.println(sb);
    }
}