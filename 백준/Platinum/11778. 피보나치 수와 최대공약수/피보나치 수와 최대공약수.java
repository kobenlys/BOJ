import java.io.*;
import java.util.*;

public class Main {
    public static int mod = 1_000_000_007;

    public static long GCD(long a, long b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }

    public static long[][] matrixMulti(long[][] copy1, long[][] copy2) {
        long[][] res = new long[2][2];
        res[0][0] = ((copy1[0][0] * copy2[0][0]) % mod + (copy1[0][1] * copy2[1][0]) % mod) % mod;
        res[0][1] = ((copy1[0][0] * copy2[0][1]) % mod + (copy1[0][1] * copy2[1][1]) % mod) % mod;
        res[1][0] = ((copy1[1][0] * copy2[0][0]) % mod + (copy1[1][1] * copy2[1][0]) % mod) % mod;
        res[1][1] = ((copy1[1][0] * copy2[0][1]) % mod + (copy1[1][1] * copy2[1][1]) % mod) % mod;

        return res;
    }

    public static long[][] dfs(long num) {

        if (num == 1) {
            long[][] arr1 = {{1, 1}, {1, 0}};
            return arr1;
        }

        long[][] tmp = dfs(num / 2);

        if (num % 2 == 0) {
            return matrixMulti(tmp, tmp);
        } else {
            return matrixMulti(tmp, matrixMulti(tmp, dfs(1)));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long gcd = GCD(N, M);
        long[][] ans = dfs(gcd);

        System.out.println(ans[0][1]);
    }
}