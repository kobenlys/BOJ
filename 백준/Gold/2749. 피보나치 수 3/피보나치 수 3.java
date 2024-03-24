import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int mod = 1_000_000;

    public static long[][] dfs(long size) {

        if (size == 1) {
            long[][] arr1 = {{1, 1}, {1, 0}};
            return arr1;
        }

        long[][] tmp = dfs(size / 2);

        if (size % 2 == 0) {
            return matrixMulti(tmp, tmp);
        } else {
            return matrixMulti(matrixMulti(tmp, tmp),dfs(1));
        }
    }

    public static long[][] matrixMulti(long[][] a, long[][] b) {
        long[][] arr1 = new long[2][2];

        arr1[0][0] = ((a[0][0] * b[0][0]) % mod + (a[0][1] * b[1][0]) % mod) % mod;
        arr1[0][1] = ((a[0][0] * b[0][1]) % mod + (a[0][1] * b[1][1]) % mod) % mod;
        arr1[1][0] = ((a[1][0] * b[0][0]) % mod + (a[1][1] * b[1][0]) % mod) % mod;
        arr1[1][1] = ((a[1][0] * b[0][1]) % mod + (a[1][1] * b[1][1]) % mod) % mod;

        return arr1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());

        System.out.println(dfs(N)[0][1]);
    }
}