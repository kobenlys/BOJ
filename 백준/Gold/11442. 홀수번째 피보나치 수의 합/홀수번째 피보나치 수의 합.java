import java.io.*;
import java.util.*;

public class Main {
    static int mod = 1_000_000_007;

    public static long[][] dfs(long size) {

        if (size == 1) {
            // 피보나치 수열 초기값
            return new long[][]{{1, 1}, {1, 0}};
        }
        // 분할정복 이용한다. 거듭제곱효과
        long[][] tmp = dfs(size / 2);

        if (size % 2 == 0) {
            // 짝수
            return matrixCalc(tmp, tmp);
        } else {
            // 홀수, 한번더 곱해준다,
            return matrixCalc(tmp, matrixCalc(tmp, dfs(1)));
        }
    }

    public static long[][] matrixCalc(long[][] arr1, long[][] arr2) {

        long[][] res = new long[2][2];
        // 행렬곱셈
        res[0][0] = (arr1[0][0] * arr2[0][0]) % mod + (arr1[0][1] * arr2[1][0]) % mod;
        res[0][1] = (arr1[0][0] * arr2[1][0]) % mod + (arr1[0][1] * arr2[1][1]) % mod;
        res[1][0] = (arr1[1][0] * arr2[0][0]) % mod + (arr1[1][1] * arr2[0][1]) % mod;
        res[1][1] = (arr1[1][0] * arr2[1][0]) % mod + (arr1[1][1] * arr2[1][1]) % mod;
        res[0][0] %= mod; res[0][1] %= mod;
        res[1][0] %= mod; res[1][1] %= mod;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        long[][] ans = dfs(N);
        // 피보나치 수열 특증 -> fibo(N) N이 짝수라면, 이전 모든 홀수번째 피보나치 수 합과 같음
        // 홀수라면 그 다음 자리 피보나치 수와 같다.
        System.out.println(N % 2 == 0 ? ans[0][1] : ans[0][0]);
    }
}