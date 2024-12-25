import java.io.*;
import java.util.*;

public class Main {
    public static long MOD = 1_000_000_000;

    public static long[][] matrixMulti(long[][] A, long[][] B) {
        long[][] arr1 = new long[2][2];
        arr1[0][0] = ((A[0][0] * B[0][0]) % MOD + (A[0][1] * B[1][0]) % MOD) % MOD;
        arr1[0][1] = ((A[0][0] * B[0][1]) % MOD + (A[0][1] * B[1][1]) % MOD) % MOD;
        arr1[1][0] = ((A[1][0] * B[0][0]) % MOD + (A[1][1] * B[1][0]) % MOD) % MOD;
        arr1[1][1] = ((A[1][0] * B[0][1]) % MOD + (A[1][1] * B[1][1]) % MOD) % MOD;
        return arr1;
    }

    public static long[][] dfs(long size) {

        if (size == 1) {
            return new long[][]{{1, 1}, {1, 0}};
        }

        long newSize = size / 2;
        long[][] res = dfs(newSize);

        if (size % 2 == 0) {
            return matrixMulti(res, res);
        } else {
            return matrixMulti(matrixMulti(res, res), dfs(1));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[][] arr1 = dfs(A + 2);
        long[][] arr = dfs(A);
        long[][] arr2 = dfs(B + 2);

        System.out.println( (arr2[0][1] % MOD - arr1[0][1] %MOD + MOD + arr[0][1]) % MOD);

        // 1 1 2 3 5 8 13 21
        // 12 -  7 = 5
    }
}