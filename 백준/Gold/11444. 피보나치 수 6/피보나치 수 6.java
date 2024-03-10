import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static long mod = 1_000_000_007;

    public static long[][] dfs(long num) {
        if (num == 1) {
            // 피보나치 수열 초기값 지정
            long[][] arr = {{1, 1}, {1, 0}};
            return arr;
        }

        long[][] tmp = dfs(num / 2);
        if (num % 2 == 0) {
            return matrixMulti(tmp, tmp);
        } else {
            // 홀수 인 경우는 * 초깃값을 한번 더 해준다.
            return matrixMulti(matrixMulti(tmp, tmp), dfs(1));
        }
    }

    // 행렬 곱셈
    public static long[][] matrixMulti(long[][] a, long[][] b) {
        long[][] ans = new long[2][2];
        ans[0][0] = (a[0][0] * b[0][0] % mod + (a[0][1] * b[1][0]) % mod) % mod;
        ans[0][1] = (a[1][0] * b[0][0] % mod + a[1][1] * b[1][0] % mod) % mod;
        ans[1][0] = (a[0][0] * b[0][1] % mod + a[0][1] * b[1][1] % mod) % mod;
        ans[1][1] = (a[1][0] * b[0][1] % mod + a[1][1] * b[1][1] % mod) % mod;
        return ans;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        System.out.print(dfs(N)[0][1]);
        //0,1 을 출력하는 이유
        // N+1 , N
        // N   , N-1
        // 을 표시한다, 행렬곱셈을 통해 O(logN)의 시간복잡도를 가지는 피보나치 수열 만들 수 있음.
    }
}