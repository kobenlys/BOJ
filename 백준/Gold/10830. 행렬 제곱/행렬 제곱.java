import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, mod = 1000;
    public static long[][] arr1;

    public static long[][] dfs(long size) {
        if (size == 1) {
            return arr1;
        }

        long[][] tmp = dfs(size / 2);

        if (size % 2 == 0) {
            // 짝수일때  (A^2)^2  = A^4
            return matrixMulti(tmp, tmp);
        } else {
            // 홀수일때 , (A^2)^2 * A  = A^5
            return matrixMulti(matrixMulti(tmp, tmp), dfs(1));
        }
    }

    public static long[][] matrixMulti(long[][] a, long[][] b) {
        long[][] ans = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    ans[i][j] += (a[i][k] * b[k][j]) % mod;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] %= mod;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        arr1 = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] ans = dfs(B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i][j] % mod).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}